package com.aloha.service;

import com.aloha.entity.User;

/**
 * @author Aloha 2022-03-29 19:14
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param user User对象
     * @return 数据库中改变的行数
     */
    int register(User user);

    /**
     * 用户登录
     *
     * @param uid 学号
     * @param pwd 密码
     * @return User对象
     */
    User login(Integer uid, String pwd);

    /**
     * 根据学号判断用户是否存在
     *
     * @param uid 学号
     * @return 布尔值
     */
    boolean existUser(Integer uid);
}
