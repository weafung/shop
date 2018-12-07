package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuAttributeNameMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SkuAttributeNameDTO;
import com.weafung.shop.model.po.SkuAttributeName;
import com.weafung.shop.service.SkuAttributeNameService;
import com.weafung.shop.service.SnowFlakeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifeng
 */
@Service
public class SkuAttributeNameServiceImpl implements SkuAttributeNameService {
    @Autowired
    private SkuAttributeNameMapper skuAttributeNameMapper;
    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
    @Cacheable(value = "skuAttributeNameCache", key = "#attributeNameId")
    public ResponseDTO<SkuAttributeNameDTO> getByAttributeNameId(Long attributeNameId) {
        SkuAttributeName skuAttributeName = skuAttributeNameMapper.getByAttributeNameId(attributeNameId);
        if (skuAttributeName == null) {
            return ResponseDTO.build(CodeEnum.SKU_ATTRIBUTE_NAME_NOT_FOUND);
        }
        SkuAttributeNameDTO skuAttributeNameDTO = new SkuAttributeNameDTO();
        BeanUtils.copyProperties(skuAttributeName, skuAttributeNameDTO);
        return ResponseDTO.buildSuccess(skuAttributeNameDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> addSkuAttributeName(String name) {
        if (StringUtils.isBlank(name)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Long attributeNameId = snowFlakeService.nextId(SkuAttributeName.class);
        boolean insertSuccess = skuAttributeNameMapper.insert(attributeNameId, name) > 0;
        if (insertSuccess) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "skuAttributeNameCache", key = "#attributeNameId")
    public ResponseDTO<Boolean> deleteByAttributeNameId(Long attributeNameId) {
        if (attributeNameId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        boolean success = skuAttributeNameMapper.deleteByAttributeNameId(attributeNameId) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @Override
    public ResponseDTO<List<SkuAttributeNameDTO>> listSkuAttributeName() {
        return ResponseDTO.buildSuccess(skuAttributeNameMapper.listSku().stream().map(skuAttributeName -> {
            SkuAttributeNameDTO skuAttributeNameDTO = new SkuAttributeNameDTO();
            BeanUtils.copyProperties(skuAttributeName, skuAttributeNameDTO);
            return skuAttributeNameDTO;
        }).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "skuAttributeNameCache", key = "#attributeNameId")
    public ResponseDTO<Boolean> updateByAttributeNameId(Long attributeNameId, String attributeName) {
        if (attributeNameId == null || StringUtils.isBlank(attributeName)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        boolean success = skuAttributeNameMapper.update(attributeNameId, attributeName) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
