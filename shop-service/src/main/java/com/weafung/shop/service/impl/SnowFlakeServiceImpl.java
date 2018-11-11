package com.weafung.shop.service.impl;

import com.weafung.shop.common.util.SnowFlake;
import com.weafung.shop.service.SnowFlakeService;
import org.springframework.stereotype.Service;

/**
 * @author weifengshih
 */
@Service
public class SnowFlakeServiceImpl implements SnowFlakeService {

    private SnowFlake addressSnowFlake;
    private SnowFlake goodsSnowFlake;
    private SnowFlake gorderSnowFlake;
    private SnowFlake orderSnowFlake;
    private SnowFlake categorySnowFlake;

    public SnowFlakeServiceImpl() {
        addressSnowFlake = new SnowFlake(1L, 1L);
        goodsSnowFlake = new SnowFlake(2L, 1L);
        gorderSnowFlake = new SnowFlake(3L, 1L);
        orderSnowFlake = new SnowFlake(4L,1L);
        categorySnowFlake = new SnowFlake(5L, 1L);
    }

    @Override
    public Long nextAddressId() {
        return addressSnowFlake.nextId();
    }

    @Override
    public Long nextGoodsId() {
        return goodsSnowFlake.nextId();
    }

    @Override
    public Long nextGorderId() {
        return gorderSnowFlake.nextId();
    }

    @Override
    public Long nextOrderId() {
        return orderSnowFlake.nextId();
    }

    @Override
    public Long nextCategoryId() {
        return categorySnowFlake.nextId();
    }
}
