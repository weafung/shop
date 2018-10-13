package com.weafung.shop.dao;

import com.weafung.shop.model.po.SkuAttributeName;
import com.weafung.shop.model.po.SkuAttributeNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuAttributeNameMapper {
    long countByExample(SkuAttributeNameExample example);

    int deleteByExample(SkuAttributeNameExample example);

    int insert(SkuAttributeName record);

    int insertSelective(SkuAttributeName record);

    List<SkuAttributeName> selectByExample(SkuAttributeNameExample example);

    int updateByExampleSelective(@Param("record") SkuAttributeName record, @Param("example") SkuAttributeNameExample example);

    int updateByExample(@Param("record") SkuAttributeName record, @Param("example") SkuAttributeNameExample example);
}