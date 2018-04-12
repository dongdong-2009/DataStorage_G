package com.tendency.cb.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DataCenter
{
    private static byte[] putredisdata = "RedisData".getBytes();

    public static ThreadPool threadPool;

    public static ThreadPool CodethreadPool;

    public static ThreadPool FilethreadPool;

    public static List<String> CMDLST = new ArrayList<String>();
    public static Hashtable<String, List<String>> CallBackData = new Hashtable<String, List<String>>();
    public static boolean MongoStatus = true;

    public static synchronized byte[] PutRedisData()
    {
        return putredisdata;
    }

    private static String HttpApiUrl = "10.1.6.139:3333";

    public static synchronized String getHttpApiUrl()
    {
        return HttpApiUrl;
    }

    public static List<String> getCallbackdata(String key)
    {
        synchronized (CallBackData)
        {
            try
            {
                if (CallBackData.containsKey(key))
                {
                    return CallBackData.get(key);
                }
                else
                {
                    return null;
                }
            }
            catch (Exception e)
            {
                // TODO: handle exception
                return null;
            }
        }

    }

    //hsash control
    public static boolean addCallBackData(String key, List<String> io)
    {
        synchronized (CallBackData)
        {
            try
            {
                if (!CallBackData.containsKey(key))
                {
                    CallBackData.put(key, io);
                    return true;
                }
                else
                {
                    CallBackData.remove(key);
                    CallBackData.put(key, io);
                    return true;
                }
            }
            catch (Exception e)
            {
                // TODO: handle exception
                return false;
            }
        }

    }

    public static boolean ContaincallBackData(String key)
    {
        synchronized (CallBackData)
        {
            if (CallBackData.contains(key))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean removeCallBackData(String key)
    {
        synchronized (CallBackData)
        {
            try
            {
                if (CallBackData.containsKey(key))
                {
                    CallBackData.remove(key);
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception e)
            {
                // TODO: handle exception
                return false;
            }
        }
    }

    public static void CallBackDataClear()
    {
        synchronized (CallBackData)
        {
            CallBackData.clear();
        }
    }
}
