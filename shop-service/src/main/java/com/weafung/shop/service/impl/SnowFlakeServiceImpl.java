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

    public SnowFlakeServiceImpl() {
        addressSnowFlake = new SnowFlake(1L, 1L);
        goodsSnowFlake = new SnowFlake(2L, 1L);
    }

    @Override
    public Long nextAddressId() {
        return addressSnowFlake.nextId();
    }

    @Override
    public Long nextGoodsId() {
        return goodsSnowFlake.nextId();
    }
}
