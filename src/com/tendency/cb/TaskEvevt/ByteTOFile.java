package com.tendency.cb.TaskEvevt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.ProtocolUtils;
import com.tendency.cb.xml.FlashDataList;

public class ByteTOFile implements Runnable
{

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        MysqHelp mysqHelp = new MysqHelp();
        try
        {
            long starTime = System.currentTimeMillis();
            HashMap<String, List<byte[]>> Hash = FlashDataList.CopyHashMap_();
            Iterator<Entry<String, List<byte[]>>> iterator = Hash.entrySet().iterator();
            while (iterator.hasNext())
            {
                Entry<String, List<byte[]>> entry = iterator.next();
                List<byte[]> listDataList = entry.getValue();
                String speid = entry.getKey();
                Calendar now = Calendar.getInstance();
                String d = new SimpleDateFormat("yyyy").format(now.getTime());
                String d2 = new SimpleDateFormat("MM").format(now.getTime());
                String d3 = new SimpleDateFormat("dd").format(now.getTime());
                String dataString = d + d2 + d3;
                getFile(sysCopy(listDataList), ProtocolUtils.GetConfg().FTPURL + dataString + "/" + speid, mysqHelp.GetFTPCount("GetFtpLid(?,?,?,?)", 1, dataString, speid) + "_" + listDataList.size() + ".tdr");
            }
            long endTime = System.currentTimeMillis();
            long Time = endTime - starTime;
            System.out.println("========生成文件花费时间:" + Time + "=========Length:" + Hash.size() + "=======");
        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("========生成文件异常:" + e.getMessage() + "=======");
        }


    }

    public static byte[] sysCopy(List<byte[]> srcArrays)
    {
        int len = 0;
        for (byte[] srcArray : srcArrays)
        {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays)
        {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory())
            {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

}
