package com.tendency.cb.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tendency.cb.TaskEvevt.ByteTOFile;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;

public class FlashDataList
{

    static HashMap<String, List<byte[]>> hashMap = new HashMap<String, List<byte[]>>();
    //static ExectStack exectStack=new ExectStack(new StackTimeRead());
    static int Count_ = 0;

    public static boolean addF_(String sqeid, byte[] s_)
    {
        synchronized (hashMap)
        {
            if (Count_ > Integer.parseInt(ProtocolUtils.GetConfg().PatchMongoItemCount))
            {
                if (hashMap.containsKey(sqeid))
                {
                    List<byte[]> Data_ = hashMap.get(sqeid);
                    Data_.add(s_);
                }
                else
                {
                    List<byte[]> Temp_ = new ArrayList<byte[]>();
                    Temp_.add(s_);
                    hashMap.put(sqeid, Temp_);
                }
                Count_ = 0;
                DataCenter.FilethreadPool.execute(new ByteTOFile());
                //exectStack.SetEx();
            }
            else
            {
                if (hashMap.containsKey(sqeid))
                {
                    List<byte[]> Data_ = hashMap.get(sqeid);
                    Data_.add(s_);
                }
                else
                {
                    List<byte[]> Temp_ = new ArrayList<byte[]>();
                    Temp_.add(s_);
                    hashMap.put(sqeid, Temp_);
                }
            }
            Count_++;
            return true;

        }
    }

    public static int GetCount()
    {
        synchronized (hashMap)
        {
            return hashMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, List<byte[]>> CopyHashMap_()
    {
        synchronized (hashMap)
        {

            HashMap<String, List<byte[]>> H_Temp = new HashMap<String, List<byte[]>>();
            H_Temp = (HashMap<String, List<byte[]>>) hashMap.clone();
            hashMap.clear();
            return H_Temp;
        }
    }
}
