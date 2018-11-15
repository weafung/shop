package com.weafung.shop.service;

import com.weafung.shop.model.dto.GoodsImageDTO;

import java.util.List;

/**
 * @author weifengshih
 */
public interface GoodsImageService {
    List<GoodsImageDTO> listByGoodsId(Long goodsId);

    List<GoodsImageDTO> listBySkuId(Long skuId);
}
