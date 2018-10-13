package com.weafung.shop.model.vo;

import com.weafung.shop.model.dto.SkuAttributeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author weifeng
 */
@Data
public class SkuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private Long storeCount;

    private Long saleCount;

    private Long salePrice;

    private Long marketPrice;

    private Byte bonusRatio;

    private Boolean onsale;

    private Set<SkuAttributeDTO> attributes;
}
