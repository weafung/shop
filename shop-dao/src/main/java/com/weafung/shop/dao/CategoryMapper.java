package com.weafung.shop.dao;

import com.weafung.shop.model.po.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    List<Category> listByCategoryId(Long categoryId);

    List<Category> listByParentId(Long parentId);

    int insert(@Param("categoryId") Long categoryId, @Param("parentId") Long parentId,
               @Param("title") String title, @Param("image") String image,
               @Param("rank") Integer rank);

    int delete(@Param("categoryId") Long categoryId);

    int update(@Param("categoryId") Long categoryId,
               @Param("title") String title, @Param("image") String image,
               @Param("rank") Integer rank);
}