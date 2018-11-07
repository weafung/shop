package com.weafung.shop.service;

import com.weafung.shop.model.dto.CategoryDTO;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;

public interface CategoryService {
    /**
     * 获取商品类目
     * @param categoryId
     * @return
     */
    ResponseDTO<CategoryDTO> listCategories(Long categoryId);

    ResponseDTO<GoodsDTO> listGoods(Long categoryId);

}
