package com.weafung.shop.service.impl;

import com.weafung.shop.dao.WebsiteConfigMapper;
import com.weafung.shop.model.po.WebsiteConfig;
import com.weafung.shop.model.po.WebsiteConfigExample;
import com.weafung.shop.service.WebsiteConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author weifeng
 */
@Service
public class WebsiteConfigServiceImpl implements WebsiteConfigService {
    @Resource
    WebsiteConfigMapper websiteConfigMapper;

    @Override
    public Map<String, String> listConfig(Set<String> keys) {
        return null;
    }

    @Override
    public String getConfigValue(String key) {
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key);
        List<WebsiteConfig> list = websiteConfigMapper.selectByExample(websiteConfigExample);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0).getConfigValue();
        }
        return null;
    }

    @Override
    public boolean insertOrUpdateConfig(String key, String value) {
        WebsiteConfig websiteConfig = new WebsiteConfig();
        websiteConfig.setConfigKey(key);
        websiteConfig.setConfigValue(value);
        if (getConfigValue(key) == null) {
            return websiteConfigMapper.insertSelective(websiteConfig) > 0;
        }
        WebsiteConfigExample websiteConfigExample = new WebsiteConfigExample();
        websiteConfigExample.createCriteria().andConfigKeyEqualTo(key);
        return websiteConfigMapper.updateByExampleSelective(websiteConfig, websiteConfigExample) > 0;
    }
}
