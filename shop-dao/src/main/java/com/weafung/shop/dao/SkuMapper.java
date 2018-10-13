package com.weafung.shop.dao;

import com.weafung.shop.model.po.Sku;
import com.weafung.shop.model.po.SkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
    long countByExample(SkuExample example);

    int deleteByExample(SkuExample example);

    int insert(Sku record);

    int insertSelective(Sku record);

    List<Sku> selectByExample(SkuExample example);

    int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByExample(@Param("record") Sku record, @Param("example") SkuExample example);
}