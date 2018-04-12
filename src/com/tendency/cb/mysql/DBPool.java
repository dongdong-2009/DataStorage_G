package com.tendency.cb.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tendency.cb.util.ProtocolUtils;
import util.ErrorInfo;
import util.Log;

public class DBPool
{
    private static Connection conn;
    private static ComboPooledDataSource ds;
    ;

//    public  static  String  Init()
//    {
//        try
//        {
//            ds = new ComboPooledDataSource();//创建连接池实例
//            ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动
//            ds.setJdbcUrl(ProtocolUtils.GetConfg().MysqlUrl);//设置连接数据库的URL
//            ds.setUser(ProtocolUtils.GetConfg().MysqlUser);//设置连接数据库的用户名
//            ds.setPassword(ProtocolUtils.GetConfg().MysqlPassWord);//设置连接数据库的密码
//            ds.setMaxPoolSize(100);//设置连接池的最大连接数
//            ds.setMinPoolSize(10);//设置连接池的最小连接数
//            ds.setInitialPoolSize(50);//设置连接池的初始连接数
//            ds.setMaxStatements(50);//设置连接池的缓存Statement的最大数
//            ds.setMaxIdleTime(10000);
//            return "0";
//        }
//        catch (Exception ex)
//        {
//
//            return ErrorInfo.GetInfo(ex);
//
//        }
//
//    }


    static
    {
        try
        {
            ds = new ComboPooledDataSource();//创建连接池实例
            ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动
            ds.setJdbcUrl(ProtocolUtils.GetConfg().MysqlUrl);//设置连接数据库的URL
            ds.setUser(ProtocolUtils.GetConfg().MysqlUser);//设置连接数据库的用户名
            ds.setPassword(ProtocolUtils.GetConfg().MysqlPassWord);//设置连接数据库的密码
            ds.setMaxPoolSize(100);//设置连接池的最大连接数
            ds.setMinPoolSize(10);//设置连接池的最小连接数
            ds.setInitialPoolSize(50);//设置连接池的初始连接数
            ds.setMaxStatements(50);//设置连接池的缓存Statement的最大数
            ds.setMaxIdleTime(10000);


        }
        catch (Exception ex)
        {

            Log.Error("Mysql初始化错误："+ErrorInfo.GetInfo(ex));

        }

    }

    //获取与指定数据库的连接
    public static ComboPooledDataSource getInstance()
    {
        return ds;
    }

    //从连接池返回一个连接
    public static Connection getConnection()
    {
        Connection conn = null;
        try
        {
            conn = ds.getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    //释放资源
    public static void realeaseResource(ResultSet rs, PreparedStatement ps, Connection conn)
    {
        if (null != rs)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (null != ps)
        {
            try
            {
                ps.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
