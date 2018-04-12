package com.tendency.cb.xml;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.tendency.cb.Info.DDCXMLInfo;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.TaskEvevt.diandongcheXMLTask;
import com.tendency.cb.mogo.MongoDBDaoImpl;

import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.ChildCallBackObject;
import com.tendency.cb.util.ChildCallbackListen;
import com.tendency.cb.util.DBLIDValue;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.GetListArrayItem;
import com.tendency.cb.util.HashCeurentQueue;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.util.ProtocolUtils;

import util.Log;

public class RedisTimersch implements Runnable
{
    private GetHashItem G;
    MongoDBDaoImpl MK;
    JedisUtil J;
    Long CurrentCount = Long.parseLong("0");

    public RedisTimersch(GetHashItem A, MongoDBDaoImpl M, JedisUtil J)
    {
        this.G = A;
        this.MK = M;
        this.J = J;

    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        try
        {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            Calendar ca = Calendar.getInstance();
            ca.setTime(new Date());
            Date currnrtTime = ca.getTime();
            HashMap<String, List<String>> G1 = G.CopyHashMap();
            //Long CurrentCount=GetLid.GetLids(Long.parseLong(String.valueOf(G1.size())));
            //Long CurrentCount=JEDIS_.MutilSet(Long.parseLong(String.valueOf(G1.size())));
            long starTime = System.currentTimeMillis();
            if (G1.size() > 0)
            {
                List<DBObject> LDB = new ArrayList<DBObject>();
                //List<String> DList=new ArrayList<String>();
                MysqHelp MysqHelp_ = new MysqHelp();
                Iterator<Entry<String, List<String>>> iterator = G1.entrySet().iterator();
                String speid_ = "";
                long Time1 = 0;
                int count_ = 0;
                while (iterator.hasNext())
                {
                    Long CurrentCount = Long.parseLong("0");
                    Entry<String, List<String>> entry = iterator.next();
                    List<String> listDataList = entry.getValue();
                    String speid = entry.getKey();

                    //speid_=speid;
                    //count_=listDataList.size();
                    //DList.add(speid_+","+count_);
                    //CurrentCount=(long)DBLIDValue.GetValue(speid, listDataList.size());
                    long starTime1 = System.currentTimeMillis();
                    CurrentCount = MysqHelp_.GetCallCount("GetLID(?,?,?)", listDataList.size(), speid);
                    long endTime1 = System.currentTimeMillis();
                    Time1 = endTime1 - starTime1;
                    //System.out.println("插入时间为:"+Time1);
                    for (String data : listDataList)
                    {
                        try
                        {
                            //data="DC0000007C0100000039C1090001000702000000420295CD0200000B000720100C04112B210080010C000CA4010101012F091102041121331D80010C0024BF010101012F091102041120330880010100688F010101012F09110204111C0800800101025661010101012F09110204111C0901800101000F48010101012F09110204111C0902800101011806010101012F09110204111C090380010101FF48010101012F09110204111C090480010100C78F010101012F09100C041130071C80010C00005F010101012F09100C041131081C80010C00005F010101012F09100C04112F071580010003A1B9010101012F09110204111C0A0580010101FF0B010101012F09100C04112903018001010252D3010101012F09110204111C0C06800101006836010101012F09110204111C0C07800101001CA9010101012F09110204111C0D0880010000C436010101012F09100C041126200A800100033E65010101012F09110204111C1903800101000816010101012F09110204111D0A1280010102A37E010101012F09110204111D0C0780010101FA55010101012F09100C0411260204800101024DF1010101012F09100C0411252F11800101020329010101012F09100C041125201080010001AE6A010101012F09100C04112C000180010C00005F010101012F09110204111E161A800100008BCB010101012F09110204111D161880010C00005F010101012F09100C04112D2D198001020348A5010101012F09100C04112D2E1C8001010216EB010101012F0911020411201D0880010100688F010101012F0911020411201D0980010202EA00010101012F09612A";

                            DBObject basic = new BasicDBObject();
                            final ObjectInfo OI = new ObjectInfo();
                            CurrentCount += 1;
                            OI.SetLid(CurrentCount);
                            basic.put("LID", CurrentCount);

                            //OI.SetCREATED(currnrtTime);
                            basic.put("CREATED", currnrtTime);

                            String SN = data.substring(12, 16);
                            //SN=String.valueOf( ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(SN)));
                            OI.SetRN(SN);
                            basic.put("RN", SN);

                            String deviceid = data.substring(18, 26);
                            int deviceid_ = (ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(deviceid)));
                            OI.SetDEVID(deviceid_);
                            basic.put("DEVID", deviceid_);

                            //String Commandid=data.substring(28, 32);
                            //String command=String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Commandid)));

                            String Reserve = data.substring(32, 40);
                            //Reserve=String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Reserve)));
                            basic.put("RESERVE", Reserve);


                            String SUBDEVTYPE = Reserve.substring(4, 8);
                            basic.put("SUBDEVTYPE", SUBDEVTYPE);
                            OI.SetSUBDEVTYPE(SUBDEVTYPE);


                            String ContentSize = data.substring(40, 44);
                            int Datasize = ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(ContentSize));
                            int SunTruct = data.length() - 50;
                            if (Datasize * 2 == SunTruct)
                            {
                                if (Datasize > 8)
                                {
                                    // byte[] Content = new byte[Datasize];
                                    String Content = data.substring(46, 46 + Datasize * 2);
                                    String Speid = Content.substring(0, 8);
                                    Speid = String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
                                    basic.put("SPEID", Speid);
                                    OI.SetSPEID(Speid);


                                    String Version = Content.substring(8, 12);
                                    String Version1 = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Version)));
                                    basic.put("VERSION", Version1);
                                    OI.SetVERSION(Version);

                                    String subcommadn = Content.substring(12, 16);
                                    String subcommadn1 = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subcommadn)));
                                    basic.put("CMDID", subcommadn1);
                                    OI.SetCMDID(subcommadn1);
                                    String Comment = Content.substring(16, Datasize * 2);
                                    basic.put("COMTENT", Comment);
                                    OI.SetCOMTENT(Comment);

