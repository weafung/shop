package com.weafung.shop.dao;

import com.weafung.shop.model.po.Sorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int insert(Sorder record);

    List<Sorder> listOrderByGorderId(@Param("gorderId") Long gorderId);
}