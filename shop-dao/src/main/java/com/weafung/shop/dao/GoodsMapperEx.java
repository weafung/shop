package com.weafung.shop.dao;

import com.weafung.shop.model.po.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapperEx {
   List<Goods> listGoodsByCategoryId(@Param("firstCategoryId") Long firstCategoryId,
                                     @Param("secondCategoryId") Long secondCategoryId,
                                     @Param("thirdCategoryId") Long thirdCategoryId);
}