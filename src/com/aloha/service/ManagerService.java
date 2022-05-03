package com.aloha.service;

import com.aloha.entity.Manager;

/**
 * @author Aloha 2022-03-30 15:35
 */
public interface ManagerService {
    /**
     * 管理员登录
     * @param managerId 管理员账号
     * @param pwd 密码
     * @return Manager对象
     */
    Manager login(String managerId, String pwd);
}
