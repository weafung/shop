package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeValueMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.service.SkuAttributeNameService;
import com.weafung.shop.service.SkuAttributeValueService;
import com.weafung.shop.service.SnowFlakeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifeng
 */
@Service
@SuppressWarnings("Duplicate")
public class SkuAttributeValueServiceImpl implements SkuAttributeValueService {
    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;
    @Autowired
    private SkuAttributeNameService skuAttributeNameService;
    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
    public ResponseDTO<SkuAttributeValueDTO> getByAttributeValueId(Long attributeValueId) {
        List<SkuAttributeValue> skuAttributeValueList = skuAttributeValueMapper.listByAttributeValueId(attributeValueId);
        if (CollectionUtils.isEmpty(skuAttributeValueList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueList.get(0);
        return ResponseDTO.buildSuccess(skuAttributeValue2DTO(skuAttributeValue));
    }

    @Override
    public ResponseDTO<SkuAttributeValueDTO> getByAttributeNameId(Long attributeNameId) {
        List<SkuAttributeValue> skuAttributeValueList = skuAttributeValueMapper.listByAttributeNameId(attributeNameId);
        if (CollectionUtils.isEmpty(skuAttributeValueList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueList.get(0);
        return ResponseDTO.buildSuccess(skuAttributeValue2DTO(skuAttributeValue));
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

    @Override
    public ResponseDTO<List<SkuAttributeValueDTO>> listSkuAttributeValue() {
        return ResponseDTO.buildSuccess(skuAttributeValueMapper.listSkuAttributeValue().stream().map(skuAttributeValue -> {
            SkuAttributeValueDTO skuAttributeValueDTO = skuAttributeValue2DTO(skuAttributeValue);
            skuAttributeValueDTO.setAttributeName(
                    skuAttributeNameService.getByAttributeNameId(skuAttributeValueDTO.getAttributeNameId())
                            .getData().getAttributeName());
            return skuAttributeValueDTO;
        }).collect(Collectors.toList()));

    }

    private SkuAttributeValueDTO skuAttributeValue2DTO(SkuAttributeValue skuAttributeValue) {
        if (skuAttributeValue == null) {
            return null;
        }
        SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
        BeanUtils.copyProperties(skuAttributeValue, skuAttributeValueDTO);
        return skuAttributeValueDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> deleteByAttributeValueId(Long attributeValueId) {
        if (attributeValueId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        boolean success = skuAttributeValueMapper.deleteByAttributeValueId(attributeValueId) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);

    }

    @Override
    public ResponseDTO<Boolean> updateByAttributeValueId(Long attributeValueId, String attributeValue) {
        if (attributeValueId == null || StringUtils.isBlank(attributeValue)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        boolean success = skuAttributeValueMapper.update(attributeValueId, attributeValue) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
