package com.weafung.shop.dao;

import com.weafung.shop.model.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author weifeng
 */
public interface UserMapper {
    /**
     *
     * @param id
     * @return
     */
    User findByUserId(@Param("id") Integer id);

    /**
     *
     * @param username
     * @return
     */
    int saveUser(@Param("username") String username);
}
