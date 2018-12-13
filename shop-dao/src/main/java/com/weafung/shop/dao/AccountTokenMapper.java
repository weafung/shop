package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountToken;
import org.apache.ibatis.annotations.Param;

public interface AccountTokenMapper {
    int insert(@Param("accountId") String accountId, @Param("token") String token, @Param("type") Integer type);

    AccountToken getAccountToken(@Param("token") String token);
}