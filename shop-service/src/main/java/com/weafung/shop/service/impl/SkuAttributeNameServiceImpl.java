package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeNameMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeNameDTO;
import com.weafung.shop.model.dto.SkuAttributeValueDTO;
import com.weafung.shop.model.po.SkuAttributeName;
import com.weafung.shop.model.po.SkuAttributeNameExample;
import com.weafung.shop.model.po.SkuAttributeValue;
import com.weafung.shop.service.SkuAttributeNameService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weifeng
 */
@Service
public class SkuAttributeNameServiceImpl implements SkuAttributeNameService {
    @Autowired
    private SkuAttributeNameMapper skuAttributeNameMapper;
    @Override
    public ResponseDTO<SkuAttributeNameDTO> getByAttributeNameId(Long attributeNameId) {
        SkuAttributeNameExample skuAttributeNameExample = new SkuAttributeNameExample();
        skuAttributeNameExample.createCriteria().andIsDeletedEqualTo(false)
                .andAttributeNameIdEqualTo(attributeNameId);
        List<SkuAttributeName> skuAttributeNameExampleList = skuAttributeNameMapper.selectByExample(skuAttributeNameExample);
        if (CollectionUtils.isEmpty(skuAttributeNameExampleList)) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_NAME_NOT_FOUND);
        }
        SkuAttributeName skuAttributeName = skuAttributeNameExampleList.get(0);
        SkuAttributeNameDTO skuAttributeNameDTO = new SkuAttributeNameDTO();
        BeanUtils.copyProperties(skuAttributeName, skuAttributeNameDTO);
        return ResponseDTO.buildSuccess(skuAttributeNameDTO);
    }
}
