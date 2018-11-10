package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.dao.GorderMapper;
import com.weafung.shop.model.dto.AddressDTO;
import com.weafung.shop.model.dto.OrderItemDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.service.AddressService;
import com.weafung.shop.service.GorderService;
import com.weafung.shop.service.OrderService;
import com.weafung.shop.service.SnowFlakeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

/**
 * @author weifengshih
 */
@Service
@Slf4j
public class GorderServiceImpl implements GorderService {

    @Autowired
    private AddressService addressService;
    @Autowired
    private SnowFlakeService snowFlakeService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private GorderMapper gorderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> checkOut(String accountId, Long addressId, Set<OrderItemDTO> orderItemDTOSet) {
        if (StringUtils.isBlank(accountId) || Objects.isNull(addressId) || CollectionUtils.isEmpty(orderItemDTOSet)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        AddressDTO addressDTO = addressService.getAddress(addressId);
        if (addressDTO == null) {
            return ResponseDTO.build(CodeEnum.ADDRESS_NOT_FOUND, Boolean.FALSE);
        }
        Long gorderId = snowFlakeService.nextGorderId();
        Long currentTimeMillis = System.currentTimeMillis();
        if (gorderMapper.insert(gorderId, accountId, addressId, currentTimeMillis) <= 0) {
            log.error("create gorder failed. accountId: {}, addredssId: {}, orderItems: {}",
                    accountId, addressId, orderItemDTOSet);
            throw new RuntimeException("create gorder failed");
        }
        for (OrderItemDTO orderItemDTO : orderItemDTOSet) {
            ResponseDTO<Boolean> responseDTO = orderService.createOrder(accountId, gorderId,
                    orderItemDTO.getSkuId(), orderItemDTO.getCount());
            if (responseDTO == null) {
                log.warn("create order failed. responseDTO is null.");
                throw new RuntimeException("create order failed. responseDTO is null.");
            } else if (Boolean.FALSE.equals(responseDTO.getData())) {
                log.warn("create order failed. responseDTO: {}", responseDTO);
                throw new RuntimeException(responseDTO.getMsg());
            }
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }
}
