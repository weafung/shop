package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountToken;
import com.weafung.shop.model.po.AccountTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountTokenMapper {
    long countByExample(AccountTokenExample example);

    int deleteByExample(AccountTokenExample example);

    int insert(AccountToken record);

    int insertSelective(AccountToken record);

    List<AccountToken> selectByExample(AccountTokenExample example);

    int updateByExampleSelective(@Param("record") AccountToken record, @Param("example") AccountTokenExample example);

    int updateByExample(@Param("record") AccountToken record, @Param("example") AccountTokenExample example);
}