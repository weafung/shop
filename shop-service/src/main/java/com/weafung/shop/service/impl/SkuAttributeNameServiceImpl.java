package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeNameMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeNameDTO;
import com.weafung.shop.model.po.SkuAttributeName;
import com.weafung.shop.service.SkuAttributeNameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weifeng
 */
@Service
public class SkuAttributeNameServiceImpl implements SkuAttributeNameService {
    @Autowired
    private SkuAttributeNameMapper skuAttributeNameMapper;
    @Override
    public ResponseDTO<SkuAttributeNameDTO> getByAttributeNameId(Long attributeNameId) {
        SkuAttributeName skuAttributeName = skuAttributeNameMapper.getByAttributeNameId(attributeNameId);
        if (skuAttributeName == null) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_NAME_NOT_FOUND);
        }
        SkuAttributeNameDTO skuAttributeNameDTO = new SkuAttributeNameDTO();
        BeanUtils.copyProperties(skuAttributeName, skuAttributeNameDTO);
        return ResponseDTO.buildSuccess(skuAttributeNameDTO);
    }
}
