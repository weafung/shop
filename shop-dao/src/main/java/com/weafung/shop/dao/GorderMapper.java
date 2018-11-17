package com.weafung.shop.dao;

import com.weafung.shop.model.po.Gorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GorderMapper {
    int insert(@Param("gorderId") Long gorderId, @Param("accountId") String accountId,
               @Param("addressId") Long addressId, @Param("orderTime") Long orderTime);

    List<Gorder> listGorderPageByGorderIdAndStatus(@Param("accountId") String accountId,
                                                   @Param("gorderId") Long gorderId,
                                                   @Param("status") Integer status);

    int updateGorderStatus(@Param("accountId") String accountId, @Param("gorderId") Long gorderId,
                           @Param("status") Integer status);
}