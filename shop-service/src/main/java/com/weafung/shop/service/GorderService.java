package com.weafung.shop.service;


import com.weafung.shop.model.dto.OrderItemDTO;
import com.weafung.shop.model.dto.ResponseDTO;

import java.util.Set;

/**
 * @author weifengshih
 */
public interface GorderService {

    ResponseDTO<Boolean> checkOut(String accountId, Long addressId, Set<OrderItemDTO> orderItemDTOSet);
}
