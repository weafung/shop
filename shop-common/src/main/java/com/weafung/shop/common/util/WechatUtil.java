package com.weafung.shop.common.util;

import com.weafung.shop.common.util.wechat.AccessToken;
import com.weafung.shop.common.util.wechat.SnsapiUserinfo;

import java.util.Arrays;

/**
 * @author weifengshih
 */
public class WechatUtil {
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static final String SNSAPI_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        if (token == null || signature == null || timestamp == null || nonce == null) {
            return false;
        }
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);

        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String tmpStr = EncryptUtil.sha1(arr[0] + arr[1] + arr[2]);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    public static AccessToken getAccessToken(String appid, String appSecret, String code) {
        String url = String.format(ACCESS_TOKEN_URL, appid, appSecret, code);
        return HTTPUtil.sendGet(url, null, AccessToken.class);
    }

    public static SnsapiUserinfo getSnsapiUserinfo(String accessToken, String openid) {
        String url = String.format(SNSAPI_USERINFO_URL, accessToken, openid);
        return HTTPUtil.sendGet(url, null, SnsapiUserinfo.class);
    }

    public static void main(String[] args) {
        AccessToken accessToken = getAccessToken("wx3c6c90720c6c7735", "ad22f445146c3f973598d0e16faeb430","061YbaVU1uvKOX0LJwRU1ZKQUU1YbaVv");

        System.out.println(accessToken);

        SnsapiUserinfo snsapiUserinfo = getSnsapiUserinfo(accessToken.getAccessToken(), accessToken.getOpenid());

        System.out.println(snsapiUserinfo);
    }

}