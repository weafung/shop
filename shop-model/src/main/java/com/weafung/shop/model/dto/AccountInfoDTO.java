package com.weafung.shop.model.dto;

import com.weafung.shop.model.po.AccountInfo;
import lombok.Data;

/**
 * @author weifengshih
 */

@Data
public class AccountInfoDTO {

    private String accountId;

    private String nickname;

    private Byte sex;

    private String avatar;

    private Byte state;

    public static AccountInfoDTO fromAccountInfo(AccountInfo accountInfo) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setAccountId(accountInfo.getAccountId());
        accountInfoDTO.setNickname(accountInfo.getNickname());
        accountInfoDTO.setAvatar(accountInfo.getAvatar());
        accountInfoDTO.setSex(accountInfo.getSex());
        accountInfoDTO.setState(accountInfo.getState());
        return accountInfoDTO;
    }
}
