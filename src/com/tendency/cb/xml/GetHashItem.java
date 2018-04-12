package com.tendency.cb.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.util.ProtocolUtils;
import com.tendency.cb.util.StackFlush;

public abstract class GetHashItem
{
    HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
    static ExectStack T = new ExectStack(new StackFlush());

    public abstract MongoDBDaoImpl SetMongoPool();

    public abstract JedisUtil SetJedisUtil();

    static int Count_ = 0;

    public void HashInsert(String sqeid, String data)
    {
        synchronized (hashMap)
        {
            int count_ = Integer.parseInt(ProtocolUtils.GetConfg().PatchMongoItemCount);
            int ddd = GetHashCount();

            if (Count_ >= Integer.parseInt(ProtocolUtils.GetConfg().PatchMongoItemCount))
            {
                if (hashMap.containsKey(sqeid))
                {
                    List<String> Data_ = hashMap.get(sqeid);
                    Data_.add(data);
                }
                else
                {
                    List<String> Temp_ = new ArrayList<String>();
                    Temp_.add(data);
                    hashMap.put(sqeid, Temp_);
                }
                Count_ = 0;
                T.SetEx(SetMongoPool(), this, SetJedisUtil());
            }
            else
            {
                if (hashMap.containsKey(sqeid))
                {
                    List<String> Data_ = hashMap.get(sqeid);
                    Data_.add(data);
                }
                else
                {
                    List<String> Temp_ = new ArrayList<String>();
                    Temp_.add(data);
                    hashMap.put(sqeid, Temp_);
                }
            }
            Count_++;

        }
    }

    public int GetHashCount()
    {
        synchronized (hashMap)
        {
            return hashMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, List<String>> CopyHashMap()
    {
        synchronized (hashMap)
        {

            HashMap<String, List<String>> H_Temp = new HashMap<String, List<String>>();
            H_Temp = (HashMap<String, List<String>>) hashMap.clone();
            hashMap.clear();
            return H_Temp;
        }
    }


}
