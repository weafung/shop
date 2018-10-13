package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShopGoods;
import com.weafung.shop.model.po.ShopGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopGoodsMapper {
    long countByExample(ShopGoodsExample example);

    int deleteByExample(ShopGoodsExample example);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    List<ShopGoods> selectByExample(ShopGoodsExample example);

    int updateByExampleSelective(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByExample(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);
}