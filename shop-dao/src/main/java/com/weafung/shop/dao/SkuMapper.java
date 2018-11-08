package com.weafung.shop.dao;

import com.weafung.shop.model.po.Sku;
import com.weafung.shop.model.po.SkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
    Sku selectBySkuId(@Param("skuId") Long skuId);

    int countBySkuId(@Param("skuId") Long skuId);

    Long selectMinSalePrice(@Param("goodsId") Long goodsId);

    List<Sku> listByGoodsId(@Param("goodsId") Long goodsId);
}