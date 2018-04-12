package com.tendency.cb.TaskEvevt;

import com.tendency.cb.util.ChildCallbackListen;
import com.tendency.cb.util.JedisUtil;

public class ActiveCallBackDataRun implements Runnable
{
    JedisUtil J = null;

    public ActiveCallBackDataRun(JedisUtil JedisUtil_)
    {
        this.J = JedisUtil_;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (true)
        {
            try
            {
                Thread.sleep(1000 * 10);
                String Temp_ = ChildCallbackListen.CopeChildCallBackObject();
                if (Temp_.length() > 0)
                {
                    J.lpush("CallBackDataStatus", Temp_);

                    System.out.println("===============回传数据到API================");
                }


            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
