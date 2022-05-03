package com.aloha.service;

import com.aloha.entity.Mapper;

import java.util.List;

/**
 * @author Aloha 2022-04-01 16:31
 */
public interface MapperService {
    /**
     * 添加一条记录
     *
     * @param mapper mapper对象
     */
    void add(Mapper mapper);

    /**
     * 根据task_id查找集合
     *
     * @param id task_id
     * @return List
     */
    List<Mapper> queryMappersByTid(Integer id);

    /**
     * 根据user_id查找集合
     *
     * @param id user_id
     * @return List
     */
    List<Mapper> queryMappersByUid(Integer id);

    /**
     * 查询一条记录
     *
     * @param tid task_id
     * @param uid user_id
     * @return Mapper
     */
    Mapper queryMapper(Integer tid, Integer uid);

    /**
     * 根据给定的task_id查询没有提交任务的人员名单
     *
     * @param tid task_id
     * @return 列表
     */
    List<Mapper> queryUserNotSubmitByTid(Integer tid);
}
