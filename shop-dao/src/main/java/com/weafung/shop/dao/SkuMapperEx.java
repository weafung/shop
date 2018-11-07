package com.weafung.shop.dao;

import com.weafung.shop.model.po.Sku;
import com.weafung.shop.model.po.SkuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapperEx {
    Sku selectBySkuId(Long skuId);
}