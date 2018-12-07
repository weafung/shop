package com.weafung.shop.model.query;

import lombok.Data;

import java.util.List;

/**
 * @author weifengshih
 */
@Data
public class AdminUpdateImagesOfGoodsQuery {
    private Long goodsId;
    private List<String> imageUrls;
}
