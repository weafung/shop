package com.weafung.shop.model.dto;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class AdminGoodsDTO {
    private Long goodsId;
    private Long firstCategoryId;
    private Long secondCategoryId;
    private Long thirdCategoryId;
    private String firstCategory;
    private String secondCategory;
    private String thirdCategory;
    private String title;
    private String introduce;
    private Boolean hide;
}
