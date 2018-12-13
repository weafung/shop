package com.weafung.shop.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author weifengshih
 */
public class HTTPUtil {
    public static String sendGet(String url, Map<String, String> paramsMap) {
        StringBuilder params = new StringBuilder();
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        String urlParams = url + (StringUtils.isNotBlank(params) ? "?" + params : "");
        StringBuilder str = new StringBuilder();
        try {
            // 打开和URL之间的连接
            URLConnection connection = new URL(urlParams).openConnection();
            // 建立实际的连接
            connection.connect();
            //ConstantUtil.UTF_CODE 编码格式
            InputStreamReader reader = new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8"));
            char[] buff = new char[1024];
            int length = 0;
            while ((length = reader.read(buff)) != -1) {
                str.append(new String(buff, 0, length));
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return str.toString();
    }

    public static <T> T sendGet(String url, Map<String, String> param, Class<T> clazz) {
        return JSON.parseObject(sendGet(url, param), clazz);
    }
}
