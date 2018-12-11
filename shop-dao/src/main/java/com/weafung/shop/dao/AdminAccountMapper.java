package com.weafung.shop.dao;

import com.weafung.shop.model.po.AdminAccount;
import org.apache.ibatis.annotations.Param;

/**
 * @author weifengshih
 */
public interface AdminAccountMapper {

    AdminAccount getByToken(@Param("token") String token);

    AdminAccount getByUsername(@Param("username") String username);

    int login(@Param("username") String username, @Param("token") String token, @Param("lastLogin") Long lastLogin);

}
