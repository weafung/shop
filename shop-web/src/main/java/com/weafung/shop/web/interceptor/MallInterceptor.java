package com.weafung.shop.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.weafung.shop.RequestHolder;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.common.util.WechatUtil;
import com.weafung.shop.common.util.wechat.AccessToken;
import com.weafung.shop.common.util.wechat.SnsapiUserinfo;
import com.weafung.shop.model.dto.AccountInfoDTO;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author weifengshih
 */
@Component
@Slf4j
public class MallInterceptor implements HandlerInterceptor {
    @Value("${wechat.appid}")
    private String APPID;
    @Value("${wechat.appsecret}")
    private String APP_SECRET;
    @Value("${mall.token_url}")
    private String MALL_TOKEN_URL;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        String code = request.getParameter("code");
        if (StringUtils.isNotBlank(code)) {
            AccessToken accessToken = WechatUtil.getAccessToken(APPID, APP_SECRET, code);
            SnsapiUserinfo snsapiUserinfo = WechatUtil.getSnsapiUserinfo(accessToken.getAccessToken(), accessToken.getOpenid());
            log.info("userInfo: {}", snsapiUserinfo);
            ResponseDTO<String> responseDTO = accountService.updateOrInsertAccountInfo(snsapiUserinfo);
            String token = responseDTO.getData();
            response.sendRedirect(String.format(MALL_TOKEN_URL, token));
            return false;
        }
        String authorization = request.getHeader("Authorization");

        ResponseDTO<AccountInfoDTO> responseDTO = accountService.getAccountInfoByToken(authorization);
        if (StringUtils.isBlank(authorization) || responseDTO == null || responseDTO.getData() == null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseDTO.build(CodeEnum.FORBIDDEN, null)));
            writer.close();
            return false;
        }
        RequestHolder.add(responseDTO.getData());

        log.info("success check for token: " + authorization);
        return true;
    }
}
