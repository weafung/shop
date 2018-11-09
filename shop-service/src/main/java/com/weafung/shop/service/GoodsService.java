package com.weafung.shop.service;

import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsDTO;

import java.util.List;

/**
 * @author weifeng
 */
public interface GoodsService {
    ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId);

    ResponseDTO<Boolean> saveGoods(GoodsDTO goodsDTO);

    ResponseDTO<Boolean> updateGoods(GoodsDTO goodsDTO);

    SimpleGoodsDTO getSimpleGoodsByGoodsId(Long goodsId);

    SimpleGoodsDTO getSimpleGoodsBySkuId(Long skuId);

    ResponseDTO<List<SimpleGoodsDTO>> listGoodsByCategoryId(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId);
}
