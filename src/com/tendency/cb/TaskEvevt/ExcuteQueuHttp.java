package com.tendency.cb.TaskEvevt;

import com.tendency.cb.util.HashCeurentQueue;
import com.tendency.cb.xml.FlashDataList;

public class ExcuteQueuHttp implements Runnable
{

    static Object hexStringToByte_ = new Object();

    public static byte[] hexStringToByte(String hex)
    {
        synchronized (hexStringToByte_)
        {
            int len = (hex.length() / 2);
            byte[] result = new byte[len];
            char[] achar = hex.toCharArray();
            for (int i = 0; i < len; i++)
            {
                int pos = i * 2;
                result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
            }
            return result;
        }

    }

    private static byte toByte(char c)
    {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (true)
        {
            try
            {
                int MaxCount = 0;
                String data = HashCeurentQueue.basket.take();
                String[] C = data.split(",");
                byte[] B_data = hexStringToByte(C[1]);
                FlashDataList.addF_(C[0], B_data);
            }
            catch (Exception e)
            {
                // TODO: handle exception
                System.out.println("=============转换出现异常:" + e.getMessage());
            }

        }

    }

}
