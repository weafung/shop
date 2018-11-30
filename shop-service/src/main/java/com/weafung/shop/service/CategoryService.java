package com.weafung.shop.service;

import com.weafung.shop.model.dto.CategoryDetailDTO;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.ResponseDTO;

public interface CategoryService {
    /**
     * 获取商品类目
     * @param categoryId
     * @return
     */
    ResponseDTO<CategoryDetailDTO> listCategories(Long categoryId);

    ResponseDTO<GoodsDTO> listGoods(Long categoryId);

    ResponseDTO<Boolean> insertCategory(Long parentId, String title, String image, Integer rank);

    ResponseDTO<Boolean> updateCategory(Long categoryId, String title, String image, Integer rank);

    ResponseDTO<Boolean> deleteCategory(Long categoryId);
}
