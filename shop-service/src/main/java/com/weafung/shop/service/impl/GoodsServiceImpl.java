package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.GoodsImageMapper;
import com.weafung.shop.dao.GoodsMapper;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.service.CategoryService;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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

    @Autowired
    private CategoryService categoryService;

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
    public ResponseDTO<Boolean> deleteGoods(Long goodsId) {
        if (goodsId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        boolean success = goodsMapper.deleteGoods(goodsId) > 0;
        if (!success) {
            log.warn("delete goods failed. goodsId:{}", goodsId);
            return ResponseDTO.build(CodeEnum.GOODS_DELETE_FAIL, Boolean.FALSE);
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
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
        simpleGoodsDTO.setGoodsDetailId(0L);
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
        simpleGoodsDTO.setGoodsDetailId(0L);
        return simpleGoodsDTO;
    }

    @Override
    public ResponseDTO<List<SimpleGoodsDTO>> listGoodsByCategoryId(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId) {
        List<Goods> goodsList = goodsMapper.listGoodsByCategoryId(firstCategoryId, secondCategoryId, thirdCategoryId);
        if (CollectionUtils.isEmpty(goodsList)) {
            return ResponseDTO.buildSuccess(Lists.newArrayList());
        }
        List<SimpleGoodsDTO> simpleGoodsDTOList = goodsList.stream()
                .map(goods -> getSimpleGoodsByGoodsId(goods.getGoodsId()))
                .filter(Objects::nonNull).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(simpleGoodsDTOList);
    }

    @Override
    public SimpleGoodsSkuDTO getGoodsSku(Long skuId) {
        SimpleGoodsSkuDTO simpleGoodsSkuDTO = new SimpleGoodsSkuDTO();

        SimpleGoodsDTO simpleGoodsDTO = getSimpleGoodsBySkuId(skuId);
        simpleGoodsSkuDTO.setGoods(simpleGoodsDTO);
        SkuDTO skuDTO = skuService.getSkuDTOBySkuId(skuId);
        simpleGoodsSkuDTO.setSku(skuDTO);

        return simpleGoodsSkuDTO;
    }

    @Override
    public ResponseDTO<Map<Long, SimpleGoodsSkuDTO>> listGoodsSku(List<Long> skuIdList) {
        if (CollectionUtils.isEmpty(skuIdList)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Maps.newHashMap());
        }
        Map<Long, SimpleGoodsSkuDTO> map = Maps.newHashMapWithExpectedSize(skuIdList.size());
        for (Long skuId : skuIdList) {
            map.put(skuId, getGoodsSku(skuId));
        }
        return ResponseDTO.buildSuccess(map);
    }

    @Override
    public ResponseDTO<List<AdminGoodsDTO>> listAdminGoods() {
        List<AdminGoodsDTO> list = goodsMapper.listAdminGoods()
                .stream().map(this::goods2AdminGoodsDTO).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(list);
    }

    private AdminGoodsDTO goods2AdminGoodsDTO(Goods goods) {
        AdminGoodsDTO adminGoodsDTO = new AdminGoodsDTO();
        adminGoodsDTO.setGoodsId(goods.getGoodsId());
        adminGoodsDTO.setTitle(goods.getTitle());
        adminGoodsDTO.setIntroduce(goods.getIntroduce());
        adminGoodsDTO.setFirstCategoryId(goods.getFirstCategoryId());
        adminGoodsDTO.setSecondCategoryId(goods.getSecondCategoryId());
        adminGoodsDTO.setThirdCategoryId(goods.getThirdCategoryId());
        try {
            ResponseDTO<CategoryDTO> responseDTO = null;
            responseDTO = categoryService.getCategory(goods.getFirstCategoryId());
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                adminGoodsDTO.setFirstCategory(responseDTO.getData().getTitle());
            }
            responseDTO = categoryService.getCategory(goods.getSecondCategoryId());
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                adminGoodsDTO.setSecondCategory(responseDTO.getData().getTitle());
            }
            responseDTO = categoryService.getCategory(goods.getThirdCategoryId());
            if (Objects.equals(responseDTO.getCode(), CodeEnum.SUCCESS.getCode())) {
                adminGoodsDTO.setThirdCategory(responseDTO.getData().getTitle());
            }
        } catch (Exception e) {
            log.warn("get category detail failed. goods:{}", goods, e);
        }
        return adminGoodsDTO;
    }
}
