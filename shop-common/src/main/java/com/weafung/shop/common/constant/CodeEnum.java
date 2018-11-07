package com.weafung.shop.common.constant;

import lombok.Getter;

/**
 * @author weifeng
 */

@Getter
public enum CodeEnum {
    SUCCESS(200, "操作成功"),
    PARAM_EMPTY(400, "请求参数为空"),
    ERROR(500, "调用服务失败"),

    /**
     * 参数配置相关 1*****
     */
    WEBSITE_CONFIG_NOT_FOUND(100404, "参数配置不存在"),

    /**
     * 商品相关 2*****
     */
    GOODS_NOT_FOUND(201404, "商品不存在"),
    SKU_ATTRIBUTE_NAME_NOT_FOUND(202404, "商品SKU属性名不存在"),
    SKU_ATTRIBUTE_VALUE_NOT_FOUND(203404, "商品SKU属性值不存在"),
    SKU_NOT_FOUND(204404, "商品SKU不存在"),
    /**
     * 商品类目相关 3*****
     */
    CATEGORY_NOT_FOUND(300404, "类目不存在"),

    /**
     * 订单相关 4*****
     */
    GOODS_COUNT_MORE_THAN_LIMIT(400404, "商品数量超过每单限制"),

    ;

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
