package com.weafung.shop.common.util.wechat;

import lombok.Data;

/**
 * @author weifengshih
 */
@Data
public class AccessToken {
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openid;
    private String scope;
}
