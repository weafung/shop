package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountInfo;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
   AccountInfo getAccount(@Param("accountId") String accountId);

   int insertAccount(@Param("accountInfo") AccountInfo accountInfo);
}