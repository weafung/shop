package com.weafung.shop.web.controller;

import com.google.common.collect.Sets;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.query.AdminUpdateConfigQuery;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.WebsiteConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/config")
@Slf4j
public class ConfigController {
    @Autowired
    private WebsiteConfigService websiteConfigService;

    @RequestMapping("/get")
    @ResponseBody
    public ResponseVO<String> get(@RequestParam("key") String key) {
        if (StringUtils.isBlank(key)) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<String> responseDTO = websiteConfigService.getConfigValue(key);
        if (responseDTO != null) {
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            } else {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseVO<Map<String, String>> list(@RequestBody List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Map<String, String>> responseDTO = websiteConfigService.listConfig(Sets.newHashSet(keys));
        if (responseDTO != null) {
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            } else {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> update(@RequestBody AdminUpdateConfigQuery adminUpdateConfigQuery) {
        try {
            ResponseDTO<Boolean> responseDTO = websiteConfigService.insertOrUpdateConfig(adminUpdateConfigQuery.getKey(), adminUpdateConfigQuery.getValue());
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.error("failed to update config");
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
