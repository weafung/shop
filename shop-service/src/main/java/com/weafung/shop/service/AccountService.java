package com.weafung.shop.service;

import com.weafung.shop.common.util.wechat.SnsapiUserinfo;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.dto.ResponseDTO;

import java.util.List;

/**
 * @author weifengshih
 */
public interface AccountService {
    ResponseDTO<AccountInfoDTO> getAccountInfoByToken(String token);

    ResponseDTO<String> updateOrInsertAccountInfo(SnsapiUserinfo snsapiUserinfo);

    ResponseDTO<List<AccountInfoDTO>> listAccountInfo();
}
