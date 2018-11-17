package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeValueMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.service.SkuAttributeValueService;
import com.weafung.shop.service.SnowFlakeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weifeng
 */
@Service
@SuppressWarnings("Duplicate")
public class SkuAttributeValueServiceImpl implements SkuAttributeValueService {
    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;
    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
    public ResponseDTO<SkuAttributeValueDTO> getByAttributeValueId(Long attributeValueId) {
        List<SkuAttributeValue> skuAttributeValueList = skuAttributeValueMapper.listByAttributeValueId(attributeValueId);
        if (CollectionUtils.isEmpty(skuAttributeValueList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueList.get(0);
        SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
        BeanUtils.copyProperties(skuAttributeValue, skuAttributeValueDTO);
        return ResponseDTO.buildSuccess(skuAttributeValueDTO);
    }

    @Override
    public ResponseDTO<SkuAttributeValueDTO> getByAttributeNameId(Long attributeNameId) {
        List<SkuAttributeValue> skuAttributeValueList = skuAttributeValueMapper.listByAttributeNameId(attributeNameId);
        if (CollectionUtils.isEmpty(skuAttributeValueList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueList.get(0);
        SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
        BeanUtils.copyProperties(skuAttributeValue, skuAttributeValueDTO);
        return ResponseDTO.buildSuccess(skuAttributeValueDTO);
    }

    @Override
    public ResponseDTO<Boolean> addSkuAttributeValue(Long attributeNameId, String value) {
        if (StringUtils.isBlank(value)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Long attributeValueId = snowFlakeService.nextId(SkuAttributeValue.class);
        boolean insertSuccess = skuAttributeValueMapper.insert(attributeValueId, value, attributeNameId) > 0;
        if (insertSuccess) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
