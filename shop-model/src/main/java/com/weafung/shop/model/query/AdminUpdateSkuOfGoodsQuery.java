package com.weafung.shop.model.query;

import com.weafung.shop.model.dto.SkuAttributeDTO;
import lombok.Data;

import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class AdminUpdateSkuOfGoodsQuery {
    private Long skuId;
    private Long goodsId;
    private List<SkuAttributeDTO> attributes;
    private Long storeCount;
    private Long salePrice;
    private Long marketPrice;
    private String image;
    private Byte bonusRatio;
    private Boolean hidden;
    private Boolean onsale;
}
