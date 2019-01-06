package com.weafung.shop.service.impl;

import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.WebsiteConfigMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.WebsiteConfig;
import com.weafung.shop.service.WebsiteConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author weifeng
 */
@Service
@Slf4j
public class WebsiteConfigServiceImpl implements WebsiteConfigService {
    @Resource
    private WebsiteConfigMapper websiteConfigMapper;

    @Override
    @Cacheable(value = "websiteConfigCache", key = "0")
    public ResponseDTO<Map<String, String>> listConfig(Set<String> keys) {
        Map<String, String> configMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(keys)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, configMap);
        }
        keys.forEach(key -> {
            WebsiteConfig websiteConfig = websiteConfigMapper.selectByKey(key);
            if (websiteConfig != null) {
                configMap.put(key, websiteConfig.getConfigValue());
            } else {
                configMap.put(key, "");
            }
        });
        return ResponseDTO.buildSuccess(configMap);
    }

    @Override
    @Cacheable(value = "websiteConfigCache", key = "#key")
    public ResponseDTO<String> getConfigValue(String key) {
        WebsiteConfig websiteConfig = websiteConfigMapper.selectByKey(key);
        if (websiteConfig != null) {
            return ResponseDTO.buildSuccess(websiteConfig.getConfigValue());
        }
        return ResponseDTO.build(CodeEnum.WEBSITE_CONFIG_NOT_FOUND, null);
    }

    @Override

    @Caching(evict = {@CacheEvict(value = "websiteConfigCache", key = "#key"),
            @CacheEvict(value = "websiteConfigCache", key = "0")})
    public ResponseDTO<Boolean> insertOrUpdateConfig(String key, String value) {
        if (websiteConfigMapper.selectByKey(key) == null) {
            if (websiteConfigMapper.insert(key, value) > 0) {
                return ResponseDTO.buildSuccess(Boolean.TRUE);
            } else {
                log.warn("insert new config key and value failed. key:{}, value:{}", key, value);
                return ResponseDTO.build(CodeEnum.ERROR, false);
            }
        }
        boolean updateSuccess = websiteConfigMapper.updateByKey(key, value) > 0;
        if (updateSuccess) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        } else {
            log.warn("update new config key and value failed. key:{}, value:{}", key, value);
            return ResponseDTO.build(CodeEnum.ERROR, false);
        }
    }
}
