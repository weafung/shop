package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeValue;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuAttributeValueMapper {
    List<SkuAttributeValue> listByAttributeNameId(@Param("attributeNameId") Long attributeNameId);

    List<SkuAttributeValue> listByAttributeValueId(@Param("attributeValueId") Long attributeValueId);

}