package com.weafung.shop.dao;

import com.weafung.shop.model.po.Category;
import com.weafung.shop.model.po.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);
}