package com.weafung.shop.model.dto;

import com.weafung.shop.model.po.AdminAccount;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class AdminAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private String owner;

    private String password;

    private String token;

    private Long lastLogin;

    public static AdminAccountDTO fromAdminAccount(AdminAccount adminAccount) {
        if (adminAccount == null) {
            return null;
        }
        AdminAccountDTO adminAccountDTO = new AdminAccountDTO();
        adminAccountDTO.setUsername(adminAccount.getUsername());
        adminAccountDTO.setPassword(adminAccount.getPassword());
        adminAccountDTO.setOwner(adminAccount.getOwner());
        adminAccountDTO.setToken(adminAccount.getToken());
        adminAccountDTO.setLastLogin(adminAccount.getLastLogin());
        return adminAccountDTO;
    }
}
