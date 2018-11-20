package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuAttributeValueMapper {
    List<SkuAttributeValue> listSkuAttributeValue();

    List<SkuAttributeValue> listByAttributeNameId(@Param("attributeNameId") Long attributeNameId);

    List<SkuAttributeValue> listByAttributeValueId(@Param("attributeValueId") Long attributeValueId);

    int insert(@Param("attributeValueId") Long attributeValueId, @Param("attributeValue") String attributeValue,
               @Param("attributeNameId") Long attributeNameId);

    int update(@Param("attributeValueId") Long attributeValueId, @Param("attributeValue") String attributeValue);

    int deleteByAttributeValueId(@Param("attributeValueId") Long attributeValueId);
}