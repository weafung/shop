package com.weafung.shop.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifeng
 */
@Data
public class GoodsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long goodsId;

    private List<SkuVO> skuList;

    private Long firstCategoryId;

    private Long secondCategoryId;

    private String title;

    private String introduce;

    private List<GoodsImageVO> goodsImageList;

    private Integer limitPerOrder;

    private Boolean freeDelivery;
}
