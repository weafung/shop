package com.weafung.shop.web.controller;

import com.google.common.collect.Lists;
import com.weafung.shop.common.util.GoodsIdGeneratorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("id")
    @ResponseBody
    public List<Long> getIds() throws InterruptedException {
        List<Long> longs = Lists.newArrayList();
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                for (int j = 1; j < 100; j++) {
                    longs.add(GoodsIdGeneratorUtil.getNextId());
                }
            }).start();
        }
        Thread.sleep(5000L);
        return longs;
    }
}
