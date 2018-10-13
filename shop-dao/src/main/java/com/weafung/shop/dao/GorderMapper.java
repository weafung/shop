package com.weafung.shop.dao;

import com.weafung.shop.model.po.Gorder;
import com.weafung.shop.model.po.GorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GorderMapper {
    long countByExample(GorderExample example);

    int deleteByExample(GorderExample example);

    int insert(Gorder record);

    int insertSelective(Gorder record);

    List<Gorder> selectByExample(GorderExample example);

    int updateByExampleSelective(@Param("record") Gorder record, @Param("example") GorderExample example);

    int updateByExample(@Param("record") Gorder record, @Param("example") GorderExample example);
}