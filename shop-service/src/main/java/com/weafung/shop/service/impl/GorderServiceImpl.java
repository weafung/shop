package com.weafung.shop.service.impl;

import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.common.util.PageUtil;
import com.weafung.shop.dao.GorderMapper;
import com.weafung.shop.model.dto.*;
import com.weafung.shop.model.po.Gorder;
import com.weafung.shop.model.query.AdminGorderQuery;
import com.weafung.shop.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private ShoppingCartService shoppingCartService;

    @Autowired
    private GorderMapper gorderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> checkOut(String accountId, Long addressId, Set<OrderItemDTO> orderItemDTOSet) {
        if (StringUtils.isBlank(accountId) || Objects.isNull(addressId) || CollectionUtils.isEmpty(orderItemDTOSet)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        AddressDTO addressDTO = addressService.getAddress(accountId, addressId).getData();
        if (addressDTO == null) {
            return ResponseDTO.build(CodeEnum.ADDRESS_NOT_FOUND, Boolean.FALSE);
        }
        Long gorderId = snowFlakeService.nextId(GorderService.class);
        Long currentTimeMillis = System.currentTimeMillis();
        // 创建大订单
        if (gorderMapper.insert(gorderId, accountId, addressId, currentTimeMillis,
                addressDTO.getProvince(), addressDTO.getCity(), addressDTO.getDistrict(),
                addressDTO.getDetail(), addressDTO.getName(), addressDTO.getPhone()) <= 0) {
            log.error("create gorder failed. accountId: {}, addredssId: {}, orderItems: {}",
                    accountId, addressId, orderItemDTOSet);
            throw new RuntimeException("create gorder failed");
        }
        // 创建小订单
        for (OrderItemDTO orderItemDTO : orderItemDTOSet) {
            ResponseDTO<Boolean> orderResponseDTO = orderService.createOrder(accountId, gorderId,
                    orderItemDTO.getSkuId(), orderItemDTO.getCount());
            if (orderResponseDTO == null) {
                log.warn("create order failed. responseDTO is null. orderItemDTO:{}", orderItemDTO);
                throw new RuntimeException("create order failed. responseDTO is null.");
            } else if (Boolean.FALSE.equals(orderResponseDTO.getData())) {
                log.warn("create order failed. orderItemDTO:{}, responseDTO: {}", orderItemDTO, orderResponseDTO);
                throw new RuntimeException(orderResponseDTO.getMsg());
            }
            ResponseDTO<Boolean> shoppingCartResponseDTO = shoppingCartService.deleteGoods(accountId, orderItemDTO.getSkuId());
            if (shoppingCartResponseDTO == null) {
                log.warn("remove from shopping cart failed. responseDTO is null.");
            } else if (Boolean.FALSE.equals(shoppingCartResponseDTO.getData())) {
                log.warn("create order failed. orderItemDTO:{}, responseDTO: {}", orderItemDTO, shoppingCartResponseDTO);
            }
        }
        return ResponseDTO.buildSuccess(Boolean.TRUE);
    }

    @Override
    public ResponseDTO<List<GorderDetailDTO>> listGorderDetail(String accountId, Long gorderId, Integer status) {
        List<Gorder> gorderList = gorderMapper.listGorderPageByGorderIdAndStatus(accountId, gorderId, status);
        List<GorderDetailDTO> list = gorderList.stream().map(gorder -> {
            GorderDetailDTO gorderDetailDTO = new GorderDetailDTO();

            GorderDTO gorderDTO = new GorderDTO();
            BeanUtils.copyProperties(gorder, gorderDTO);

            List<SorderDTO> sorderDTOList = orderService.listSorderByGorderId(gorderDTO.getGorderId());
            gorderDetailDTO.setSorderDTOList(sorderDTOList);

            long money = 0L;
            int count = 0;
            for (SorderDTO sorderDTO : sorderDTOList) {
                money += sorderDTO.getMoney();
                count += sorderDTO.getCount();
            }
            gorderDTO.setCount(count);
            gorderDTO.setMoney(money);
            gorderDetailDTO.setGorderDTO(gorderDTO);

            return gorderDetailDTO;
        }).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> confirmPay(String accountId, Long gorderId) {
        long payTime = System.currentTimeMillis();
        boolean success = gorderMapper.payGorder(accountId, gorderId, payTime) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.GORDER_PAY_FAIL, Boolean.FALSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> confirmReceived(String accountId, Long gorderId) {
        long receiveTime = System.currentTimeMillis();
        boolean success = gorderMapper.receiveGorder(accountId, gorderId, receiveTime) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.GORDER_PAY_FAIL);
    }

    @Override
    public ResponseDTO<List<AdminGorderDTO>> listGorderByPage(AdminGorderQuery query) {
        Long startGorderId = query.getStartGorderId() == null ? Long.MAX_VALUE : query.getStartGorderId();
        Integer pageSize = PageUtil.getPageSize(query.getPageSize());

        List<AdminGorderDTO> list = gorderMapper.listGorder(query.getGorderId(), query.getPhone(),
                query.getName(), query.getStatus(),
                query.getOrderTimeStart(), query.getOrderTimeEnd(),
                query.getPayTimeStart(), query.getPayTimeEnd(),
                query.getPackageTimeStart(), query.getPackageTimeEnd(),
                query.getConfirmTimeStart(), query.getConfirmTimeEnd(),
                startGorderId, pageSize)
                .stream()
                .map(gorder -> {
                    AdminGorderDTO adminGorderDTO = new AdminGorderDTO();
                    adminGorderDTO.setGorderId(gorder.getGorderId());
                    adminGorderDTO.setOrderTime(gorder.getOrderTime());
                    adminGorderDTO.setPayTime(gorder.getPayTime());
                    adminGorderDTO.setConfirmTime(gorder.getConfirmTime());
                    adminGorderDTO.setStatus(Integer.valueOf(gorder.getStatus()));
                    adminGorderDTO.setName(gorder.getName());
                    adminGorderDTO.setPhone(gorder.getPhone());
                    adminGorderDTO.setAddress(gorder.getProvince() + gorder.getCity()
                            + gorder.getDistrict() + gorder.getDetail());
                    adminGorderDTO.setPackageCode(gorder.getPackageCode());
                    adminGorderDTO.setPackageTime(gorder.getPackageTime());
                    return adminGorderDTO;
                }).collect(Collectors.toList());
        return ResponseDTO.buildSuccess(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> packageGorder(Long gorderId, String packageCode) {
        if (gorderId == null || StringUtils.isEmpty(packageCode)) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        long packageTime = System.currentTimeMillis();
        boolean success = gorderMapper.packageGorder(gorderId, StringUtils.trimToEmpty(packageCode), packageTime) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.GORDER_PACKAGE_FAIL, Boolean.FALSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<Boolean> deleteGorder(Long gorderId) {
        if (gorderId == null) {
            return ResponseDTO.build(CodeEnum.PARAM_EMPTY, Boolean.FALSE);
        }
        boolean success = gorderMapper.adminDelete(gorderId) > 0;
        if (success) {
            return ResponseDTO.buildSuccess(Boolean.TRUE);
        }
        return ResponseDTO.build(CodeEnum.GORDER_DELETE_FAIL, Boolean.FALSE);
    }
}
