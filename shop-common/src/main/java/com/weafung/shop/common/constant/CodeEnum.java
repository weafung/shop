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
     * 参数配置相关 10***
     */
    WEBSITE_CONFIG_NOT_FOUND(10404, "参数配置不存在"),

    /**
     * 商品相关 20***
     */
    GOODS_NOT_FOUND(20404, "商品不存在"),


    ;

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
