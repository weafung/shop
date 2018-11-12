package com.weafung.shop.model.dto;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class SimpleGoodsSkuDTO {
    private SimpleGoodsDTO goods;
    private SkuDTO sku;
}
