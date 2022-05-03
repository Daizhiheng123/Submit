package com.aloha.dao.impl;

import com.aloha.dao.BaseDao;
import com.aloha.dao.TaskDao;
import com.aloha.entity.Task;

import java.util.List;

/**
 * @author Aloha 2022-03-30 22:24
 */
public class TaskDaoImpl extends BaseDao<Task> implements TaskDao {
    @Override
    public int insertTask(Task task) {
        String sql = "insert into task(manager_id, state, `describe`) values(?,?,?)";
        return update(sql, task.getManagerId(), task.getState(), task.getDescribe());
    }

    @Override
    public List<Task> queryTasksByMid(Integer id) {
        String sql = "select task_id taskId, manager_id managerId, state,`describe` from task where manager_id=?";
        return queryForList(sql, id);
    }

    @Override
    public List<Task> queryTasksByMidAndState(Integer id, Integer state) {
        String sql = "select task_id taskId, manager_id managerId, state, `describe` from task where manager_id=? and state=?";
        return queryForList(sql, id, state);
    }

    @Override
    public List<Task> queryTasksByState(Integer state) {
        String sql = "select task_id taskId, manager_id managerId, state, `describe` from task where state=?";
        return queryForList(sql, state);
    }

    @Override
    public int changeTask(Integer tid, Integer state) {
        String sql = "update task set state = ? where task_id = ?";
        return update(sql, state, tid);
    }

    @Override
    public Task queryOneTask(Integer tid) {
        String sql = "select task_id taskId, describe from task where task_id=?";
        return  queryForOne(sql, tid);
    }
}
