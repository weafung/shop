package com.weafung.shop.dao;

import org.apache.ibatis.annotations.Param;

public interface GorderMapper {
    int insert(@Param("gorderId") Long gorderId, @Param("accountId") String accountId,
               @Param("addressId") Long addressId, @Param("orderTime") Long orderTime);
}