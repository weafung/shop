package com.weafung.shop.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author weifengshih
 */
@Data
public class CheckOutDTO {
    private Long addressId;

    private Set<OrderItemDTO> orderItems;
}
