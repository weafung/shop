package com.weafung.shop.model.query;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class AdminUpdateGoodsQuery {
    private Long goodsId;
    private String title;
    private String introduce;
    private Long firstCategoryId;
    private Long secondCategoryId;
    private Long thirdCategoryId;
    private Integer limitPerOrder;
}
