package com.weafung.shop.dao;

import com.weafung.shop.model.po.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<Goods> listGoodsByCategoryId(@Param("firstCategoryId") Long firstCategoryId,
                                      @Param("secondCategoryId") Long secondCategoryId,
                                      @Param("thirdCategoryId") Long thirdCategoryId);

    Goods getGoodsByGoodsId(@Param("goodsId") Long goodsId);

    Goods updateSelective(@Param("goods") Goods goods);

    Goods getGoodsBySkuId(@Param("skuId") Long skuId);

    List<Goods> listAdminGoods();

    int deleteGoods(@Param("goodsId") Long goodsId);
}