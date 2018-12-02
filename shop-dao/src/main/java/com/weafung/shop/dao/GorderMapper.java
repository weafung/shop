package com.weafung.shop.dao;

import com.weafung.shop.model.po.Gorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GorderMapper {
    int insert(@Param("gorderId") Long gorderId, @Param("accountId") String accountId,
               @Param("addressId") Long addressId, @Param("orderTime") Long orderTime,
               @Param("province") String province, @Param("city") String city, @Param("district") String district,
               @Param("detail") String detail, @Param("name") String name, @Param("phone") String phone);

    List<Gorder> listGorderPageByGorderIdAndStatus(@Param("accountId") String accountId,
                                                   @Param("gorderId") Long gorderId,
                                                   @Param("status") Integer status);

    int updateGorderStatus(@Param("accountId") String accountId, @Param("gorderId") Long gorderId,
                           @Param("status") Integer status);

    int payGorder(@Param("accountId") String accountId, @Param("gorderId") Long gorderId, @Param("payTime") Long payTime);

    int receiveGorder(@Param("accountId") String accountId, @Param("gorderId") Long gorderId, @Param("confirmTime") Long confirmTime);


    List<Gorder> listGorder(@Param("gorderId") Long gorderId,
                            @Param("phone") String phone,
                            @Param("name") String name,
                            @Param("status") Integer status,
                            @Param("orderTimeStart") Long orderTimeStart, @Param("orderTimeEnd") Long orderTimeEnd,
                            @Param("payTimeStart") Long payTimeStart, @Param("payTimeEnd") Long payTimeEnd,
                            @Param("packageTimeStart") Long packageTimeStart, @Param("packageTimeEnd") Long packageTimeEnd,
                            @Param("confirmTimeStart") Long confirmTimeStart, @Param("confirmTimeEnd") Long confirmTimeEnd,
                            @Param("startGorderId") Long startGorderId, @Param("pageSize") Integer pageSize);

    int packageGorder(@Param("gorderId") Long gorderId, @Param("packageCode") String packageCode, @Param("packageTime") Long packageTime);

    int adminDelete(@Param("gorderId") Long gorderId);

    Gorder getGorder(@Param("gorderId") Long gorderId);

}