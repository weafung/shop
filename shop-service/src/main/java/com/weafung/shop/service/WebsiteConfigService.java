package com.weafung.shop.service;

import com.weafung.shop.model.dto.ResponseDTO;

import java.util.Map;
import java.util.Set;

public interface WebsiteConfigService {
    /**
     * 获取配置列表
     * @param keys
     * @return
     */
    ResponseDTO<Map<String, String>> listConfig(Set<String> keys);

    /**
     * 获取配置
     * @param key
     * @return
     */
    ResponseDTO<String> getConfigValue(String key);

    /**
     * 插入或更新配置
     * @param key
     * @param value
     * @return
     */
    ResponseDTO<Boolean> insertOrUpdateConfig(String key, String value);
}
