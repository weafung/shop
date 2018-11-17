package com.weafung.shop.service;

/**
 * @author weifengshih
 */
public interface SnowFlakeService {
    Long nextId(Class<?> clazz);
}
