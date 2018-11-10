package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;

/**
 * @author weifengshih
 */
public interface OrderService {

    ResponseDTO<Boolean> createOrder(String accountId, Long gorderId, Long skuId, Integer count);
}
