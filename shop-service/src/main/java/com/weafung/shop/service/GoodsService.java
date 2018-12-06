package com.weafung.shop.service;

import com.weafung.shop.model.dto.*;

import java.util.List;
import java.util.Map;

/**
 * @author weifeng
 */
public interface GoodsService {
    ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId);

    ResponseDTO<Boolean> saveGoods(GoodsDTO goodsDTO);

    ResponseDTO<Boolean> updateGoods(GoodsDTO goodsDTO);

    ResponseDTO<Boolean> deleteGoods(Long goodsId);


    SimpleGoodsDTO getSimpleGoodsByGoodsId(Long goodsId);

    SimpleGoodsDTO getSimpleGoodsBySkuId(Long skuId);

    ResponseDTO<List<SimpleGoodsDTO>> listGoodsByCategoryId(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId);

    SimpleGoodsSkuDTO getGoodsSku(Long skuId);

    ResponseDTO<Map<Long, SimpleGoodsSkuDTO>> listGoodsSku(List<Long> skuIdList);

    ResponseDTO<List<AdminGoodsDTO>> listAdminGoods();
}
