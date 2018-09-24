package com.weafung.shop.dao;

import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.model.po.GoodsImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsImageMapper {
    long countByExample(GoodsImageExample example);

    int deleteByExample(GoodsImageExample example);

    int insert(GoodsImage record);

    int insertSelective(GoodsImage record);

    List<GoodsImage> selectByExample(GoodsImageExample example);

    int updateByExampleSelective(@Param("record") GoodsImage record, @Param("example") GoodsImageExample example);

    int updateByExample(@Param("record") GoodsImage record, @Param("example") GoodsImageExample example);
}