package com.weafung.shop.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.weafung.shop.common.constant.CodeEnum;
import com.weafung.shop.model.dto.ResponseDTO;
import com.weafung.shop.service.AdminAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminAccountService adminAccountService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization) || adminAccountService.getAdminAccount(authorization).getData() == null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseDTO.build(CodeEnum.FORBIDDEN, null)));
            writer.close();
            log.info("failed check for token: {}", authorization);
            return false;
        }
        log.info("success check for token: " + authorization);
        return true;
    }
}
