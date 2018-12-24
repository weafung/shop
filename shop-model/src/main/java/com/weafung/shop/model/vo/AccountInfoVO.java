package com.weafung.shop.model.vo;

import com.weafung.shop.model.dto.AccountInfoDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class AccountInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountId;

    private String nickname;

    private Byte sex;

    private String avatar;

    private Byte state;

    public static AccountInfoVO fromDTO(AccountInfoDTO dto) {
        AccountInfoVO vo = new AccountInfoVO();
        vo.setAccountId(dto.getAccountId());
        vo.setNickname(dto.getNickname());
        vo.setAvatar(dto.getAvatar());
        vo.setSex(dto.getSex());
        vo.setState(dto.getState());
        return vo;
    }
}

