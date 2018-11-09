package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartMapper {
    List<ShoppingCart> selectByAccountId(@Param("accountId") String accountId);

    ShoppingCart selectByAccountIdAndSkuId(@Param("accountId")String accountId,
                                          @Param("skuId") Long skuId);

    int insert(@Param("accountId")String accountId, @Param("goodsId") Long goodsId, @Param("skuId") Long skuId,
               @Param("count") Integer count);

    int delete(@Param("accountId")String accountId, @Param("skuId") Long skuId);

    int update(@Param("accountId")String accountId, @Param("skuId") Long skuId, @Param("count") Integer count);

}