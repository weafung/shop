package com.weafung.shop.common.util;

/**
 * @author weifengshih
 */
public class PageUtil {

    public static int getPageSize(Integer size) {
        int DEFAULT_PAGE_SIZE = 20;
        int MAX_PAGE_SIZE = 100;
        if (size == null || size < 0) {
            return DEFAULT_PAGE_SIZE;
        }
        if (size > MAX_PAGE_SIZE) {
            return MAX_PAGE_SIZE;
        }
        return size;
    }
}
