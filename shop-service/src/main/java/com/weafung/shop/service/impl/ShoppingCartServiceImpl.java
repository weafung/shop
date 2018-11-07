package com.weafung.shop.service.impl;

import com.google.common.collect.Lists;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.ShoppingCartMapperEx;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.ShoppingCart;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.ShoppingCartService;
import com.weafung.shop.service.SkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author weifengshih
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapperEx shoppingCartMapperEx;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SkuService skuService;

    @Override
    public ResponseDTO<ShoppingCartDTO> getShoppingCart(String accountId) {
        if (StringUtils.isBlank(accountId)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY);
        }
        List<ShoppingCart> shoppingCartList = shoppingCartMapperEx.selectByAccountId(accountId);

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setAccountId(accountId);
        shoppingCartDTO.setShoppingCartDetailList(Lists.newArrayList());
        if (CollectionUtils.isEmpty(shoppingCartList)) {
            return ResponseDTO.buildSuccess(shoppingCartDTO);
        }
        List<ShoppingCartDetailDTO> shoppingCartDetailDTOList = shoppingCartList.stream().map(shoppingCart -> {
            ShoppingCartDetailDTO shoppingCartDetailDTO = new ShoppingCartDetailDTO();
            SimpleGoodsDTO simpleGoodsDTO = goodsService.getSimpleGoodsByGoodsId(shoppingCart.getGoodsId());
            shoppingCartDetailDTO.setGoods(simpleGoodsDTO);
            SkuDTO skuDTO = skuService.getSkuDTOBySkuId(shoppingCart.getSkuId());
            shoppingCartDetailDTO.setSku(skuDTO);
            return shoppingCartDetailDTO;
        }).collect(Collectors.toList());
        shoppingCartDTO.setShoppingCartDetailList(shoppingCartDetailDTOList);
        return ResponseDTO.buildSuccess(shoppingCartDTO);
    }
}
