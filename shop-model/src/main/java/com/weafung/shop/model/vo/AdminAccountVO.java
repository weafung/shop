package com.weafung.shop.model.vo;

import com.weafung.shop.model.dto.AdminAccountDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class AdminAccountVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String owner;

    private String token;

    private Long lastLogin;

    public static AdminAccountVO fromAdminAccountDTO(AdminAccountDTO adminAccountDTO) {
        if (adminAccountDTO == null) {
            return null;
        }
        AdminAccountVO adminAccountVO = new AdminAccountVO();
        adminAccountVO.setUsername(adminAccountDTO.getUsername());
        adminAccountVO.setOwner(adminAccountDTO.getOwner());
        adminAccountVO.setToken(adminAccountDTO.getToken());
        adminAccountVO.setLastLogin(adminAccountDTO.getLastLogin());
        return adminAccountVO;
    }
}