package com.tendency.cb.TaskEvevt;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;
import  util.*;

public class ThreadPoolStatus implements Runnable
{

    @Override
    public void run()
    {

        MysqHelp mysqHelp_ = new MysqHelp();
        while (true)
        {
            try
            {
                Thread.sleep(1000 * 60 * 2);
                MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
                MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
                long totalMemorySize = memoryUsage.getInit(); //初始的总内存
                long maxMemorySize = memoryUsage.getMax(); //最大可用内存
                long usedMemorySize = memoryUsage.getUsed(); //已使用的内存
                mysqHelp_.DecodeIte("t_decode_updata_status(?,?,?,?,?,?,?,?)", ProtocolUtils.GetConfg().ID, DataCenter.threadPool.getExecutor().getActiveCount(), DataCenter.threadPool.getExecutor().getPoolSize(), String.valueOf(maxMemorySize), String.valueOf(usedMemorySize), DataCenter.threadPool.workQueue.size(), DataCenter.CodethreadPool.getExecutor().getActiveCount());
            }
            catch (Exception ex)
            {
                // TODO: handle exception
                Log.Error("调用Mysql（t_decode_updata_status过程）错误："+ErrorInfo.GetInfo(ex));

            }

        }
    }

}
