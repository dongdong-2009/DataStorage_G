package com.tendency.cb.mogo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class tenp implements Runnable
{
    MongoDBDaoImpl M;

    public tenp(MongoDBDaoImpl M_)
    {
        M = M_;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        BasicDBObject composeCod = new BasicDBObject();
        composeCod.put("DEVID", "220199");
        composeCod.put("CMDID", "61506");

        DB db = M.getDb("tendency");
        DBCollection dbCollection = db.getCollection("tendencydata");
        DBCursor dbCursor = dbCollection.find(composeCod);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int SN1 = 0;
        while (dbCursor.hasNext())
        {
            SN1++;
            BasicDBObject bdbObj = (BasicDBObject) dbCursor.next();
            if (bdbObj != null)
            {
                //Electrombile E=new Electrombile();


                String LID = String.valueOf(bdbObj.getLong("LID"));
                String CMDID = bdbObj.getString("CMDID");
                String TEMP_ = bdbObj.getString("COMTENT");

                BasicDBObject composeCodCursor = new BasicDBObject();
                composeCodCursor.put("DEVID", "220199");
                composeCodCursor.put("CMDID", "61506");
                composeCodCursor.put("LID", bdbObj.getLong("LID"));

                System.out.print("LID:" + LID);
                //Date tempDate = bdbObj.getDate("CREATED");
                // String CREATED=formatter.format(tempDate);
                // String DEVID=bdbObj.getString("DEVID");
                // String RESERVE=bdbObj.getString("RESERVE");
                // //String RN=String.valueOf(SN1);
                // String SUBDEVTYPE=bdbObj.getString("SUBDEVTYPE");
                // String VERSION=bdbObj.getString("VERSION");
                int FileCount = (TEMP_.length() / 2) / 35;
                BasicDBObject[] B = new BasicDBObject[FileCount];
                for (int i = 0; i < FileCount; i++)
                {
                    try
                    {
                        String S1 = TEMP_;
                        DBObject basic = new BasicDBObject();

                        int index_ = i * 70;
                        String monitorTime = GetTime(TEMP_.substring(index_ + 0, index_ + 12));
                        basic.put("monitorTime", monitorTime);
                        String SN = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 12, index_ + 14).toString(), 16));
                        basic.put("SN", SN);
                        //String deviceType=Integer.toString(Integer.parseInt(TEMP_.subSequence(index_+14, index_+18).toString(), 16));
                        //  basic.put("deviceType", deviceType);

                        String DeviceID = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 18, index_ + 26).toString(), 16));
                        basic.put("DeviceID", DeviceID);

                        String commandID = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 26, index_ + 28).toString(), 16));
                        basic.put("commandID", commandID);
                        //进门
                        if (commandID.equals("19"))
                        {
                            String InRoleCount = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 28, index_ + 30).toString(), 16));
                            basic.put("InRoleCount", InRoleCount);
                            String InUltrasonicTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("InUltrasonicTime", InUltrasonicTime);
                            String DoorStopTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 32, index_ + 34).toString(), 16));
                            basic.put("DoorStopTime", DoorStopTime);
                            String InDoorTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 34, index_ + 36).toString(), 16));
                            basic.put("InDoorTime", InDoorTime);
                            String MaxHightTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 36, index_ + 38).toString(), 16));
                            basic.put("MaxHightTime", MaxHightTime);
                            String OpenFirstDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 38, index_ + 40).toString(), 16));
                            basic.put("OpenFirstDoorVibratesTime", OpenFirstDoorVibratesTime);
                            String OpenMaxDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 40, index_ + 42).toString(), 16));
                            basic.put("OpenMaxDoorVibratesTime", OpenMaxDoorVibratesTime);
                            String OpenEndDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 42, index_ + 44).toString(), 16));
                            basic.put("OpenEndDoorVibratesTime", OpenEndDoorVibratesTime);
                            String CloseFirstDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 44, index_ + 46).toString(), 16));
                            basic.put("CloseFirstDoorVibratesTime", CloseFirstDoorVibratesTime);
                            String CloseMaxDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 46, index_ + 48).toString(), 16));
                            basic.put("CloseMaxDoorVibratesTime", CloseMaxDoorVibratesTime);
                            String CloseEndDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 48, index_ + 50).toString(), 16));
                            basic.put("CloseEndDoorVibratesTime", CloseEndDoorVibratesTime);

                            String OpenDoorVibratesValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 50, index_ + 54).toString(), 16));
                            basic.put("OpenDoorVibratesValue", OpenDoorVibratesValue);
                            String CloseDoorVibratesValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 54, index_ + 58).toString(), 16));
                            basic.put("CloseDoorVibratesValue", CloseDoorVibratesValue);
                            String InDoorMaxHight = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 58, index_ + 60).toString(), 16));
                            basic.put("InDoorMaxHight", InDoorMaxHight);
                            String InDoorMaxHight_ = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 60, index_ + 62).toString(), 16));
                            basic.put("InDoorMaxHight_", InDoorMaxHight_);
                            String calibrationValve = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 62, index_ + 64).toString(), 16));
                            basic.put("calibrationValve", calibrationValve);
                            String Version = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 68, index_ + 70).toString(), 16));
                            basic.put("Version", Version);
                            DBObject basic1 = new BasicDBObject("$set", basic);
                            dbCollection.update(composeCodCursor, basic1);

                        }
                        //出门
                        if (commandID.equals("21"))
                        {
                            String OutRoleCount = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 28, index_ + 30).toString(), 16));
                            basic.put("OutRoleCount", OutRoleCount);
                            String InUltrasonicTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("InUltrasonicTime", InUltrasonicTime);
                            String DoorStopTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 32, index_ + 34).toString(), 16));
                            basic.put("DoorStopTime", DoorStopTime);

                            //String  InDoorTime=Integer.toString(Integer.parseInt(TEMP_.subSequence(index_+34, index_+36).toString(), 16));
                            //basic.put("InDoorTime", InDoorTime);
                            String MaxHightTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 34, index_ + 36).toString(), 16));
                            basic.put("MaxHightTime", MaxHightTime);
                            String OutUltrasonicTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 36, index_ + 38).toString(), 16));
                            basic.put("OutUltrasonicTime", OutUltrasonicTime);

                            String CloseFirstDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 38, index_ + 40).toString(), 16));
                            basic.put("CloseFirstDoorVibratesTime", CloseFirstDoorVibratesTime);
                            String CloseMaxDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 40, index_ + 42).toString(), 16));
                            basic.put("CloseMaxDoorVibratesTime", CloseMaxDoorVibratesTime);
                            String CloseEndDoorVibratesTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 42, index_ + 44).toString(), 16));
                            basic.put("CloseEndDoorVibratesTime", CloseEndDoorVibratesTime);
                            String CloseDoorVibratesValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 44, index_ + 48).toString(), 16));
                            basic.put("CloseDoorVibratesValue", CloseDoorVibratesValue);
                            String InDoorMaxHight = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 48, index_ + 50).toString(), 16));
                            basic.put("InDoorMaxHight", InDoorMaxHight);
                            String InDoorMaxHight_ = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 50, index_ + 52).toString(), 16));
                            basic.put("InDoorMaxHight_", InDoorMaxHight_);
                            String CalibrationLandValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 52, index_ + 54).toString(), 16));
                            basic.put("CalibrationLandValue", CalibrationLandValue);


                            String Version = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 68, index_ + 70).toString(), 16));
                            basic.put("Version", Version);
                            DBObject basic1 = new BasicDBObject("$set", basic);
                            dbCollection.update(composeCodCursor, basic1);
                        }
                        //心跳
                        if (commandID.equals("17"))
                        {
                            String EnvironmentStatus = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 28, index_ + 30).toString(), 16));
                            basic.put("EnvironmentStatus", EnvironmentStatus);
                            String CalibrationRoleValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 30, index_ + 32).toString(), 16));
                            basic.put("CalibrationRoleValue", CalibrationRoleValue);

                            String CalibrationLandValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 32, index_ + 34).toString(), 16));
                            basic.put("CalibrationLandValue", CalibrationLandValue);
                            String WorkStatus = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 34, index_ + 36).toString(), 16));
                            basic.put("WorkStatus", WorkStatus);

                            String CurTemperatureValue = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 36, index_ + 38).toString(), 16));
                            basic.put("CurTemperatureValue", CurTemperatureValue);
                            //String tobiary=Integer.toBinaryString(Integer.parseInt(CurTemperatureValue));
                            //if (Integer.parseInt(tobiary.substring(7))>0) {
                            //basic.put("CurTemperatureValue","+"+String.valueOf((Integer.parseInt(CurTemperatureValue)-128)));
                            //}
                            //else {
                            //	basic.put("CurTemperatureValue","-"+String.valueOf((Integer.parseInt(CurTemperatureValue)-128)));
                            //}
                            String Tamper = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 38, index_ + 40).toString(), 16));
                            basic.put("Tamper", Tamper);
                            String voltage = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 40, index_ + 42).toString(), 16));
                            basic.put("voltage", voltage);
                            //String  RoleCount=Integer.toString(Integer.parseInt(TEMP_.subSequence(index_+42, index_+44).toString(), 16));
                            //basic.put("RoleCount", RoleCount);
                            String HotTriggerCount = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 42, index_ + 44).toString(), 16));
                            basic.put("HotTriggerCount", HotTriggerCount);
                            String UltrasoundWorkTime = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 44, index_ + 48).toString(), 16));
                            basic.put("UltrasoundWorkTime", UltrasoundWorkTime);
                            String HotTriggerTotalCount = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 48, index_ + 52).toString(), 16));
                            basic.put("HotTriggerTotalCount", HotTriggerTotalCount);
                            String VibratesTotal = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 52, index_ + 56).toString(), 16));
                            basic.put("VibratesTotal", VibratesTotal);

                            String MouthPressCount = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 56, index_ + 58).toString(), 16));
                            basic.put("MouthPressCount", MouthPressCount);

                            String Status = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 58, index_ + 60).toString(), 16));
                            basic.put("Status", Status);

                            //String beiyong=Integer.toString(Integer.parseInt(TEMP_.subSequence(index_+60, index_+68).toString(), 16));
                            //basic.put("beiyong", beiyong);
                            String VerSion = Integer.toString(Integer.parseInt(TEMP_.subSequence(index_ + 68, index_ + 70).toString(), 16));
                            basic.put("VerSion", VerSion);
                            DBObject basic1 = new BasicDBObject("$set", basic);
                            dbCollection.update(composeCodCursor, basic1);
                        }
                        //B[i]=basic;

                    }
                    catch (Exception e)
                    {
                        // TODO: handle exception
                    }


                }


            }
        }

    }

    public String StringForm(int Num, String value)
    {
        return String.format("%0" + Num + "d", Integer.parseInt(value));
    }

    static Object GetTime_ = new Object();

    public String GetTime(String timeByte)
    {
        synchronized (GetTime_)
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
}
