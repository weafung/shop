package com.weafung.shop.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author weifeng
 */
@Controller
@RequestMapping("/health")
@Slf4j
public class HealthController {

    @RequestMapping("/status")
    public String status() {
        log.info("success");
        return "success";
    }
}
