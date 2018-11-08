package com.weafung.shop.dao;

import com.weafung.shop.model.po.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> listByCategoryId(Long categoryId);
}