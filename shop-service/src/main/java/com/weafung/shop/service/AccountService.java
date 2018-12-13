package com.weafung.shop.service;

import com.weafung.shop.common.util.wechat.SnsapiUserinfo;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.dto.ResponseDTO;

/**
 * @author weifengshih
 */
public interface AccountService {
    ResponseDTO<AccountInfoDTO> getAccountInfoByToken(String token);

    ResponseDTO<String> updateOrInsertAccountInfo(SnsapiUserinfo snsapiUserinfo);
}
