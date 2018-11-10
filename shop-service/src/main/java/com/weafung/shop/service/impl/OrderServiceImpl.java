package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.OrderMapper;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.model.dto.SimpleGoodsDTO;
import com.weafung.shop.model.dto.SkuDTO;
import com.weafung.shop.model.dto.SorderDTO;
import com.weafung.shop.model.po.Sorder;
import com.weafung.shop.service.GoodsService;
import com.weafung.shop.service.OrderService;
import com.weafung.shop.service.SkuService;
import com.weafung.shop.service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author weifengshih
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private SnowFlakeService snowFlakeService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SkuService skuService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> createOrder(String accountId, Long gorderId, Long skuId, Integer count) {
        if (StringUtils.isBlank(accountId) || Objects.isNull(gorderId) || Objects.isNull(skuId) || Objects.isNull(count)) {
            log.warn("param is empty. accountId:{}, gorderId: {}, skuId: {}, count: {}",
                    accountId, gorderId, skuId, count);
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        Long orderId = snowFlakeService.nextOrderId();

        Sorder sorder = new Sorder();
        sorder.setGorderId(gorderId);
        sorder.setOrderId(orderId);

        SimpleGoodsDTO simpleGoodsDTO = goodsService.getSimpleGoodsBySkuId(skuId);
        if (simpleGoodsDTO == null) {
            return ResponseDTO.build(CodeEnum.GOODS_NOT_FOUND, Boolean.FALSE);
        }
        sorder.setGoodsId(simpleGoodsDTO.getGoodsId());

        SkuDTO skuDTO = skuService.getSkuDTOBySkuId(skuId);
        if (skuDTO == null) {
            return ResponseDTO.build(CodeEnum.SKU_NOT_FOUND, Boolean.FALSE);
        }
        sorder.setSkuId(skuId);

        if (count > simpleGoodsDTO.getLimitPerOrder()) {
            return ResponseDTO.build(CodeEnum.GOODS_COUNT_MORE_THAN_LIMIT, Boolean.FALSE);
        }
        if (count <= 0) {
            return ResponseDTO.build(CodeEnum.GOODS_COUNT_LESS_THAN_ONE, Boolean.FALSE);
        }
        sorder.setCount(count);

        sorder.setTitle(simpleGoodsDTO.getTitle());
        sorder.setIntroduce(simpleGoodsDTO.getIntroduce());
        sorder.setGoodsDetailId(simpleGoodsDTO.getGoodsDetailId());
        sorder.setSalePrice(skuDTO.getSalePrice());
        sorder.setMarketPrice(skuDTO.getMarketPrice());
        sorder.setMoney(skuDTO.getSalePrice() * count);

        boolean insertSuccess = orderMapper.insert(sorder) > 0;
        if (!insertSuccess) {
            log.warn("insert to sorder table failed. sorder: {}", sorder);
            return ResponseDTO.build(CodeEnum.ORDER_INSERT_FAIL, Boolean.FALSE);
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }

    @Override
    public List<SorderDTO> listSorderByGorderId(Long gorderId) {
        List<Sorder> sorderList = orderMapper.listOrderByGorderId(gorderId);
        return sorderList.stream().map(sorder -> {
            SorderDTO sorderDTO = new SorderDTO();
            BeanUtils.copyProperties(sorder, sorderDTO);
            return sorderDTO;
        }).collect(Collectors.toList());
    }
}
