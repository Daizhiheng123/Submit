package com.aloha.dao.impl;

import com.aloha.dao.BaseDao;
import com.aloha.dao.MapperDao;
import com.aloha.entity.Mapper;
import com.aloha.entity.User;

import java.util.List;

/**
 * @author Aloha 2022-04-01 16:17
 */
public class MapperDaoImpl extends BaseDao<Mapper> implements MapperDao {

    @Override
    public int insertOneMapper(Mapper mapper) {
        String sql = "insert into mapper(task_id, user_id, `name`) values(?,?,?)";
        return update(sql, mapper.getTaskId(), mapper.getUserId(), mapper.getName());
    }

    @Override
    public List<Mapper> queryByTid(Integer tid) {
        String sql = "select task_id taskId, user_id userId, `name` from mapper where task_id=?";
        return queryForList(sql, tid);
    }

    @Override
    public List<Mapper> queryByUid(Integer Uid) {
        String sql = "select task_id taskId, user_id userId, `name` from mapper where user_id=?";
        return queryForList(sql, Uid);
    }

    @Override
    public Mapper queryByTidAndUid(Integer tid, Integer uid) {
        String sql = "select task_id taskId, user_id userId, `name` from mapper where task_id=? and user_id=?";
        return queryForOne(sql, tid, uid);
    }

    @Override
    public List<Mapper> queryNotSubmit(Integer id) {
        String sql = "SELECT `user`.user_id userId, `user`.`name` name FROM `user` LEFT JOIN mapper ON `user`.user_id = mapper.user_id AND mapper.task_id = ? WHERE mapper.user_id IS NULL ORDER BY `user`.user_id";
        return queryForList(sql, id);
    }
}
