package com.weafung.shop.model.constant;

/**
 * @author weifengshih
 */
public interface MallConstant {
    int GORDER_STATUS_WAIT_PAY = 0;

    int GORDER_STATUS_WAIT_SEND = 1;

    int GORDER_STATUS_WAIT_RECEIVED = 2;

    int GORDER_STATUS_WAIT_COMMENT = 3;

    long ROOT_CATEGORY_ID = 0L;

    String CONFIG_KEY_LIMIT_PER_ORDER = "limit_per_order";
}
