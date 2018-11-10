package com.weafung.shop.model.dto;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class GorderDTO {
    private Long gorderId;

    private String accountId;

    private Long addressId;

    private Byte status;

    private Long orderTime;

    private Long payTime;

    private Long confirmTime;
}
