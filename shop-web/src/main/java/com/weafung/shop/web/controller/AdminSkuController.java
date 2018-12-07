package com.weafung.shop.web.controller;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeNameDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.dto.SkuSpecDTO;
import com.weafung.shop.model.vo.ResponseVO;
import com.weafung.shop.service.SkuAttributeNameService;
import com.weafung.shop.service.SkuAttributeValueService;
import com.weafung.shop.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/admin/sku")
@Slf4j
public class AdminSkuController {
    @Autowired
    private SkuService skuService;
    @Autowired
    private SkuAttributeNameService skuAttributeNameService;
    @Autowired
    private SkuAttributeValueService skuAttributeValueService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SkuSpecDTO>> listSkuSpec() {
        try {
            ResponseDTO<List<SkuSpecDTO>> responseDTO = skuService.listSkuSpec();
            if (responseDTO.getData() != null) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            }
            return ResponseVO.build(responseDTO.getCode(), Lists.newArrayList(), responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed list sku spec. ", e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SkuAttributeNameDTO>> listAttributeName() {
        ResponseDTO<List<SkuAttributeNameDTO>> responseDTO = null;
        try {
            responseDTO = skuAttributeNameService.listSkuAttributeName();
            if (responseDTO.getData() != null) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            }
            return ResponseVO.build(responseDTO.getCode(), Lists.newArrayList(), responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed list sku. responseDTO: {}", responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> addAttributeName(@RequestParam("attributeName") String attributeName) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeNameService.addSkuAttributeName(attributeName);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed add attribute name. name:{}, repsonseDTO:{}", attributeName, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeName", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> deleteName(@RequestParam("attributeNameId") Long attributeNameId) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeNameService.deleteByAttributeNameId(attributeNameId);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed delete attribute name. attributeNameId:{}, repsonseDTO:{}", attributeNameId, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeName", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateName(@RequestParam("attributeNameId") Long attributeNameId,
                                          @RequestParam("attributeName") String attributeName) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeNameService.updateByAttributeNameId(attributeNameId, attributeName);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed update attribute name. attributeNameId:{}, attributeName:{}, repsonseDTO:{}",
                    attributeNameId, attributeName, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeValue", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<SkuAttributeValueDTO>> listAttributeValue() {
        ResponseDTO<List<SkuAttributeValueDTO>> responseDTO = null;
        try {
            responseDTO = skuAttributeValueService.listSkuAttributeValue();
            if (responseDTO.getData() != null) {
                return ResponseVO.buildSuccess(responseDTO.getData());
            }
            return ResponseVO.build(responseDTO.getCode(), Lists.newArrayList(), responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed list sku attribute value. responseDTO: {}", responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeValue", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<Boolean> addAttributeValue(@RequestParam("attributeValue") String attributeValue,
                                                 @RequestParam("attributeNameId") Long attributeNameId) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeValueService.addSkuAttributeValue(attributeNameId, attributeValue);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed add attribute value. value:{}, attributeNameId:{}, repsonseDTO:{}",
                    attributeValue, attributeNameId, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeValue", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO<Boolean> deleteValue(@RequestParam("attributeValueId") Long attributeValueId) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeValueService.deleteByAttributeValueId(attributeValueId);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed delete attribute value. attributeValueId:{}, repsonseDTO:{}",
                    attributeValueId, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }

    @RequestMapping(value = "/attributeValue", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO<Boolean> updateValue(@RequestParam("attributeValueId") Long attributeValueId,
                                           @RequestParam("attributeValue") String attributeValue) {
        ResponseDTO<Boolean> responseDTO = null;
        try {
            responseDTO = skuAttributeValueService.updateByAttributeValueId(attributeValueId, attributeValue);
            if (responseDTO.getData()) {
                return ResponseVO.buildSuccess(Boolean.TRUE);
            }
            return ResponseVO.build(responseDTO.getCode(), Boolean.FALSE, responseDTO.getMsg());
        } catch (Exception e) {
            log.warn("failed update attribute value. attributeValueId:{}, attributeValue: {}repsonseDTO:{}",
                    attributeValueId, attributeValue, responseDTO, e);
        }
        return ResponseVO.build(CodeEnum.ERROR);
    }
}
