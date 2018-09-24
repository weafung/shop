package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountInfo;
import com.weafung.shop.model.po.AccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    long countByExample(AccountInfoExample example);

    int deleteByExample(AccountInfoExample example);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    List<AccountInfo> selectByExample(AccountInfoExample example);

    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);
}