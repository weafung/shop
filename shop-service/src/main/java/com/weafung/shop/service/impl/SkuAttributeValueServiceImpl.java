package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeValueMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.model.po.SkuAttributeValueExample;
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
        SkuAttributeValueExample skuAttributeValueExample = new SkuAttributeValueExample();
        skuAttributeValueExample.createCriteria().andIsDeletedEqualTo(false)
                .andAttributeValueIdEqualTo(attributeValueId);
        List<SkuAttributeValue> skuAttributeValueExampleList = skuAttributeValueMapper.selectByExample(skuAttributeValueExample);
        if (CollectionUtils.isEmpty(skuAttributeValueExampleList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueExampleList.get(0);
        SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
        BeanUtils.copyProperties(skuAttributeValue, skuAttributeValueDTO);
        return ResponseDTO.buildSuccess(skuAttributeValueDTO);
    }

    @Override
    public ResponseDTO<SkuAttributeValueDTO> getByAttributeNameId(Long attributeNameId) {
        SkuAttributeValueExample skuAttributeValueExample = new SkuAttributeValueExample();
        skuAttributeValueExample.createCriteria().andIsDeletedEqualTo(false)
                .andAttributeNameIdEqualTo(attributeNameId);
        List<SkuAttributeValue> skuAttributeValueExampleList = skuAttributeValueMapper.selectByExample(skuAttributeValueExample);
        if (CollectionUtils.isEmpty(skuAttributeValueExampleList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_VALUE_NOT_FOUND);
        }
        SkuAttributeValue skuAttributeValue = skuAttributeValueExampleList.get(0);
        SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
        BeanUtils.copyProperties(skuAttributeValue, skuAttributeValueDTO);
        return ResponseDTO.buildSuccess(skuAttributeValueDTO);
    }
}
