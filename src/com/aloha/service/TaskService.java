package com.aloha.service;

import com.aloha.entity.Task;

import java.util.List;

/**
 * @author Aloha 2022-03-30 22:37
 */
public interface TaskService {
    /**
     * 添加任务
     *
     * @param task 任务
     */
    void addTask(Task task);

    /**
     * 修改任务状态
     *
     * @param tid   task_id
     * @param state state
     */
    void changeTask(Integer tid, Integer state);

    /**
     * 根据管理者在数据库中的主键值，来查询其所发布的所有任务
     *
     * @param id id
     * @return 任务列表
     */
    List<Task> queryTasksById(Integer id);

    /**
     * 根据管理者id和任务状态查询所有任务
     *
     * @param id    id
     * @param state state
     * @return 目标任务的集合
     */
    List<Task> queryExistTaskByIdAndState(Integer id, Integer state);

    /**
     * 根据任务状态查询数据
     *
     * @param state state
     * @return 目标任务的集合
     */
    List<Task> queryExistTaskByState(Integer state);

    Task queryOneTask(Integer id);
}
