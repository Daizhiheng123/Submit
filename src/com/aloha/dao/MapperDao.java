package com.aloha.dao;

import com.aloha.entity.Mapper;
import com.aloha.entity.User;

import java.util.List;

/**
 * @author Aloha 2022-04-01 16:16
 */
public interface MapperDao {
    /**
     * 每位用户完成任务的上传，就插入一条记录
     * @param mapper 任务和用户的映射
     * @return 数据库改变的行数
     */
    int insertOneMapper(Mapper mapper);

    /**
     * 根据任务的id号查询映射
     * @param tid task_id
     * @return Mapper集合
     */
    List<Mapper> queryByTid(Integer tid);

    /**
     * 根据用户id查询映射
     * @param Uid userId
     * @return Mapper集合
     */
    List<Mapper> queryByUid(Integer Uid);

    /**
     * 根据任务编号和用户id查找一条记录
     * @param tid task_id
     * @param uid user_id
     * @return Mapper
     */
    Mapper queryByTidAndUid(Integer tid, Integer uid);

    /**
     * 根据给定的task_id查询没有提交任务的用户
     *
     * @param id task_id
     * @return 未提交指定任务的用户列表
     */
    List<Mapper> queryNotSubmit(Integer id);
}
