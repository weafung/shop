package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author weifengshih
 */
@Data
public class ShoppingCartDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleGoodsDTO goods;

    private SkuDTO sku;

    private Integer count;
}
