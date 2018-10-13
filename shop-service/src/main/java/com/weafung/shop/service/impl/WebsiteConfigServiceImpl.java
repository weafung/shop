package com.weafung.shop.service.impl;

import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.WebsiteConfigMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.po.WebsiteConfig;
import com.weafung.shop.model.po.WebsiteConfigExample;
import com.weafung.shop.service.WebsiteConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    @Cacheable(value = "websiteConfigCache", key = "#keys")
    public ResponseDTO<Map<String, String>> listConfig(Set<String> keys) {
        Map<String, String> configMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(keys)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, configMap);
        }
        keys.forEach(key -> {
            WebsiteConfig websiteConfig = getWebsiteConfigByKey(key);
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
        WebsiteConfig websiteConfig = getWebsiteConfigByKey(key);
        if (websiteConfig != null) {
            return ResponseDTO.buildSuccess(websiteConfig.getConfigValue());
        }
        return ResponseDTO.build(CodeEnum.WEBSITE_CONFIG_NOT_FOUND, null);
    }

    @Override
    @CacheEvict(value = "websiteConfigCache", key = "#key")
    public ResponseDTO<Boolean>  insertOrUpdateConfig(String key, String value) {
        WebsiteConfig websiteConfig = new WebsiteConfig();
        websiteConfig.setConfigKey(key);
        websiteConfig.setConfigValue(value);
        if (getConfigValue(key) == null) {
            if (websiteConfigMapper.insertSelective(websiteConfig) > 0) {
                return ResponseDTO.buildSuccess(Boolean.TRUE);
            } else {
                log.warn("insert new config key and value failed. key:{}, value:{}", key, value);
                return ResponseDTO.build(CodeEnum.ERROR, false);
            }
        }
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key);
        boolean updateResult = websiteConfigMapper.updateByExampleSelective(websiteConfig, websiteConfigExample) > 0;
        if (updateResult) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        } else {
            log.warn("update new config key and value failed. key:{}, value:{}", key, value);
            return ResponseDTO.build(CodeEnum.ERROR, false);
        }
    }

    @Cacheable(value = "websiteConfigCache", key = "#key")
    public WebsiteConfig getWebsiteConfigByKey(String key) {
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key).andIsDeletedEqualTo(false);
        List<WebsiteConfig> list = websiteConfigMapper.selectByExample(websiteConfigExample);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
