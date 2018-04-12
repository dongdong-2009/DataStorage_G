package com.tendency.cb.xml;

import java.awt.List;
import java.util.ArrayList;

import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.JedisUtil;

public class CallbackListen implements Runnable
{
    static JedisUtil JDdis_ = null;

    public CallbackListen(JedisUtil Jdedis)
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
                //String control="145#639283,639284,639289,642182,642183,642184,642185|145#639283,639284,639289,642182,642183,642184,642185";
                String control = JDdis_.CallBackrpop("DataBackList").get(1);
                DataCenter.CallBackDataClear();
                if (control.equals("null"))
                {

                }
                else
                {
                    String[] Ccontent = control.split("\\|");
                    for (String string : Ccontent)
                    {
                        String[] cconment = string.split("\\#");
                        String[] AllDeviceId = cconment[1].split("\\,");
                        java.util.List<String> S_ = new ArrayList<String>();
                        for (String string2 : AllDeviceId)
                        {
                            if (!DataCenter.ContaincallBackData(string2))
                            {
                                java.util.List<String> L = new ArrayList<String>();
                                L.add(cconment[0]);
                                DataCenter.addCallBackData(string2, L);
                            }
                            else
                            {
                                java.util.List<String> Temp = DataCenter.getCallbackdata(string2);
                                Temp.add(cconment[0]);
                            }

                        }

                    }
                }


            }
            catch (Exception e)
            {
                // TODO: handle exception
            }


        }

    }

}
