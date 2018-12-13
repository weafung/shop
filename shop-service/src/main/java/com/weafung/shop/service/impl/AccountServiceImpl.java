package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.common.util.EncryptUtil;
import com.weafung.shop.common.util.wechat.SnsapiUserinfo;
import com.weafung.shop.dao.AccountInfoMapper;
import com.weafung.shop.dao.AccountTokenMapper;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.AccountInfo;
import com.weafung.shop.model.po.AccountToken;
import com.weafung.shop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weifengshih
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private AccountTokenMapper accountTokenMapper;

    private Integer WECHAT_TYPE = 1;

    public static final String HASH_SALT = "fej8w89432h5iub7yvf78d95iu3289hgfiu43";

    @Override
    public ResponseDTO<AccountInfoDTO> getAccountInfoByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, null);
        }
        AccountToken accountToken = accountTokenMapper.getAccountToken(token);
        if (accountToken == null) {
            return ResponseDTO.build(CodeEnum.FORBIDDEN);
        }
        AccountInfo accountInfo = accountInfoMapper.getAccount(accountToken.getAccountId());
        if (accountInfo == null) {
            return ResponseDTO.build(CodeEnum.FORBIDDEN);
        }
        return ResponseDTO.buildSuccess(AccountInfoDTO.fromAccountInfo(accountInfo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> updateOrInsertAccountInfo(SnsapiUserinfo snsapiUserinfo) {
        if (snsapiUserinfo == null || StringUtils.isBlank(snsapiUserinfo.getOpenid())) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, null);
        }
        String accountId = EncryptUtil.md5(HASH_SALT + snsapiUserinfo.getOpenid() + HASH_SALT) + "@163.com";
        String token = EncryptUtil.md5(HASH_SALT + System.currentTimeMillis() + accountId + HASH_SALT);

        AccountInfo accountInfo = accountInfoMapper.getAccount(accountId);
        // 首次微信登录, 创建账号
        if (accountInfo == null) {
            accountInfo = new AccountInfo();
            accountInfo.setAccountId(accountId);
            accountInfo.setNickname(snsapiUserinfo.getNickname());
            accountInfo.setSex(Byte.valueOf(snsapiUserinfo.getSex()));
            accountInfo.setAvatar(snsapiUserinfo.getHeadimgurl());
            accountInfo.setState(new Byte("1"));
            if (accountInfoMapper.insertAccount(accountInfo) <= 0) {
                throw new RuntimeException("创建账号失败");
            }
        }

        // 更新token
        if (accountTokenMapper.insert(accountId, token, WECHAT_TYPE) <= 0) {
            throw new RuntimeException("登录失败");
        }

        return ResponseDTO.buildSuccess(token);
    }
}
