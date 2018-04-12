package com.tendency.cb.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.tendency.cb.util.DBLIDValue;
import com.tendency.cb.util.ProtocolUtils;
import util.ErrorInfo;
import util.Log;


public class MysqHelp
{
    static String driver = "com.mysql.jdbc.Driver";
    static String url = ProtocolUtils.GetConfg().MysqlUrl;
    static String username = ProtocolUtils.GetConfg().MysqlUser;
    static String password = ProtocolUtils.GetConfg().MysqlPassWord;
    private Lock Mysqllock = new ReentrantLock();
    static Object getConn_ = new Object();

    private Connection getConn()
    {
        synchronized (getConn_)
        {
            Connection conn = null;
            try
            {
                Class.forName(driver); //classLoader,加载对应驱动
                conn = (Connection) DriverManager.getConnection(url, username, password);
            }
            catch (ClassNotFoundException e)
            {
                Log.Error(MessageFormat.format("CMDID:8==具体操作:{0}==异常原因:{1}", "获取连接发生异常", e.getMessage()));
            }
            catch (SQLException e)
            {
                Log.Error(MessageFormat.format("CMDID:8==具体操作:{0}==异常原因:{1}", "获取连接发生异常", e.getMessage()));
            }
            return conn;
        }

    }

    static Object GetCallable_ = new Object();

    public Long GetCallable(String callname, java.util.List<String> P_)
    {
        synchronized (GetCallable_)
        {
            Connection conn = DBPool.getConnection();
            CallableStatement call = null;
            if (conn != null)
            {
                try
                {
                    call = (CallableStatement) conn.prepareCall("{call " + callname + "}");
                    int count = P_.size();
                    for (int i = 0; i < count; i++)
                    {
                        call.setString(i + 2, P_.get(i));
                    }
                    call.registerOutParameter(1, Types.INTEGER);
                    call.execute();
                    return call.getLong(1);
                }
                catch (SQLException e)
                {

                    Log.Error(MessageFormat.format("CMDID:7==具体操作:{0}==异常原因:{1}", "执行存储过程异常", e.getMessage()));
                }
                finally
                {
                    if (call != null)
                    {
                        try
                        {
                            call.close();
                            conn.close();
                        }
                        catch (SQLException e)
                        {
                            Log.Error(MessageFormat.format("CMDID:4==执行任务:获取SQL连接出错==执行结果{0}", e.getMessage()));
                        }

                    }
                }
            }


            return null;
        }

    }

    static Object GetCallCount_ = new Object();

    public Long GetCallCount(String callname, int P2_, String s_)
    {
        synchronized (GetCallCount_)
        {
            Connection conn = DBPool.getConnection();
            CallableStatement call = null;
            if (conn != null)
            {
                try
                {
                    call = (CallableStatement) conn.prepareCall("{call " + callname + "}");
                    call.setInt(2, P2_);
                    call.setString(3, s_);
                    call.registerOutParameter(1, Types.BIGINT);
                    call.execute();
                    return call.getLong(1);
                }
                catch (SQLException e)
                {

                    Log.Error(MessageFormat.format("CMDID:6==具体操作:{0}==异常原因:{1}", "MYSQL获取LD发生异常", e.getMessage()));
                }
                finally
                {
                    if (call != null)
                    {
                        try
                        {
                            call.close();
                            conn.close();
                        }
                        catch (SQLException e)
                        {

                            Log.Error(MessageFormat.format("CMDID:4==执行任务:获取SQL连接出错==执行结果{0}", e.getMessage()));
                        }

                    }
                }
            }


            return null;
        }

    }

    static Object GetFTPCount_ = new Object();

    public Long GetFTPCount(String callname, int P2_, String s_, String s2_)
    {
        synchronized (GetFTPCount_)
        {
            Connection conn = DBPool.getConnection();
            CallableStatement call = null;
            if (conn != null)
            {
                try
                {
                    call = (CallableStatement) conn.prepareCall("{call " + callname + "}");
                    call.setInt(2, P2_);
                    call.setString(3, s_);
                    call.setString(4, s2_);
                    call.registerOutParameter(1, Types.INTEGER);
                    call.execute();
                    return call.getLong(1);
                }
                catch (SQLException e)
                {

                    Log.Error(MessageFormat.format("CMDID:6==具体操作:{0}==异常原因:{1}", "MYSQL获取LD发生异常", e.getMessage()));
                }
                finally
                {
                    if (call != null)
                    {
                        try
                        {
                            call.close();
                            conn.close();
                        }
                        catch (SQLException e)
                        {

                            Log.Error(MessageFormat.format("CMDID:4==执行任务:获取SQL连接出错==执行结果{0}", e.getMessage()));
                        }

                    }
                }
            }


            return null;
        }

    }

    public String DecodeIte(String callName, String Sid, int ActiveCount, int TotalCount, String totalMemorySize_, String usedMemorySize_, int UnexteSize_, int CodeActiveSize_)
    {
        Connection conn = DBPool.getConnection();
        CallableStatement call = null;
        try
        {
            call = (CallableStatement) conn.prepareCall("{call " + callName + "}");
            call.setString(2, Sid);
            call.setInt(3, ActiveCount);
            call.setInt(4, TotalCount);
            call.setString(5, totalMemorySize_);
            call.setString(6, usedMemorySize_);
            call.setInt(7, UnexteSize_);
            call.setInt(8, CodeActiveSize_);
            call.registerOutParameter(1, Types.VARCHAR);
            call.execute();
            return call.getString(1);
        }
        catch (Exception ex)
        {

            Log.Error("CMDID:6==具体操作：MYSQL汇总线程池信息==异常原因："+ ErrorInfo.GetInfo(ex));
        }
        finally
        {
            try
            {
                if (call != null)
                {
                    call.close();
                    conn.close();
                }

            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return null;
    }

    public boolean Init()
    {
        Connection conn = DBPool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT m.BID,m.LID FROM mongodbcurlid m;");//创建数据对象
            while (rs.next())
            {
                DBLIDValue.AddDBtic(rs.getString(1), rs.getInt(2));
                Log.Info("======正在加载数据:" + rs.getString(1) + ":" + rs.getInt(2));
            }

            return true;
        }
        catch (Exception e)
        {
            // TODO: handle exception
            return false;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


}
