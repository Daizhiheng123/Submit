package com.aloha.service.impl;

import com.aloha.dao.ManagerDao;
import com.aloha.dao.impl.ManagerDaoImpl;
import com.aloha.entity.Manager;
import com.aloha.service.ManagerService;

/**
 * @author Aloha 2022-03-30 15:37
 */
public class ManagerServiceImpl implements ManagerService {
    private static final ManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public Manager login(String managerId, String pwd) {
        return managerDao.queryByIdAndPwd(managerId, pwd);
    }
}
