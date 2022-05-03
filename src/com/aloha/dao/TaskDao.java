package com.aloha.dao;

import com.aloha.entity.Task;

import java.util.List;

/**
 * @author Aloha 2022-03-30 22:17
 */
public interface TaskDao {
    /**
     * 向task表中插入一条数据
     *
     * @param task 待提交的任务
     * @return 返回数据库改变的行数
     */
    int insertTask(Task task);

    /**
     * 根据发布任务的管理者的id查询所有的任务
     *
     * @param id id
     * @return 所有的任务
     */
    List<Task> queryTasksByMid(Integer id);

    /**
     * 按照管理者id和任务的state查询
     *
     * @param id    id
     * @param state 0 或者 1
     * @return 目标任务集合
     */
    List<Task> queryTasksByMidAndState(Integer id, Integer state);

    /**
     * 按照任务的状态查找任务
     * @param state 0 或者 1
     * @return 任务列表
     */
    List<Task> queryTasksByState(Integer state);

    /**
     * 根据task_id修改任务的状态
     *
     * @param tid   task_id
     * @param state 0 或者 1
     * @return 数据库改变的行数
     */
    int changeTask(Integer tid, Integer state);

    /**
     * 根据task_id查询一条task
     * @param tid task_id
     * @return Task对象
     */
    Task queryOneTask(Integer tid);
}
