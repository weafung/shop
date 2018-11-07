package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weifengshih
 */
public interface ShoppingCartMapperEx {
    List<ShoppingCart> selectByAccountId(@Param("accountId") String accountId);

    ShoppingCart selectByAccountIdAndGoodsIdAndSkuId(@Param("accountId")String accountId,
                                                     @Param("goodsId") Long goodsId, @Param("skuId") Long skuId);

    int insert(@Param("accountId")String accountId, @Param("goodsId") Long goodsId, @Param("skuId") Long skuId,
               @Param("count") Integer count);

    int delete(@Param("accountId")String accountId, @Param("goodsId") Long goodsId, @Param("skuId") Long skuId);
}
