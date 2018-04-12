package com.tendency.cb.xml;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Protocol;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.tendency.cb.TaskEvevt.ActiveCallBackDataRun;
import com.tendency.cb.TaskEvevt.ExcuteQueuHttp;
import com.tendency.cb.TaskEvevt.RedisReConnectEvent;
import com.tendency.cb.TaskEvevt.ThreadPoolStatus;
import com.tendency.cb.mogo.MongoDBDao;
import com.tendency.cb.mogo.MongoDBDaoImpl;

import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.GetListArrayItem;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.util.ProtocolUtils;
import com.tendency.cb.util.ftp;
import  util.*;

public class ExcuteTaskXml implements Runnable, ExectQueueList
{
    public ExcuteTaskXml(int Version)
    {
        VerSion = Version;
    }

    int VerSion = 0;
    static JedisUtil j = null;
    extecter E = null;
    static MongoDBDaoImpl MDB = null;
    GetHashItem getListArrayItem;

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        Map<String, String> dataMap = new HashMap<String, String>();
        E = new extecter();
        j = new JedisUtil();
        String[] ccString = j.GetRedisInfo();
        MDB = new MongoDBDaoImpl();
        getListArrayItem = new GetHashItem()
        {
            @Override
            public MongoDBDaoImpl SetMongoPool()
            {
                // TODO Auto-generated method stub
                return MDB;
            }

            @Override
            public JedisUtil SetJedisUtil()
            {
                // TODO Auto-generated method stub
                return j;
            }
        };

        RedisTimer RT = new RedisTimer(getListArrayItem, MDB, j);
        Timer timer = new Timer();
        timer.schedule(RT, 10000, Long.parseLong(ProtocolUtils.GetConfg().MongoPipleTme));

        while (true)
        {

            try
            {
                if (DataCenter.CodethreadPool.getExecutor().getActiveCount() > Integer.parseInt(ProtocolUtils.GetConfg().CodethreadPoolCount))
                {
                    Log.Info("当前线程活动数:" + DataCenter.CodethreadPool.getExecutor().getActiveCount() + "====停止10秒====" + "缓存队列数据:" + DataCenter.CodethreadPool.workQueue.size());
                    Thread.sleep(1000 * 5);
                }
                else
                {
                    if (DataCenter.CodethreadPool.getExecutor().getActiveCount() > Integer.parseInt(ProtocolUtils.GetConfg().CodethreadPoolCount))
                    {

                        Log.Info("当前线程活动数:" + DataCenter.CodethreadPool.getExecutor().getActiveCount() + "====停止10秒====" + "缓存队列数据:" + DataCenter.CodethreadPool.workQueue.size());
                        Thread.sleep(1000 * 5);
                    }
                    else
                    {
                        Log.Info("当前线程活动数:" + DataCenter.CodethreadPool.getExecutor().getActiveCount() + "缓存队列数据:" + DataCenter.CodethreadPool.workQueue.size());
                        E.executeMessage(ExcuteTaskXml.this, j.rpop("RedisData").get(1));
                    }

                }

            }
            catch (Exception ex)
            {

                Log.Error("CMDID:1==具体操作：redispop数据异常 == 异常原因："+ErrorInfo.GetInfo(ex));
            }
        }
    }

    @Override
    public void Close()
    {
        // TODO Auto-generated method stub
        //j.Stop();
    }

    @Override
    public void ExectMessage(String data)
    {
        // TODO Auto-generated method stub
        if (data != null)
        {
            Log.Info("=========弹出数据源 进行处理=======");
            DataCenter.CodethreadPool.execute(new XmlDecodeFirst(data, getListArrayItem));

        }
    }

    @Override
    public void MutilMessage(List<String> data)
    {
        // TODO Auto-generated method stub

    }

}
