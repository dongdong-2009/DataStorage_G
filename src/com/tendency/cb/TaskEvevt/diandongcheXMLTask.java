package com.tendency.cb.TaskEvevt;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tendency.cb.Info.DDCXMLInfo;
import com.tendency.cb.Info.GetLid;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.ProtocolUtils;

public class diandongcheXMLTask implements Runnable
{

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        try
        {
            ArrayList<ObjectInfo> DDCData = DDCXMLInfo.CopyList();
            Long LID_ = Long.parseLong("0");
            // GetLid.GetDdcLid(Long.parseLong(String.valueOf(DDCData.size())));
            if (DDCData.size() > 0)
            {
                MysqHelp MysqHelp_ = new MysqHelp();
                LID_ = MysqHelp_.GetCallCount("GetLID(?,?,?)", DDCData.size(), "1");
            }
            List<DBObject> LDB = new ArrayList<DBObject>();
            for (int i = 0; i < DDCData.size(); i++)
            {
                ObjectInfo TEMP_ = DDCData.get(i);
                if (TEMP_ != null)
                {
                    if (TEMP_.GetCMDID().equals("61443") || TEMP_.GetCMDID().equals("8199"))
                    {
                        int FileCount = (TEMP_.GetCOMTENT().length() / 2) / 19;
                        for (int j = 0; j < FileCount; j++)
                        {
                            LID_ += 1;
                            int index_ = j * 19;
                            DBObject basic = new BasicDBObject();
                            String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 0, index_ + 12));
                            basic.put("monitorTime", monitorTime);
                            String SN = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 12, index_ + 14).toString(), 16));
                            basic.put("SN", SN);
                            String deviceType = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 14, index_ + 18).toString(), 16));
                            basic.put("deviceType", deviceType);
                            String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 18, index_ + 20).toString(), 16));
                            basic.put("area", area);
                            String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 26).toString(), 16));
                            basic.put("itemNo", itemNo);
                            String commandID = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 26, index_ + 28).toString(), 16));
                            basic.put("commandID", commandID);
                            String province = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 28, index_ + 30).toString(), 16));
                            basic.put("province", province);
                            String city = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("city", city);
                            LDB.add(basic);
                        }
                    }
                    if (TEMP_.GetCMDID().equals("61446"))
                    {
                        int FileCount = (TEMP_.GetCOMTENT().length() / 2) / 12;
                        for (int j = 0; j < FileCount; j++)
                        {
                            LID_ += 1;
                            int index_ = j * 12;
                            DBObject basic = new BasicDBObject();
                            String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 0, index_ + 12));
                            basic.put("monitorTime", monitorTime);
                            String Mac = TEMP_.GetCOMTENT().subSequence(index_ + 12, index_ + 22).toString();
                            basic.put("Mac", Mac);
                            LDB.add(basic);
                        }
                    }
                    if (TEMP_.GetCMDID().equals("0016"))
                    {
                        int FileCount = (TEMP_.GetCOMTENT().length() / 2) / 28;
                        for (int j = 0; j < FileCount; j++)
                        {
                            LID_ += 1;
                            int index_ = j * 28;
                            DBObject basic = new BasicDBObject();
                            String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 0, index_ + 12));
                            basic.put("monitorTime", monitorTime);
                            String SN = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 12, index_ + 14).toString(), 16));
                            basic.put("SN", SN);
                            String deviceType = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 14, index_ + 18).toString(), 16));
                            basic.put("deviceType", deviceType);
                            String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 18, index_ + 20).toString(), 16));
                            basic.put("area", area);
                            String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 24).toString(), 16));
                            basic.put("itemNo", itemNo);
                            String commandID = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 24, index_ + 26).toString(), 16));
                            basic.put("commandID", commandID);
                            String province = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 26, index_ + 28).toString(), 16));
                            basic.put("province", province);
                            String city = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("city", city);
                            LDB.add(basic);

                        }
                    }
                    if (TEMP_.GetCMDID().equals("4096"))
                    {
                        byte[] data_ = ProtocolUtils.hexStringToByte(TEMP_.GetCOMTENT());
                        if (data_.length % 28 == 0)
                        {
                            int FileCount = data_.length / 28;
                            for (int j = 0; j < FileCount; j++)
                            {
                                LID_ += 1;
                                int index_ = j * 28;
                                DBObject basic = new BasicDBObject();
                                String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 0, index_ + 12));
                                basic.put("monitorTime", monitorTime);
                                String SN = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 12, index_ + 14).toString(), 16));
                                basic.put("SN", SN);
                                String deviceType = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 14, index_ + 18).toString(), 16));
                                basic.put("deviceType", deviceType);
                                String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 18, index_ + 20).toString(), 16));
                                basic.put("area", area);
                                String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 26).toString(), 16));
                                basic.put("itemNo", itemNo);
                                String commandID = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 26, index_ + 28).toString(), 16));
                                basic.put("commandID", commandID);
                                String province = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 28, index_ + 30).toString(), 16));
                                basic.put("province", province);
                                String city = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 30, index_ + 32).toString(), 16));
                                basic.put("city", city);

                                String content = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 32, index_ + 38).toString(), 16));
                                basic.put("content", content);
                                String carType = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 38, index_ + 40).toString(), 16));
                                basic.put("carType", carType);

                                String Temp_ = TEMP_.GetCOMTENT().substring(40, 48);

                                byte[] T_ = ProtocolUtils.hexStringToByte(Temp_);
                                int longitudeDecimal = T_[1] << 16 | T_[2] << 8 | T_[3];
                                String Lng = String.valueOf(T_[0]) + "." + longitudeDecimal;
                                basic.put("Lng", Lng);
                                int latitudeDecimal = T_[5] << 16 | T_[6] << 8 | T_[7];
                                String Lat = String.valueOf(T_[4]) + "." + latitudeDecimal;
                                basic.put("Lat", Lat);
                                LDB.add(basic);
                            }
                        }
                        if (data_.length % 19 == 0)
                        {
                            int FileCount = data_.length / 19;
                            for (int j = 0; j < FileCount; j++)
                            {
                                LID_ += 1;
                                int index_ = j * 19;
                                DBObject basic = new BasicDBObject();
                                String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 0, index_ + 2).toString(), 16));
                                basic.put("area", area);

                                String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 2, index_ + 8).toString(), 16));
                                basic.put("itemNo", itemNo);
                                String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 8, index_ + 20));
                                basic.put("monitorTime", monitorTime);
                                String whiteCar = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 22).toString(), 16));
                                basic.put("whiteCar", whiteCar);

                                String Temp_ = TEMP_.GetCOMTENT().substring(index_ + 22, index_ + 38);

                                byte[] T_ = ProtocolUtils.hexStringToByte(Temp_);
                                int longitudeDecimal = T_[1] << 16 | T_[2] << 8 | T_[3];
                                String Lng = String.valueOf(T_[0]) + "." + longitudeDecimal;
                                basic.put("Lng", Lng);
                                int latitudeDecimal = T_[5] << 16 | T_[6] << 8 | T_[7];
                                String Lat = String.valueOf(T_[4]) + "." + latitudeDecimal;
                                basic.put("Lat", Lat);
                                LDB.add(basic);
                            }
                        }

                    }
                    if (TEMP_.GetCMDID().equals("0007"))
                    {
                        int FileCount = (TEMP_.GetCOMTENT().length() / 2) / 11;
                        for (int j = 0; j < FileCount; j++)
                        {
                            LID_ += 1;
                            int index_ = j * 27;
                            DBObject basic = new BasicDBObject();
                            String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 0, index_ + 2).toString(), 16));
                            basic.put("area", area);

                            String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 2, index_ + 8).toString(), 16));
                            basic.put("itemNo", itemNo);
                            String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 8, index_ + 20));
                            basic.put("monitorTime", monitorTime);

                            String whiteCar = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 22).toString(), 16));
                            basic.put("whiteCar", whiteCar);
                        }
                    }
                    if (TEMP_.GetCMDID().equals("4103"))
                    {
                        int FileCount = (TEMP_.GetCOMTENT().length() / 2) / 27;
                        for (int j = 0; j < FileCount; j++)
                        {
                            LID_ += 1;
                            DBObject basic = new BasicDBObject();
                            int index_ = j * 27;
                            String monitorTime = ProtocolUtils.GetTime(TEMP_.GetCOMTENT().substring(index_ + 0, index_ + 12));
                            basic.put("monitorTime", monitorTime);
                            String SN = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 12, index_ + 14).toString(), 16));
                            basic.put("SN", SN);
                            String deviceType = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 14, index_ + 18).toString(), 16));
                            basic.put("deviceType", deviceType);
                            String area = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 18, index_ + 20).toString(), 16));
                            basic.put("area", area);
                            String itemNo = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 20, index_ + 26).toString(), 16));
                            basic.put("itemNo", itemNo);
                            String commandID = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 26, index_ + 28).toString(), 16));
                            basic.put("commandID", commandID);
                            String province = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 28, index_ + 30).toString(), 16));
                            basic.put("province", province);
                            String city = Integer.toString(Integer.parseInt(TEMP_.GetCOMTENT().subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("city", city);
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
