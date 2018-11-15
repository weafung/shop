package com.weafung.shop.model.dto;

import com.weafung.shop.model.po.GoodsImage;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weifeng
 */
@Data
public class GoodsImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imageUrl;

    public static GoodsImageDTO fromGoodsImage(GoodsImage goodsImage) {
        GoodsImageDTO goodsImageDTO = new GoodsImageDTO();
        goodsImageDTO.setImageUrl(goodsImage.getImageUrl());
        return goodsImageDTO;
    }
}
