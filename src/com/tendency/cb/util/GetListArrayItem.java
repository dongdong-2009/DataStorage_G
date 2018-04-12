package com.tendency.cb.util;

import java.util.ArrayList;
import java.util.List;

import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.xml.ExectStack;

public abstract class GetListArrayItem
{
    private List<String> ldtat = new ArrayList<String>();
    static ExectStack T = new ExectStack(new StackFlush());

    public abstract MongoDBDaoImpl SetMongoPool();

    public void AddItem(String LOg)
    {
        synchronized (ldtat)
        {
            if (GetListItemCount() >= Integer.parseInt(ProtocolUtils.GetConfg().PatchMongoItemCount))
            {
                //T.SetEx(SetMongoPool(),this);
            }
            ldtat.add(LOg);

        }
    }

    public List<String> GetItemListArray()
    {

        synchronized (ldtat)
        {
            List<String> tempList = new ArrayList<String>(ldtat);
            ldtat.clear();
            return ldtat;

        }
    }

    public int GetListItemCount()
    {
        synchronized (ldtat)
        {
            return ldtat.size();
        }
    }

    public List<String> CopyToList()
    {
        List<String> Temp = new ArrayList<String>();
        synchronized (ldtat)
        {
            Temp.addAll(ldtat);
            ldtat.clear();
        }
        return Temp;
    }

    public int Getsize()
    {

        synchronized (ldtat)
        {
            return ldtat.size();
        }
    }
}
