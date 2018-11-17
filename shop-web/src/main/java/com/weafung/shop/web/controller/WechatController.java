package com.weafung.shop.web.controller;

import com.weafung.shop.common.util.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author weifengshih
 */
@Controller
@RequestMapping("/api/wechat/")
@Slf4j
public class WechatController {
    @Value("${wechat.token}")
    private String TOKEN;

    @RequestMapping(value = "checkSignature", method = RequestMethod.GET)
    @ResponseBody
    public String check(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//随机字符串

        boolean checkResult = WechatUtil.checkSignature(TOKEN, signature, timestamp, nonce);
        if (checkResult) {
            return echostr;
        }
        log.warn("Invalid request for checkSignature. signature: {}, timestamp:{}, nonce:{}, echostr: {}, token: {}",
                signature, timestamp, nonce, echostr, TOKEN);
        return "Hello World!";
    }
}
