package com.weafung.shop.common.util.wechat;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class SnsapiUserinfo {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String privilege;
    private String unionid;
}
