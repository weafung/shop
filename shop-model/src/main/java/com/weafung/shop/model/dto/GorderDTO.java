package com.weafung.shop.model.dto;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class GorderDTO {
    private String gorderId;

    private String accountId;

    private Long addressId;

    private Integer status;

    private Long payTime;

    private Long confirmTime;
}
