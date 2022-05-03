package com.aloha.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Aloha 2022-03-25 23:59
 */
public class JDBCUtils {

    private static DataSource dataSource; //数据库连接池
    private static ThreadLocal<Connection> conns = new ThreadLocal<>(); //保证一个线程一个连接
    static {
        //读取配置文件
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        try {
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库的连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = conns.get();
        if (conn == null) { //为空表示当前线程没有连接
            conn = dataSource.getConnection();  //从线程池中取出一个线程
            //将这个连接设置为手动提交
            conn.setAutoCommit(false);
            //再把这个连接存入当前线程
            conns.set(conn);
        }
        return conn;
    }

    /**
     * 将事务提交，并关闭连接
     */
    public static void submitAndClose() {
        //获得当前线程的数据库连接
        Connection conn = conns.get();
        if (conn != null) { //不为空表示当前连接操作过数据库
            //提交事务，并关闭连接
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conns.remove();//将当前连接从ThreadLocal中移除
        }
    }

    /**
     * 回滚事务，并关闭连接
     */
    public static void rollbackAndClose() {
        //获得当前线程的数据库连接
        Connection conn = conns.get();
        if (conn != null) { //不为空表示当前连接操作过数据库
            //回滚事务，并关闭连接
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conns.remove();//将当前连接从ThreadLocal中移除
        }
    }
}
