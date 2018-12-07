package com.weafung.shop.dao;

import com.weafung.shop.model.po.GoodsImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsImageMapper {
    int deleteByGoodsId(@Param("goodsId")Long goodsId);

    String getImageUrlByGoodsId(@Param("goodsId")Long goodsId);

    List<GoodsImage> listByGoodsId(@Param("goodsId")Long goodsId);

    List<GoodsImage> listBySkuId(@Param("skuId")Long skuId);

    int insert(@Param("goodsId") Long goodsId, @Param("imageUrl") String imageUrl);
}