package com.aloha.service.impl;

import com.aloha.dao.UserDao;
import com.aloha.dao.impl.UserDaoImpl;
import com.aloha.entity.User;
import com.aloha.service.UserService;

/**
 * @author Aloha 2022-03-29 19:14
 */
public class UserServiceImpl implements UserService {
    private static final UserDao userDao = new UserDaoImpl();

    @Override
    public int register(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(Integer uid, String pwd) {
        return userDao.queryByUidAndPwd(uid, pwd);
    }

    @Override
    public boolean existUser(Integer uid) {
        return userDao.queryByUid(uid) != null;
    }
}
