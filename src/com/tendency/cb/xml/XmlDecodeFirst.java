package com.tendency.cb.xml;

import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.tendency.cb.util.HashCeurentQueue;
import com.tendency.cb.util.ProtocolUtils;

public class XmlDecodeFirst implements Runnable
{
    GetHashItem GetHashItem_;
    private String Temp_;

    public XmlDecodeFirst(String TempData, GetHashItem G)
    {
        this.Temp_ = TempData;
        this.GetHashItem_ = G;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        StringTokenizer st = new StringTokenizer(Temp_, ",");
        while (st.hasMoreElements())
        {
            String dataString = st.nextToken();
            String Speid = dataString.substring(46, 54);
            Speid = String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
            GetHashItem_.HashInsert(Speid, dataString);
            //HashCeurentQueue.AddBlockingQueue(Speid+","+dataString);

        }
        //String[] temp__=Temp_.split("\\,");
        //for (int i = 0; i < temp__.length; i++) {
        //String Speid=temp__[i].substring(46,54);
        //Speid=String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
        //GetHashItem_.HashInsert(Speid, temp__[i]);
        //}

    }

}
