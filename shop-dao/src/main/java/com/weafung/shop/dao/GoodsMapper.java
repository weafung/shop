package com.weafung.shop.dao;

import com.weafung.shop.model.po.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<Goods> listGoodsByKeyword(@Param("firstCategoryId") Long firstCategoryId,
                                   @Param("secondCategoryId") Long secondCategoryId,
                                   @Param("thirdCategoryId") Long thirdCategoryId,
                                   @Param("title") String title);

    Goods getGoodsByGoodsId(@Param("goodsId") Long goodsId);

    int updateSelective(@Param("goods") Goods goods);

    int insertSelective(@Param("goods") Goods goods);

    Goods getGoodsBySkuId(@Param("skuId") Long skuId);

    List<Goods> listAdminGoods(@Param("goodsId") Long goodsId, @Param("title") String title);

    int deleteGoods(@Param("goodsId") Long goodsId);
}