package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeConstant;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.WebsiteConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/api/config")
@Slf4j
public class WebsiteConfigController {
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
            if (Objects.equals(responseDTO.getCode(), CodeConstant.SUCCESS)) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            } else {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseVO<Boolean> update(@RequestParam("key") String key, @RequestParam("value") String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Boolean> responseDTO = websiteConfigService.insertOrUpdateConfig(key, value);
        if (responseDTO != null) {
            if (Objects.equals(responseDTO.getCode(), CodeConstant.SUCCESS)) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            } else {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
