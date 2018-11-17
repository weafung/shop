package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeName;
import org.apache.ibatis.annotations.Param;

public interface SkuAttributeNameMapper {
    SkuAttributeName getByAttributeNameId(@Param("attributeNameId") Long attributeNameId);

    int insert(@Param("attributeNameId") Long attributeNameId, @Param("attributeName") String name);
}