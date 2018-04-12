package com.tendency.cb.xml;

import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.JedisUtil;

public class CallBackOnlineStatus implements Runnable
{
    static JedisUtil JDdis_ = null;

    public CallBackOnlineStatus(JedisUtil Jdedis)
    {
        JDdis_ = Jdedis;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (true)
        {
            try
            {
                String control = JDdis_.CallBackOnlineStatusrpop("DeviceOnlineStatus").get(1);
                String[] Comment = control.split("\\|");
                for (String string : Comment)
                {
                    String[] comment = string.split("\\,");
                    if (DataCenter.ContaincallBackData(comment[0]))
                    {
                        JDdis_.lpush("FilerDeviceOnlineStatus", string);
                        System.out.println("===============回传状态到API================");
                    }

                }
            }
            catch (Exception e)
            {
                // TODO: handle exception
                System.out.println("===============回传状态到API================异常");
            }

        }
    }

}
