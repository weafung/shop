package com.weafung.shop.service.impl;

import com.weafung.shop.dao.UserMapper;
import com.weafung.shop.model.po.User;
import com.weafung.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(Integer userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUser(String username) {
        return userMapper.saveUser(username);
    }
}
