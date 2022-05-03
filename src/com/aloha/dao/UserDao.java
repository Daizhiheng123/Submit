package com.aloha.dao;

import com.aloha.entity.User;


/**
 * @author Aloha 2022-03-29 18:50
 */
public interface UserDao {
    /**
     * 根据user_id查询数据
     *
     * @param uid 学号
     * @return User对象
     */
    User queryByUid(Integer uid);

    /**
     * 向数据库中添加一名用户
     *
     * @param user User对象
     * @return 数据库改变的行数
     */
    int saveUser(User user);

    /**
     * 根据学号和密码查询记录
     *
     * @param uid 学号
     * @param pwd 密码
     * @return User对象
     */
    User queryByUidAndPwd(Integer uid, String pwd);


}
