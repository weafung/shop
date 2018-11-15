package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifeng
 */
@Data
public class SkuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private Long goodsId;

    private Long storeCount;

    private Long saleCount;

    private Long salePrice;

    private Long marketPrice;

    private Byte bonusRatio;

    private Boolean hidden;

    private Boolean onsale;

    private List<GoodsImageDTO> skuImages;

    private List<SkuAttributeDTO> attributes;
}
