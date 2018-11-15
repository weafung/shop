package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.ShoppingCartMapper;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.ShoppingCart;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.ShoppingCartService;
import com.weafung.shop.service.SkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author weifengshih
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SkuService skuService;

    @Override
    public ResponseDTO<ShoppingCartDTO> getShoppingCart(String accountId) {
        if (StringUtils.isBlank(accountId)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByAccountId(accountId);

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setAccountId(accountId);
        shoppingCartDTO.setShoppingCartDetailList(Lists.newArrayList());
        if (CollectionUtils.isEmpty(shoppingCartList)) {
            return ResponseDTO.buildSuccess(shoppingCartDTO);
        }
        List<ShoppingCartDetailDTO> shoppingCartDetailDTOList = shoppingCartList.stream().map(shoppingCart -> {
            ShoppingCartDetailDTO shoppingCartDetailDTO = new ShoppingCartDetailDTO();
            SimpleGoodsSkuDTO simpleGoodsSkuDTO = goodsService.getGoodsSku(shoppingCart.getSkuId());
            shoppingCartDetailDTO.setGoods(simpleGoodsSkuDTO.getGoods());
            shoppingCartDetailDTO.setSku(simpleGoodsSkuDTO.getSku());
            shoppingCartDetailDTO.setCount(shoppingCart.getCount());
            return shoppingCartDetailDTO;
        }).collect(Collectors.toList());
        shoppingCartDTO.setShoppingCartDetailList(shoppingCartDetailDTOList);
        return ResponseDTO.buildSuccess(shoppingCartDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> addGoods(String accountId, Long goodsId, Long skuId, Integer count) {
        SimpleGoodsDTO simpleGoodsDTO = goodsService.getSimpleGoodsByGoodsId(goodsId);
        if (simpleGoodsDTO == null) {
            return ResponseDTO.build(CodeEnum.GOODS_NOT_FOUND, Boolean.FALSE);
        }
        if (!skuService.checkSkuId(skuId)) {
            return ResponseDTO.build(CodeEnum.SKU_NOT_FOUND, Boolean.FALSE);
        }
        if (count > simpleGoodsDTO.getLimitPerOrder()) {
            return ResponseDTO.build(CodeEnum.GOODS_COUNT_MORE_THAN_LIMIT, Boolean.FALSE);
        }
        ShoppingCart shoppingCart = shoppingCartMapper.selectByAccountIdAndSkuId(accountId, skuId);
        if (shoppingCart != null) {
            count = simpleGoodsDTO.getLimitPerOrder() - shoppingCart.getCount() > count
                    ? shoppingCart.getCount() + count
                    : simpleGoodsDTO.getLimitPerOrder();
            shoppingCartMapper.delete(accountId, skuId);
        }
        boolean result = shoppingCartMapper.insert(accountId, goodsId, skuId, count) > 0;
        if (result) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> updateGoods(String accountId, Long skuId, Integer count) {
        SimpleGoodsDTO simpleGoodsDTO = goodsService.getSimpleGoodsBySkuId(skuId);
        if (simpleGoodsDTO == null) {
            return ResponseDTO.build(CodeEnum.GOODS_NOT_FOUND, Boolean.FALSE);
        }
        if (!skuService.checkSkuId(skuId)) {
            return ResponseDTO.build(CodeEnum.SKU_NOT_FOUND, Boolean.FALSE);
        }
        if (count > simpleGoodsDTO.getLimitPerOrder()) {
            return ResponseDTO.build(CodeEnum.GOODS_COUNT_MORE_THAN_LIMIT, Boolean.FALSE);
        }
        if (count <= 0) {
            return ResponseDTO.build(CodeEnum.GOODS_COUNT_LESS_THAN_ONE, Boolean.FALSE);
        }
        boolean updateResult = shoppingCartMapper.update(accountId, skuId, count) > 0;
        if (updateResult) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> deleteGoods(String accountId, Long skuId) {
        boolean result = shoppingCartMapper.delete(accountId, skuId) > 0;
        if (result) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.ERROR, Boolean.FALSE);
    }
}
