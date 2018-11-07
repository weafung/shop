package com.weafung.shop.web.controller;

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
 * @author weifengshih
 */
@Controller
@Slf4j
@RequestMapping("/api/admin/config")
public class AdminConfigController {
    @Autowired
    private WebsiteConfigService websiteConfigService;

    @RequestMapping("/update")
    @ResponseBody
    public ResponseVO<Boolean> update(@RequestParam("key") String key, @RequestParam("value") String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return ResponseVO.build(CodeEnum.PARAM_EMPTY);
        }
        ResponseDTO<Boolean> responseDTO = websiteConfigService.insertOrUpdateConfig(key, value);
        if (responseDTO != null) {
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            } else {
                return ResponseVO.build(responseDTO.getCode(), responseDTO.getData(), responseDTO.getMsg());
            }
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
