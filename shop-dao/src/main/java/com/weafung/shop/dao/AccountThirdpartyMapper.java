package com.weafung.shop.dao;

import com.weafung.shop.model.po.AccountThirdparty;
import com.weafung.shop.model.po.AccountThirdpartyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountThirdpartyMapper {
    long countByExample(AccountThirdpartyExample example);

    int deleteByExample(AccountThirdpartyExample example);

    int insert(AccountThirdparty record);

    int insertSelective(AccountThirdparty record);

    List<AccountThirdparty> selectByExample(AccountThirdpartyExample example);

    int updateByExampleSelective(@Param("record") AccountThirdparty record, @Param("example") AccountThirdpartyExample example);

    int updateByExample(@Param("record") AccountThirdparty record, @Param("example") AccountThirdpartyExample example);
}