                                    // List<String> Value=DataCenter.getCallbackdata(deviceid);
                                    //if (Value!=null) {
                                    // for (String string : Value) {

                                    // ChildCallbackListen.AddChileCallBack(Integer.parseInt(string)+","+deviceid+","+Version+subcommadn+Comment);
                                    //}
                                    //}
                                    // BasicDBObject[] B= new StringDecode() {
                                    //@Override
                                    //public ObjectInfo TEMP_() {

                                    //return OI;
                                    //}
                                    //}.GetStringDecode();
                                    //if (B!=null) {
                                    //basic.put("JSON", B);
                                    //}
                                    LDB.add(basic);

                                    //if (DataCenter.CMDLST.contains(subcommadn)) {
                                    /// DDCXMLInfo.AddItem(OI);
                                    /// new test(OI);
                                    //}
                                }
                                else
                                {
                                    //String Content=data.substring(46, 46+Datasize*2);
                                    // String Speid=Content.substring(0, 8);
                                    /// basic.put("SPEID", String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid))));
                                    // String Version=Content.substring(8, 12);
                                    // basic.put("VERSION", String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Version))));
                                    // String subcommadn=Content.substring(12, 16);
                                    // basic.put("CMDID", String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subcommadn))));
                                    // basic.put("COMTENT", null);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                                     
                            
                            Log.Error(MessageFormat.format("CMDID:2==具体操作:{0}==异常原因:{1}==原始数据:{2}", "解析异常(数据段异常)", e.getCause(), data));
                        }
                    }
                }
                if (LDB.size() > 0)
                {
                    try
                    {
                        if (CurrentCount != 9999)
                        {
                            long endTime2 = System.currentTimeMillis();
                            DB db = MK.GetMongoClient().getDB("tendency");
                            DBCollection dbCollection = db.getCollection("tendencydata");
                            WriteResult W = dbCollection.insert(LDB);
                            long endTime = System.currentTimeMillis();

                            long Time = endTime - starTime;
                            long Time2 = endTime - endTime2;
                            DataCenter.MongoStatus = true;
                                         
                            Log.Info(MessageFormat.format("CMDID:10==具体操作:{0}==数量:{1}==执行时间:{2}===MYSQL执行时间:{3}==mongodb插入时间:{4}", "批量插入Mongo", LDB.size(), Time, Time1, Time2));
                            LDB.clear();
                            LDB = null;
                        }
                        else
                        {
                            long endTime2 = System.currentTimeMillis();
                            DB db = MK.GetMongoClient().getDB("tendency");
                            DBCollection dbCollection = db.getCollection("tendencydatabak");
                            WriteResult W = dbCollection.insert(LDB);
                            long endTime = System.currentTimeMillis();

                            long Time = endTime - starTime;
                            long Time2 = endTime - endTime2;
                            DataCenter.MongoStatus = true;
                         
                            
                            Log.Info(MessageFormat.format("CMDID:10==具体操作:{0}==数量:{1}==执行时间:{2}===MYSQL执行时间:{3}==mongodb插入时间:{4}", "批量插入Mongo", LDB.size(), Time, Time1, Time2));
                            LDB.clear();
                            LDB = null;
                        }
                    }
                    catch (Exception e)
                    {
                     
                        Log.Error(MessageFormat.format("CMDID:9==具体操作:{0}==异常原因:{1}", "批量插入Mongo异常", e.getMessage()));
                        DataCenter.MongoStatus = false;
                    }
                    //MK.PipeWatch("tendency", "tendencydata", LDB,currnrtTime);
                }


            }
        }
        catch (Exception e)
        {
          
            
            Log.Error(MessageFormat.format("CMDID:4==具体操作:{0}==异常原因:{1}", "解析异常", e.getMessage()));
        }
    }

    public String GetTime(String timeByte)
    {

        try
        {
            int o1 = Integer.parseInt(timeByte.subSequence(2, 4).toString(), 16);
            String t1 = String.format("%0" + 2 + "d", o1);
            int o2 = Integer.parseInt(timeByte.subSequence(4, 6).toString(), 16);
            String t2 = String.format("%0" + 2 + "d", o2);
            int o3 = Integer.parseInt(timeByte.subSequence(6, 8).toString(), 16);
            String t3 = String.format("%0" + 2 + "d", o3);
            int o4 = Integer.parseInt(timeByte.subSequence(8, 10).toString(), 16);
            String t4 = String.format("%0" + 2 + "d", o4);
            int o5 = Integer.parseInt(timeByte.subSequence(10, 12).toString(), 16);
            String t5 = String.format("%0" + 2 + "d", o5);
            String Dt = String.valueOf(2000 + Integer.parseInt(timeByte.substring(0, 2).toString(), 16)) + "-" + t1 + "-" + t2 + " " + t3 + ":" + t4 + ":" + t5;

            return Dt;

        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        return "";


    }
}
