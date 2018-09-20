package com.weafung.shop.service;

import com.weafung.shop.model.po.User;

public interface UserService {
    User getUser(Integer userId);
    int saveUser(String username);
}
