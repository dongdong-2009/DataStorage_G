package com.tendency.cb.TaskEvevt;

import com.tendency.cb.util.JedisUtil;

public class RedisReConnectEvent implements Runnable
{
    JedisUtil jtemp_;

    public RedisReConnectEvent(JedisUtil j)
    {
        jtemp_ = j;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (true)
        {
            if (!jtemp_.validateObject())
            {
                try
                {
                    Thread.sleep(2 * 1000 * 60);
                    jtemp_ = null;
                    jtemp_ = new JedisUtil();
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }
    }

}
