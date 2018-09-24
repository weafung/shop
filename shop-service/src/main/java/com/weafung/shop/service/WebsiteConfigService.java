package com.weafung.shop.service;

import java.util.Map;
import java.util.Set;

public interface WebsiteConfigService {
    /**
     * 获取配置列表
     * @param keys
     * @return
     */
    Map<String, String> listConfig(Set<String> keys);

    /**
     * 获取配置
     * @param key
     * @return
     */
    String getConfigValue(String key);

    /**
     * 插入或更新配置
     * @param key
     * @param value
     * @return
     */
    boolean insertOrUpdateConfig(String key, String value);
}
