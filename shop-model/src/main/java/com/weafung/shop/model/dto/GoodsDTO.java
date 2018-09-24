package com.weafung.shop.model.dto;

import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsImage;
import lombok.Data;

import java.util.List;

/**
 * @author weifeng
 */
@Data
public class GoodsDTO extends Goods {
    private List<GoodsImage> goodsImageList;
}
