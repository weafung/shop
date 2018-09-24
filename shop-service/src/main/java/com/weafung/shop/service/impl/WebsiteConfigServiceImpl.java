package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeConstant;
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
    public Map<String, String> listConfig(Set<String> keys) {
        return null;
    }

    @Override
    @Cacheable(value = "websiteConfigCache", key = "#key")
    public ResponseDTO<String> getConfigValue(String key) {
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key);
        List<WebsiteConfig> list = websiteConfigMapper.selectByExample(websiteConfigExample);
        if (CollectionUtils.isNotEmpty(list)) {
            return ResponseDTO.buildSuccess(list.get(0).getConfigValue());
        }
        return ResponseDTO.buildFailure(CodeConstant.NOT_FOUND, null, "Config Key Not Found");
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
                return ResponseDTO.buildFailure(CodeConstant.ERROR, false, "更新失败");
            }
        }
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key);
        boolean updateResult = websiteConfigMapper.updateByExampleSelective(websiteConfig, websiteConfigExample) > 0;
        if (updateResult) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        } else {
            log.warn("update new config key and value failed. key:{}, value:{}", key, value);
            return ResponseDTO.buildFailure(CodeConstant.ERROR, false, "更新失败");
        }
    }
}
