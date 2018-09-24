package com.weafung.shop.web.controller;

import com.weafung.shop.service.WebsiteConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/config")
@Slf4j
public class WebsiteConfigController {
    @Resource
    private WebsiteConfigService websiteConfigService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("key") String key) {
        return websiteConfigService.getConfigValue(key);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Boolean add(@RequestParam("key") String key, @RequestParam("value") String value) {
        return websiteConfigService.insertOrUpdateConfig(key, value);
    }
}
