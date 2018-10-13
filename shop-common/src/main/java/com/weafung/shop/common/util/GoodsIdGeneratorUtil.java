package com.weafung.shop.common.util;

/**
 * @author weifeng
 */
public class GoodsIdGeneratorUtil {
    private GoodsIdGeneratorUtil() {
    }

    private static class InstanceHolder {
        private static final SnowFlake INSTANCE = new SnowFlake(0, 1);
    }

    public static SnowFlake getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static long getNextId() {
        return getInstance().nextId();
    }
}
