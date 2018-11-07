package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weifengshih
 */
public interface ShoppingCartMapperEx {
    List<ShoppingCart> selectByAccountId(@Param("accountId") String accountId);
}
