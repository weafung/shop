package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.common.util.EncryptUtil;
import com.weafung.shop.dao.AdminAccountMapper;
import com.weafung.shop.model.dto.AdminAccountDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.AdminAccount;
import com.weafung.shop.service.AdminAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author weifengshih
 */
@Service
public class AdminAccountServiceImpl implements AdminAccountService {
    @Autowired
    private AdminAccountMapper adminAccountMapper;

    private static final String HASH_SALT = "8v36yv789y4321yhg4g798rerytfe78943gb";

    @Override
    public ResponseDTO<AdminAccountDTO> getAdminAccount(String token) {
        AdminAccount adminAccount = adminAccountMapper.getByToken(token);
        if (adminAccount == null) {
            return ResponseDTO.build(CodeEnum.FORBIDDEN, null);
        }
        return ResponseDTO.buildSuccess(AdminAccountDTO.fromAdminAccount(adminAccount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<AdminAccountDTO> login(String username, String password) {
        username = StringUtils.trimToEmpty(username);
        password = StringUtils.trimToEmpty(password);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, null);
        }
        AdminAccount adminAccount = adminAccountMapper.getByUsername(username);
        if (adminAccount == null) {
            return ResponseDTO.build(CodeEnum.FORBIDDEN, null);
        }
        String encodePassword = EncryptUtil.md5(HASH_SALT + password + HASH_SALT);
        String correctPassword = adminAccount.getPassword();
        if (!Objects.equals(encodePassword, correctPassword)) {
            return ResponseDTO.build(CodeEnum.FORBIDDEN, null);
        }
        Long lastLogin = System.currentTimeMillis();
        String token = EncryptUtil.md5(HASH_SALT + username + password + lastLogin);
        boolean success = adminAccountMapper.login(username, token, lastLogin) > 0;
        if (success) {
            adminAccount.setToken(token);
            adminAccount.setLastLogin(lastLogin);
            return ResponseDTO.buildSuccess(AdminAccountDTO.fromAdminAccount(adminAccount));
        }
        return ResponseDTO.build(CodeEnum.LOGIN_FAIL);
    }

    public static void main(String[] args) {
        String password = "admin";
        System.out.println(EncryptUtil.md5(HASH_SALT + password + HASH_SALT));
    }
}
