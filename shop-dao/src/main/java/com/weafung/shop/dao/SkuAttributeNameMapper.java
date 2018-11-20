package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuAttributeNameMapper {
    List<SkuAttributeName> listSku();

    SkuAttributeName getByAttributeNameId(@Param("attributeNameId") Long attributeNameId);

    int insert(@Param("attributeNameId") Long attributeNameId, @Param("attributeName") String attributeName);

    int update(@Param("attributeNameId") Long attributeNameId, @Param("attributeName") String attributeName);

    int deleteByAttributeNameId(@Param("attributeNameId") Long attributeNameId);
}