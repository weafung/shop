package com.weafung.shop.web.controller;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.SkuAttributeNameService;
import com.weafung.shop.service.SkuAttributeValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/admin/sku")
@Slf4j
public class AdminSkuController {
    @Autowired
    private SkuAttributeNameService skuAttributeNameService;
    @Autowired
    private SkuAttributeValueService skuAttributeValueService;

    @RequestMapping(value = "/attributeName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> addAttributeName(@RequestParam("name") String name) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeNameService.addSkuAttributeName(name);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed add attribute name. name:{}, repsonseDTO:{}",name, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeValue", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> addAttributeValue(@RequestParam("value") String value,
                                                 @RequestParam("attributeNameId") Long attributeNameId) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeValueService.addSkuAttributeValue(attributeNameId, value);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed add attribute value. value:{}, attributeNameId:{}, repsonseDTO:{}",
                    value, attributeNameId, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
