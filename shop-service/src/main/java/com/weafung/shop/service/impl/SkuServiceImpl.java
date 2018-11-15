package com.weafung.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuMapper;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.Sku;
import com.weafung.shop.service.GoodsImageService;
import com.weafung.shop.service.SkuAttributeNameService;
import com.weafung.shop.service.SkuAttributeValueService;
import com.weafung.shop.service.SkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weifeng
 */
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuAttributeNameService skuAttributeNameService;

    @Autowired
    private SkuAttributeValueService skuAttributeValueService;
    @Autowired
    private GoodsImageService goodsImageService;

    @Override
    public ResponseDTO<List<SkuDTO>> listSku(Long goodsId) {
        if (goodsId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        List<Sku> skuList = skuMapper.listByGoodsId(goodsId);
        if (CollectionUtils.isEmpty(skuList)) {
            return ResponseDTO.buildSuccess(Lists.newArrayList());
        }
        List<SkuDTO> skuDTOList = skuList.stream().filter(sku -> StringUtils.isNotBlank(sku.getAttribute()))
                .map(this::sku2SkuDTO)
                .collect(Collectors.toList());
        return ResponseDTO.buildSuccess(skuDTOList);
    }

    @Override
    public SkuDTO getSkuDTOBySkuId(Long skuId) {
        if (skuId ==  null) {
            return null;
        }
        Sku sku = skuMapper.selectBySkuId(skuId);
        if (sku == null) {
            return null;
        }
        return sku2SkuDTO(sku);
    }

    private SkuDTO sku2SkuDTO(Sku sku) {
        SkuDTO skuDTO = new SkuDTO();
        BeanUtils.copyProperties(sku, skuDTO);
        List<SkuAttributeDTO> skuAttributeDTOS = JSON.parseObject(sku.getAttribute(),
                new TypeReference<List<SkuAttributeDTO>>() {
                });
        skuAttributeDTOS.forEach(skuAttributeDTO -> {
            ResponseDTO<SkuAttributeNameDTO> nameDTOResponseDTO = skuAttributeNameService.getByAttributeNameId(skuAttributeDTO.getAttributeNameId());
            if (nameDTOResponseDTO != null && nameDTOResponseDTO.getData() != null) {
                skuAttributeDTO.setAttributeName(nameDTOResponseDTO.getData().getAttributeName());
            }
            ResponseDTO<SkuAttributeValueDTO> valueDTOResponseDTO = skuAttributeValueService.getByAttributeValueId(skuAttributeDTO.getAttributeValueId());
            if (valueDTOResponseDTO != null && valueDTOResponseDTO.getData() != null) {
                skuAttributeDTO.setAttributeValue(valueDTOResponseDTO.getData().getAttributeValue());
            }
        });
        List<GoodsImageDTO> list = goodsImageService.listBySkuId(skuDTO.getSkuId());
        skuDTO.setSkuImages(list);
        skuDTO.setAttributes(skuAttributeDTOS);
        return skuDTO;
    }

    @Override
    public boolean checkSkuId(Long skuId) {
        return skuMapper.countBySkuId(skuId) > 0;
    }

    @Override
    public Long getMinSalePrice(Long goodsId) {
        Long minSalePrice = skuMapper.selectMinSalePrice(goodsId);
        return minSalePrice != null ? minSalePrice : 99999999999L;
    }
}
