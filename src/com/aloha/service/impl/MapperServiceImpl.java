package com.aloha.service.impl;

import com.aloha.dao.MapperDao;
import com.aloha.dao.impl.MapperDaoImpl;
import com.aloha.entity.Mapper;
import com.aloha.service.MapperService;

import java.util.List;

/**
 * @author Aloha 2022-04-01 16:33
 */
public class MapperServiceImpl implements MapperService {
    private final MapperDao mapperDao = new MapperDaoImpl();
    @Override
    public void add(Mapper mapper) {
        mapperDao.insertOneMapper(mapper);
    }

    @Override
    public List<Mapper> queryMappersByTid(Integer id) {
        return mapperDao.queryByTid(id);
    }

    @Override
    public List<Mapper> queryMappersByUid(Integer id) {
        return mapperDao.queryByUid(id);
    }

    @Override
    public Mapper queryMapper(Integer tid, Integer uid) {
        return mapperDao.queryByTidAndUid(tid, uid);
    }

    @Override
    public List<Mapper> queryUserNotSubmitByTid(Integer tid) {
        return mapperDao.queryNotSubmit(tid);
    }
}
