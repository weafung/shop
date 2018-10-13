package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShoppingCart;
import com.weafung.shop.model.po.ShoppingCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingCartMapper {
    long countByExample(ShoppingCartExample example);

    int deleteByExample(ShoppingCartExample example);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);
}