package com.weafung.shop.dao;

import com.weafung.shop.model.po.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapper {
    Sku selectBySkuId(@Param("skuId") Long skuId);

    int countBySkuId(@Param("skuId") Long skuId);

    int deleteBySkuId(@Param("skuId") Long skuId);

    Long selectMinSalePrice(@Param("goodsId") Long goodsId);

    List<Sku> listByGoodsId(@Param("goodsId") Long goodsId);

    int updateBySkuId(@Param("sku") Sku sku);

    int insert(@Param("sku") Sku sku);
}