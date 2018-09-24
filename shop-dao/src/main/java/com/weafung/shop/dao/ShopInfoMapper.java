package com.weafung.shop.dao;

import com.weafung.shop.model.po.ShopInfo;
import com.weafung.shop.model.po.ShopInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopInfoMapper {
    long countByExample(ShopInfoExample example);

    int deleteByExample(ShopInfoExample example);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    List<ShopInfo> selectByExample(ShopInfoExample example);

    int updateByExampleSelective(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);

    int updateByExample(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);
}