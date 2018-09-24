package com.weafung.shop.model.vo;

import com.weafung.shop.common.constant.CodeConstant;
import com.weafung.shop.common.constant.CodeEnum;
import lombok.Data;

/**
 * @author weifeng
 */
@Data
public class ResponseVO<T> {
    private Integer code;
    private T data;
    private String msg;

    public ResponseVO() {
    }

    public ResponseVO(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ResponseVO<T> buildSuccess(T data) {
        return new ResponseVO<>(CodeEnum.SUCCESS.getCode(), data, CodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResponseVO<T> buildFailure(Integer code, T data, String msg) {
        return new ResponseVO<>(code, data, msg);
    }

    public static <T> ResponseVO<T> build(CodeEnum codeEnum) {
        return new ResponseVO<>(codeEnum.getCode(), null, codeEnum.getMsg());
    }

    public static <T> ResponseVO<T> build(CodeEnum codeEnum, T data) {
        return new ResponseVO<>(codeEnum.getCode(), data, codeEnum.getMsg());
    }
}
