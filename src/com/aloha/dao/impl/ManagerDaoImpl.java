package com.aloha.dao.impl;

import com.aloha.dao.BaseDao;
import com.aloha.dao.ManagerDao;
import com.aloha.entity.Manager;

/**
 * @author Aloha 2022-03-30 15:26
 */
public class ManagerDaoImpl extends BaseDao<Manager> implements ManagerDao {
    @Override
    public Manager queryByIdAndPwd(String managerId, String pwd) {
        String sql = "select id, manager_id managerId from manager where manager_id=? and password=MD5(?)";
        return queryForOne(sql, managerId, pwd);
    }
}
