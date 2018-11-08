package com.weafung.shop.dao;

import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    List<Goods> listGoodsByCategoryId(@Param("firstCategoryId") Long firstCategoryId,
                                      @Param("secondCategoryId") Long secondCategoryId,
                                      @Param("thirdCategoryId") Long thirdCategoryId);

    Goods getGoodsByGoodsId(@Param("goodsId") Long goodsId);

    Goods updateSelective(@Param("goods") Goods goods);
}