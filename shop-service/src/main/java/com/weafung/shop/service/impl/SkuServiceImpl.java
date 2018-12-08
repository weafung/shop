package com.weafung.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.SkuMapper;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.Sku;
import com.weafung.shop.model.query.AdminUpdateSkuOfGoodsQuery;
import com.weafung.shop.service.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuAttributeNameService skuAttributeNameService;

    @Autowired
    private SkuAttributeValueService skuAttributeValueService;

    @Autowired
    private GoodsImageService goodsImageService;

    @Autowired
    private SnowFlakeService snowFlakeService;

    @Override
    public ResponseDTO<List<SkuDTO>> listSkuOfGoods(Long goodsId) {
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
    public ResponseDTO<Boolean> saveSkuOfGoods(AdminUpdateSkuOfGoodsQuery query) {
        if (query == null || query.getGoodsId() == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        long skuId = snowFlakeService.nextId(SkuService.class);
        Sku sku = new Sku();
        sku.setSkuId(skuId);
        sku.setGoodsId(query.getGoodsId());
        sku.setAttribute(JSON.toJSONString(query.getAttributes()));
        sku.setStoreCount(query.getStoreCount());
        sku.setSalePrice(query.getSalePrice());
        sku.setMarketPrice(query.getMarketPrice());
        sku.setBonusRatio(query.getBonusRatio());
        sku.setHidden(query.getHidden());
        sku.setOnsale(query.getOnsale());
        boolean success = skuMapper.insert(sku) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        log.warn("insert sku of goods failed. query: {}", query);
        return ResponseDTO.build(CodeEnum.SKU_UPDATE_FAIL, Boolean.FALSE);
    }

    @Override
    public ResponseDTO<Boolean> updateSkuOfGoods(AdminUpdateSkuOfGoodsQuery query) {
        if (query == null || query.getSkuId() == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Sku sku = new Sku();
        sku.setSkuId(query.getSkuId());
        sku.setAttribute(JSON.toJSONString(query.getAttributes()));
        sku.setStoreCount(query.getStoreCount());
        sku.setSalePrice(query.getSalePrice());
        sku.setMarketPrice(query.getMarketPrice());
        sku.setBonusRatio(query.getBonusRatio());
        sku.setHidden(query.getHidden());
        sku.setOnsale(query.getOnsale());
        boolean success = skuMapper.updateBySkuId(sku) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        log.warn("update sku of goods failed. query: {}", query);
        return ResponseDTO.build(CodeEnum.SKU_UPDATE_FAIL, Boolean.FALSE);
    }

    @Override
    public SkuDTO getSkuDTOBySkuId(Long skuId) {
        if (skuId == null) {
            return null;
        }
        Sku sku = skuMapper.selectBySkuId(skuId);
        if (sku == null) {
            return null;
        }
        return sku2SkuDTO(sku);
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

    @Override
    public ResponseDTO<Boolean> deleteSkuOfGoods(Long skuId) {
        boolean success = skuMapper.deleteBySkuId(skuId) > 0;
        if (!success) {
            log.warn("delete sku failed. skuId:{}", skuId);
            return ResponseDTO.build(CodeEnum.SKU_DELETE_FAIL, Boolean.FALSE);
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }

    @Override
    public ResponseDTO<List<SkuSpecDTO>> listSkuSpec() {
        List<SkuAttributeNameDTO> skuAttributeNames = skuAttributeNameService.listSkuAttributeName().getData();
        List<SkuSpecDTO> skuSpecDTOS = skuAttributeNames.stream().map(skuAttributeNameDTO -> {
            SkuSpecDTO skuSpecDTO = new SkuSpecDTO();
            skuSpecDTO.setId(skuAttributeNameDTO.getAttributeNameId());
            skuSpecDTO.setLabel(skuAttributeNameDTO.getAttributeName());
            List<SkuSpecDTO> children = skuAttributeValueService.listByAttributeNameId(skuAttributeNameDTO.getAttributeNameId())
                    .getData().stream().map(skuAttributeValueDTO -> {
                        SkuSpecDTO skuValueSpecDTO = new SkuSpecDTO();
                        skuValueSpecDTO.setId(skuAttributeValueDTO.getAttributeValueId());
                        skuValueSpecDTO.setLabel(skuAttributeValueDTO.getAttributeValue());
                        return skuValueSpecDTO;
                    }).collect(Collectors.toList());
            skuSpecDTO.setChildren(children);
            return skuSpecDTO;
        }).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(skuSpecDTOS);
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

}
