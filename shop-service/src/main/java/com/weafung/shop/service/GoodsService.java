package com.weafung.shop.service;

import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsDTO;

/**
 * @author weifeng
 */
public interface GoodsService {
    ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId);

    ResponseDTO<Boolean> saveGoods(GoodsDTO goodsDTO);

    ResponseDTO<Boolean> updateGoods(GoodsDTO goodsDTO);

    SimpleGoodsDTO getSimpleGoodsByGoodsId(Long goodsId);
}
