package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuDTO;
import com.weafung.shop.model.dto.SkuSpecDTO;
import com.weafung.shop.model.query.AdminUpdateSkuOfGoodsQuery;

import java.util.List;

public interface SkuService {
    /**
     * 根据商品ID获取商品的所有SKU
     * @param goodsId
     * @return
     */
    ResponseDTO<List<SkuDTO>> listSkuOfGoods(Long goodsId);

    ResponseDTO<Boolean> saveSkuOfGoods(AdminUpdateSkuOfGoodsQuery query);

    ResponseDTO<Boolean> updateSkuOfGoods(AdminUpdateSkuOfGoodsQuery query);

    ResponseDTO<Boolean> deleteSkuOfGoods(Long skuId);

    SkuDTO getSkuDTOBySkuId(Long skuId);

    boolean checkSkuId(Long skuId);

    Long getMinSalePrice(Long goodsId);

    ResponseDTO<List<SkuSpecDTO>> listSkuSpec();
}
