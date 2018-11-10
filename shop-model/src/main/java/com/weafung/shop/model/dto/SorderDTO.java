package com.weafung.shop.model.dto;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class SorderDTO {
    private String gorderId;

    private String orderId;

    private Long goodsId;

    private Long skuId;

    private Integer count;

    private String title;

    private String introduce;

    private Long salePrice;

    private Long marketPrice;

    private Long money;

    private Byte status;

    private Long goodsDetailId;
}
