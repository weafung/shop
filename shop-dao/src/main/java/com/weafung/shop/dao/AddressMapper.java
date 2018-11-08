package com.weafung.shop.dao;

import com.weafung.shop.model.po.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    int deleteByAccountIdAndAddressId(@Param("accountId") String accountId, @Param("addressId") Long addressId);

    int insertSelective(Address record);

    List<Address> listByAccountId(@Param("accountId") String accountId);

    int updateByAddressIdSelective(@Param("record") Address record, @Param("addressId") Long addressId);

}