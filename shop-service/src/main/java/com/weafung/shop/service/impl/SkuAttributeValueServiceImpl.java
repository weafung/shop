package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeValueMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.service.SkuAttributeValueService;
import org.apache.commons.collections4.CollectionUtils;
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
}
