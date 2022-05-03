package com.aloha.service.impl;

import com.aloha.dao.TaskDao;
import com.aloha.dao.impl.TaskDaoImpl;
import com.aloha.entity.Task;
import com.aloha.service.TaskService;

import java.util.List;

/**
 * @author Aloha 2022-03-30 22:37
 */
public class TaskServiceImpl implements TaskService {
    private static final TaskDao taskdao = new TaskDaoImpl();

    @Override
    public void addTask(Task task) {
        taskdao.insertTask(task);
    }

    @Override
    public void changeTask(Integer tid, Integer state) {
        taskdao.changeTask(tid, state);
    }

    @Override
    public List<Task> queryTasksById(Integer id) {
        return taskdao.queryTasksByMid(id);
    }

    @Override
    public List<Task> queryExistTaskByIdAndState(Integer id, Integer state) {
        return taskdao.queryTasksByMidAndState(id, state);
    }

    @Override
    public List<Task> queryExistTaskByState(Integer state) {
        return taskdao.queryTasksByState(state);
    }

    @Override
    public Task queryOneTask(Integer id) {
        return taskdao.queryOneTask(id);
    }
}
