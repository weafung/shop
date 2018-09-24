package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShopIncomeDetail;
import com.weafung.shop.model.po.ShopIncomeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopIncomeDetailMapper {
    long countByExample(ShopIncomeDetailExample example);

    int deleteByExample(ShopIncomeDetailExample example);

    int insert(ShopIncomeDetail record);

    int insertSelective(ShopIncomeDetail record);

    List<ShopIncomeDetail> selectByExample(ShopIncomeDetailExample example);

    int updateByExampleSelective(@Param("record") ShopIncomeDetail record, @Param("example") ShopIncomeDetailExample example);

    int updateByExample(@Param("record") ShopIncomeDetail record, @Param("example") ShopIncomeDetailExample example);
}