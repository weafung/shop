package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.GoodsImageMapper;
import com.weafung.shop.dao.GoodsMapper;
import com.weafung.shop.model.dto.GoodsDTO;
import com.weafung.shop.model.dto.GoodsImageDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsDTO;
import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author weifeng
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private SkuService skuService;

    @Override
    public ResponseDTO<GoodsDTO> getGoodsByGoodsId(Long goodsId) {
        if (goodsId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        GoodsDTO goodsDTO = getGoods(goodsId);
        if (goodsDTO == null) {
            return ResponseDTO.build(CodeEnum.GOODS_NOT_FOUND);
        }
        return ResponseDTO.buildSuccess(goodsDTO);
    }

    private GoodsDTO getGoods(Long goodsId) {
        // 获取商品信息
        Goods goods = goodsMapper.getGoodsByGoodsId(goodsId);
        if (goods == null) {
            return null;
        }
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goods, goodsDTO);
        // 获取商品图片信息
        List<GoodsImage> goodsImageList = goodsImageMapper.listByGoodsId(goodsId);
        goodsDTO.setGoodsImageList(goodsImageList.stream().map(goodsImage -> {
            GoodsImageDTO goodsImageDTO = new GoodsImageDTO();
            BeanUtils.copyProperties(goodsImage, goodsImageDTO);
            return goodsImageDTO;
        }).collect(Collectors.toList()));
        // 获取SKU信息
        goodsDTO.setSkuList(skuService.listSku(goodsId).getData());
        return goodsDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> saveGoods(GoodsDTO goodsDTO) {
        if (goodsDTO == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> updateGoods(GoodsDTO goodsDTO) {
        if (goodsDTO == null || goodsDTO.getGoodsId() == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        Goods goods = goodsMapper.getGoodsByGoodsId(goodsDTO.getGoodsId());
        if (goods == null) {
            return null;
        }
        BeanUtils.copyProperties(goodsDTO, goods);
        goodsMapper.updateSelective(goods);
        // 删除原来的照片
        goodsImageMapper.deleteByGoodsId(goodsDTO.getGoodsId());
        // 添加新照片
        if (CollectionUtils.isNotEmpty(goodsDTO.getGoodsImageList())) {
            goodsDTO.getGoodsImageList()
                    .forEach(goodsImageDTO -> goodsImageMapper.insert(goodsDTO.getGoodsId(), goodsImageDTO.getImageUrl()));
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }

    @Override
    public SimpleGoodsDTO getSimpleGoodsByGoodsId(Long goodsId) {
        if (goodsId == null) {
            return null;
        }
        Goods goods = goodsMapper.getGoodsByGoodsId(goodsId);
        if (goods == null) {
            return null;
        }
        SimpleGoodsDTO simpleGoodsDTO = new SimpleGoodsDTO();
        BeanUtils.copyProperties(goods, simpleGoodsDTO);
        simpleGoodsDTO.setSalePrice(skuService.getMinSalePrice(goodsId));
        simpleGoodsDTO.setGoodsImage(goodsImageMapper.getImageUrlByGoodsId(goodsId));
        return simpleGoodsDTO;
    }

    @Override
    public SimpleGoodsDTO getSimpleGoodsBySkuId(Long skuId) {
        if (skuId == null) {
            return null;
        }
        Goods goods = goodsMapper.getGoodsBySkuId(skuId);
        if (goods == null) {
            return null;
        }
        SimpleGoodsDTO simpleGoodsDTO = new SimpleGoodsDTO();
        BeanUtils.copyProperties(goods, simpleGoodsDTO);
        simpleGoodsDTO.setSalePrice(skuService.getMinSalePrice(goods.getGoodsId()));
        simpleGoodsDTO.setGoodsImage(goodsImageMapper.getImageUrlByGoodsId(goods.getGoodsId()));
        return simpleGoodsDTO;
    }

    @Override
    public ResponseDTO<List<SimpleGoodsDTO>> listGoodsByCategoryId(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId) {
        List<Goods> goodsList = goodsMapper.listGoodsByCategoryId(firstCategoryId,secondCategoryId,thirdCategoryId);
        if (CollectionUtils.isEmpty(goodsList)) {
            return ResponseDTO.buildSuccess(Lists.newArrayList());
        }
        List<SimpleGoodsDTO> simpleGoodsDTOList = goodsList.stream()
                .map(goods -> getSimpleGoodsByGoodsId(goods.getGoodsId()))
                .filter(Objects::nonNull).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(simpleGoodsDTOList);
    }
}
