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



    DELETE_FAIL(601, "删除失败"),
    SAVE_FAIL(602, "保存失败"),

    FORBIDDEN(1403, "登录凭据无效"),
    LOGIN_FAIL(1500, "登录失败, 请重新尝试"),

    LOGIN_SUCCESS(2200, "登录成功"),
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
    GOODS_DELETE_FAIL(205500, "商品删除失败"),
    SKU_DELETE_FAIL(206500, "商品SKU删除失败"),
    GOODS_UPDATE_FAIL(207500, "商品更新失败"),
    IMAGE_OF_GOODS_UPDATE_FAIL(208500, "商品图片更新失败"),
    GOODS_INSERT_FAIL(209500, "商品保存失败"),
    SKU_UPDATE_FAIL(210500, "商品SKU更新失败"),
    SKU_SAVE_FAIL(211500, "商品SKU添加失败"),
    /**
     * 商品类目相关 3*****
     */
    CATEGORY_NOT_FOUND(300404, "类目不存在"),
    CATEGORY_DELETE_FAILED(301500, "类目删除失败"),
    CATEGORY_UPDATE_FAILED(302500, "类目编辑失败"),

    /**
     * 订单相关 4*****
     */
    GOODS_COUNT_MORE_THAN_LIMIT(400400, "商品数量超过每单限制"),
    GOODS_COUNT_LESS_THAN_ONE(401400, "商品数量小于1"),
    ORDER_INSERT_FAIL(402500, "创建订单失败"),

    GORDER_PAY_FAIL(403500, "订单支付失败"),
    GORDER_PACKAGE_FAIL(404500, "发货失败"),
    GORDER_DELETE_FAIL(405500, "订单删除失败"),

    GORDER_NOT_FOUND(406404, "订单不存在"),

    /**
     * 地址相关 5*****
     */
    ADDRESS_NOT_FOUND(500400, "地址不存在"),

    ;

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
