package com.aloha.dao;

import com.aloha.entity.Manager;

/**
 * @author Aloha 2022-03-30 15:17
 */
public interface ManagerDao {
    /**
     * 根据和密码查询记录
     * @param managerId 管理员编号
     * @param pwd 密码
     * @return Manager对象
     */
    Manager queryByIdAndPwd(String managerId, String pwd);
}
