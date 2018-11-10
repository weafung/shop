package com.weafung.shop.dao;

import com.weafung.shop.model.po.GoodsImage;

import java.util.List;

public interface GoodsImageMapper {
    int deleteByGoodsId(Long goodsId);

    String getImageUrlByGoodsId(Long goodsId);

    List<GoodsImage> listByGoodsId(Long goodsId);

    int insert(Long goodsId, String imageUrl);
}