package com.aloha.dao.impl;

import com.aloha.dao.BaseDao;
import com.aloha.dao.UserDao;
import com.aloha.entity.User;

import java.util.List;

/**
 * @author Aloha 2022-03-29 18:51
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User queryByUid(Integer uid) {
        String sql = "select id, user_id userId, name from user where user_id=?";
        return queryForOne(sql, uid);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(user_id, name, password) values(?,?,MD5(?))";
        return update(sql, user.getUserId(), user.getName(), user.getPassword());
    }

    @Override
    public User queryByUidAndPwd(Integer uid, String pwd) {
        String sql = "select id, user_id userId, name from user where user_id=? and password=MD5(?)";
        return queryForOne(sql, uid, pwd);
    }


}
