package com.tendency.cb.util;

public class ChildCallBackUnoine implements Runnable
{
    JedisUtil J;

    public ChildCallBackUnoine(JedisUtil j)
    {
        J = j;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        try
        {

        }
        catch (Exception e)
        {
            // TODO: handle exception
            String control = J.rpop("UserStatusUnonlie").get(1);
            if (control != null)
            {
                DataCenter.removeCallBackData(control);
            }
        }

    }

}
