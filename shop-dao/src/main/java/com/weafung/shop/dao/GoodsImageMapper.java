package com.weafung.shop.dao;

import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.model.po.GoodsImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsImageMapper {
    int deleteByGoodsId(Long goodsId);

    String getImageUrlByGoodsId(Long goodsId);

    List<GoodsImage> listByGoodsId(Long goodsId);

    int insert(Long goodsId, String imageUrl);
}