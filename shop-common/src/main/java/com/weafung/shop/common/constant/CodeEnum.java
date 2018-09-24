package com.weafung.shop.common.constant;

import lombok.Getter;

/**
 * @author weifeng
 */

@Getter
public enum CodeEnum {
    SUCCESS(CodeConstant.SUCCESS, "操作成功"),
    PARAM_EMPTY(CodeConstant.PARAM_EMPTY, "参数为空"),
    ERROR(CodeConstant.ERROR, "调用服务失败"),


    ;

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
