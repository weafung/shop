package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class SimpleGoodsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long goodsId;

    private Long firstCategoryId;

    private Long secondCategoryId;

    private String title;

    private String introduce;

    private List<GoodsImageDTO> goodsImageList;

    private Integer limitPerOrder;

    private Boolean freeDelivery;
}
