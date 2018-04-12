package com.tendency.cb.xml;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tendency.cb.Info.GetLid;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.mogo.MongoDBDaoImpl;

import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.GetListArrayItem;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.util.ProtocolUtils;

public class RedisTimer extends TimerTask
{

    private GetHashItem G;
    MongoDBDaoImpl MK;
    JedisUtil JEDIS_;

    public RedisTimer(GetHashItem A, MongoDBDaoImpl M, JedisUtil JEDIS)
    {
        this.G = A;
        this.MK = M;
        JEDIS_ = JEDIS;

    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        //JedisUtil.PipleOp(G);
        if (G.GetHashCount() > 0)
        {
            //new RedisTimersch(G, MK, JEDIS_);
            util.Log.Info("=========解析分类 将进行处理=======");
            DataCenter.threadPool.execute(new bll.RedisTimersch_Data(G, MK, JEDIS_));


            //ProtocolUtils.GetLog4jObject(MessageFormat.format("CMDID:3==具体操作(规定时间内批量操作):{0}","====目前运行的线程为："+DataCenter.threadPool.getExecutor().getActiveCount()+"===可使用的线程数目为："+DataCenter.threadPool.getExecutor().getPoolSize()));
        }
    }
}
