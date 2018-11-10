package com.weafung.shop.service;

/**
 * @author weifengshih
 */
public interface SnowFlakeService {
    Long nextAddressId();

    Long nextGoodsId();

    Long nextGorderId();

    Long nextOrderId();
}
