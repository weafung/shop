package com.weafung.shop.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Sorder implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;

    private Long gorderId;

    private Long orderId;

    private Long goodsId;

    private Long skuId;

    private Integer count;

    private String title;

    private String introduce;

    private Long salePrice;

    private Long marketPrice;

    private Long money;

    private Integer status;

    private Long goodsDetailId;

    private static final long serialVersionUID = 1L;

}