package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuDTO;

import java.util.List;

public interface SkuService {
    /**
     * 根据商品ID获取商品的所有SKU
     * @param goodsId
     * @return
     */
    ResponseDTO<List<SkuDTO>> listSku(Long goodsId);

    SkuDTO getSkuDTOBySkuId(Long skuId);

    boolean checkSkuId(Long skuId);

    Long getMinSalePrice(Long goodsId);
}
