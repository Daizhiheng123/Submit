package com.aloha.dao;
//test push
import com.aloha.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Aloha 2022-03-29 18:21
 */
public abstract class BaseDao<T> {
    private Class<T> clazz;
    private QueryRunner runner = new QueryRunner(); //对数据库进行操作的工具类

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * 对数据库的增删改操作
     *
     * @param sql SQL语句
     * @param args 参数
     * @return 返回数据库改变的行数，返回 -1 表示失败
     */
    public int update(String sql, Object... args) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            int ret = runner.update(conn, sql, args);
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一条记录
     *
     * @param sql SQL语句
     * @param args 参数
     * @return 封装好的对象
     */
    public T queryForOne(String sql, Object... args) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条记录
     *
     * @param sql SQL语句
     * @param args 参数
     * @return 从数据库中查询到的结果的列表
     */
    public List<T> queryForList(String sql, Object... args) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询类似于最大的，最小的，平均的，总和，个数相关的数据
     * @param sql SQL语句
     * @param args 参数
     * @return object
     */
    public Object queryForSingleValue(String sql, Object... args) {
        try {
            Connection conn = JDBCUtils.getConnection();
            return runner.query(conn, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
