package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.GoodsImageMapper;
import com.weafung.shop.dao.GoodsMapper;
import com.weafung.shop.model.constant.MallConstant;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.Goods;
import com.weafung.shop.model.po.GoodsImage;
import com.weafung.shop.model.query.AdminGoodsQuery;
import com.weafung.shop.model.query.AdminUpdateGoodsQuery;
import com.weafung.shop.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private SnowFlakeService snowFlakeService;

    @Autowired
    private WebsiteConfigService websiteConfigService;

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
        goodsDTO.setSkuList(skuService.listSkuOfGoods(goodsId).getData());
        return goodsDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> saveGoods(AdminUpdateGoodsQuery query) {
        if (query == null || StringUtils.isBlank(query.getTitle())) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        query.setTitle(StringUtils.trimToEmpty(query.getTitle()));
        query.setIntroduce(StringUtils.trimToEmpty(query.getIntroduce()));
        if (query.getLimitPerOrder() == null) {
            query.setLimitPerOrder(Integer.valueOf(websiteConfigService.getConfigValue(MallConstant.CONFIG_KEY_LIMIT_PER_ORDER).getData()));
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(query, goods);
        Long goodsId = snowFlakeService.nextId(GoodsService.class);
        goods.setGoodsId(goodsId);
        boolean success = goodsMapper.insertSelective(goods) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        log.warn("insert new goods failed. query: {}", query);
        return ResponseDTO.build(CodeEnum.GOODS_INSERT_FAIL, Boolean.FALSE);
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
    public ResponseDTO<Boolean> updateGoods(AdminUpdateGoodsQuery query) {
        if (query == null || query.getGoodsId() == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        Goods goods = goodsMapper.getGoodsByGoodsId(query.getGoodsId());
        if (goods == null) {
            return ResponseDTO.build(CodeEnum.GOODS_NOT_FOUND, Boolean.FALSE);
        }
        BeanUtils.copyProperties(query, goods);
        boolean success = goodsMapper.updateSelective(goods) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.GOODS_UPDATE_FAIL, Boolean.FALSE);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> updateImagesOfGoods(Long goodsId, List<String> imageUrls) {
        try {
            // 删除原来的照片
            goodsImageMapper.deleteByGoodsId(goodsId);
            // 添加新照片
            if (CollectionUtils.isNotEmpty(imageUrls)) {
                imageUrls.stream().map(StringUtils::trimToEmpty)
                        .filter(StringUtils::isNotBlank)
                        .forEach(url -> goodsImageMapper.insert(goodsId, url));
            }
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        } catch (Exception e) {
            log.warn("update image of goods failed. goodsId: {}, urls: {}", goodsId, imageUrls, e);
            throw new RuntimeException(e.getMessage());
        }
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
    public ResponseDTO<List<SimpleGoodsDTO>> listGoodsByKeyword(Long firstCategoryId, Long secondCategoryId, Long thirdCategoryId, String title) {
        List<Goods> goodsList = goodsMapper.listGoodsByKeyword(firstCategoryId, secondCategoryId, thirdCategoryId, title);
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
    public ResponseDTO<List<AdminGoodsDTO>> listGoodsForAdministrator(AdminGoodsQuery query) {
        List<AdminGoodsDTO> list = goodsMapper.listAdminGoods(query.getGoodsId(), StringUtils.trimToNull(query.getTitle()))
                .stream().map(this::goods2AdminGoodsDTO).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(list);
    }

    @Override
    public ResponseDTO<List<String>> listImageOfGoods(Long goodsId) {
        if (goodsId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Lists.newArrayList());
        }
        List<GoodsImage> goodsImages = goodsImageMapper.listByGoodsId(goodsId);
        List<String> goodsImageUrls = goodsImages.stream().map(GoodsImage::getImageUrl).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(goodsImageUrls);
    }


    private AdminGoodsDTO goods2AdminGoodsDTO(Goods goods) {
        AdminGoodsDTO adminGoodsDTO = new AdminGoodsDTO();
        adminGoodsDTO.setGoodsId(goods.getGoodsId());
        adminGoodsDTO.setTitle(goods.getTitle());
        adminGoodsDTO.setIntroduce(goods.getIntroduce());
        adminGoodsDTO.setFirstCategoryId(goods.getFirstCategoryId());
        adminGoodsDTO.setSecondCategoryId(goods.getSecondCategoryId());
        adminGoodsDTO.setThirdCategoryId(goods.getThirdCategoryId());
        adminGoodsDTO.setLimitPerOrder(goods.getLimitPerOrder());
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
