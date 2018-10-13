package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.model.po.SkuAttributeValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuAttributeValueMapper {
    long countByExample(SkuAttributeValueExample example);

    int deleteByExample(SkuAttributeValueExample example);

    int insert(SkuAttributeValue record);

    int insertSelective(SkuAttributeValue record);

    List<SkuAttributeValue> selectByExample(SkuAttributeValueExample example);

    int updateByExampleSelective(@Param("record") SkuAttributeValue record, @Param("example") SkuAttributeValueExample example);

    int updateByExample(@Param("record") SkuAttributeValue record, @Param("example") SkuAttributeValueExample example);
}