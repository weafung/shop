package com.weafung.shop.service;

import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.query.AdminGoodsQuery;
import com.weafung.shop.model.query.AdminUpdateGoodsQuery;

import java.util.List;
import java.util.Map;

/**
 * @author weifeng
 */
public interface GoodsService {
    ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId);

    SimpleGoodsDTO getSimpleGoodsByGoodsId(Long goodsId);

    SimpleGoodsDTO getSimpleGoodsBySkuId(Long skuId);

    ResponseDTO<List<SimpleGoodsDTO>> listGoodsByCategoryId(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId);

    SimpleGoodsSkuDTO getGoodsSku(Long skuId);

    ResponseDTO<Map<Long, SimpleGoodsSkuDTO>> listGoodsSku(List<Long> skuIdList);

    ResponseDTO<List<AdminGoodsDTO>> listGoodsForAdministrator(AdminGoodsQuery query);

    ResponseDTO<List<String>> listImageOfGoods(Long goodsId);

    ResponseDTO<Boolean> saveGoods(AdminUpdateGoodsQuery query);

    ResponseDTO<Boolean> updateGoods(AdminUpdateGoodsQuery query);

    ResponseDTO<Boolean> deleteGoods(Long goodsId);

    ResponseDTO<Boolean> updateImagesOfGoods(Long goodsId, List<String> imageUrls);
}
