package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.ShoppingCartDTO;

/**
 * @author weifengshih
 */
public interface ShoppingCartService {
    ResponseDTO<ShoppingCartDTO> getShoppingCart(String accountId);

    ResponseDTO<Boolean> addGoods(String accountId, Long goodsId, Long skuId, Integer count);

    ResponseDTO<Boolean> updateGoods(String accountId, Long skuId, Integer count);

    ResponseDTO<Boolean> deleteGoods(String accountId, Long skuId);
}
