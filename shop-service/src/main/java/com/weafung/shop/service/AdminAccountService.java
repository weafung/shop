package com.weafung.shop.service;

import com.weafung.shop.model.dto.AdminAccountDTO;
import com.weafung.shop.model.dto.ResponseDTO;

/**
 * @author weifengshih
 */
public interface AdminAccountService {
    /**
     * 检查token是否有效
     * @param token
     * @return 返回相应的管理员用户
     */
    ResponseDTO<AdminAccountDTO> getAdminAccount(String token);

    /**
     * 管理员登录接口
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回账号
     */
    ResponseDTO<AdminAccountDTO> login(String username, String password);
}
