package com.weafung.shop.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class SorderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long gorderId;

    private Long orderId;

    private Long goodsId;

    private Long skuId;

    private String skuImage;

    private List<SkuAttributeDTO> attributes;

    private Integer count;

    private String title;

    private String introduce;

    private Long salePrice;

    private Long marketPrice;

    private Long money;

    private Byte status;

    private Long goodsDetailId;
}
