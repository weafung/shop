package com.weafung.shop.dao;

import com.weafung.shop.model.po.WebsiteConfig;

import org.apache.ibatis.annotations.Param;

public interface WebsiteConfigMapper {
    int deleteByKey(@Param("configKey") String configKey);

    int insert(@Param("configKey") String configKey, @Param("configValue") String configValue);

    int updateByKey(@Param("configKey") String configKey, @Param("configValue") String configValue);

    WebsiteConfig selectByKey(@Param("configKey") String configKey);
}