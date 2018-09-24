package com.weafung.shop.dao;

import com.weafung.shop.model.po.WebsiteConfig;
import com.weafung.shop.model.po.WebsiteConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WebsiteConfigMapper {
    long countByExample(WebsiteConfigExample example);

    int deleteByExample(WebsiteConfigExample example);

    int insert(WebsiteConfig record);

    int insertSelective(WebsiteConfig record);

    List<WebsiteConfig> selectByExample(WebsiteConfigExample example);

    int updateByExampleSelective(@Param("record") WebsiteConfig record, @Param("example") WebsiteConfigExample example);

    int updateByExample(@Param("record") WebsiteConfig record, @Param("example") WebsiteConfigExample example);
}