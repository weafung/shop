package com.weafung.shop.model.dto;

import com.weafung.shop.model.po.GoodsImage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifeng
 */
@Data
public class GoodsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long goodsId;

    private Long skuId;

    private Long firstCategoryId;

    private Long secondCategoryId;

    private String title;

    private String introduce;

    private List<GoodsImage> goodsImageList;

    private Integer limitPerOrder;

    private Boolean freeDelivery;
}
