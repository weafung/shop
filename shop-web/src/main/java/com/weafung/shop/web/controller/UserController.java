package com.weafung.shop.web.controller;

import com.weafung.shop.model.po.User;
import com.weafung.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author weifeng
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public User get(@RequestParam("id") Integer userId) {
        return userService.getUser(userId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public int add(@RequestParam("username") String username) {
        return userService.saveUser(username);
    }
}
