package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SorderDTO;

import java.util.List;

/**
 * @author weifengshih
 */
public interface OrderService {

    ResponseDTO<Boolean> createOrder(String accountId, Long gorderId, Long skuId, Integer count);

    List<SorderDTO> listSorderByGorderId(Long gorderId);
}
