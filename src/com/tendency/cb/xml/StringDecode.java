package com.tendency.cb.xml;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.util.ProtocolUtils;

public abstract class StringDecode{
	public abstract ObjectInfo TEMP_();
	public  BasicDBObject[] GetStringDecode()
	{
		if (TEMP_().GetCMDID().equals("61443")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/19;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j < FileCount; j++) {
				int index_=j*38;
				BasicDBObject basic = new BasicDBObject();
				//DBObject basic = new BasicDBObject(); 
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
				basic.put("monitorTime", monitorTime);
				String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
				basic.put("SN", SN);
			    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14, index_+18).toString(), 16));
			    basic.put("deviceType", deviceType);
				String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+20).toString(), 16));
				basic.put("area", area);
				String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+26).toString(), 16));
				basic.put("itemNo", itemNo);
				String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
				basic.put("commandID", commandID);
				String province=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
				basic.put("province", province);
				String city=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
				basic.put("city", city);
				B[j]=basic;
				
			}
			return B;
		}
		if (TEMP_().GetCMDID().equals("8199")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/19;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j < FileCount; j++) {
				int index_=j*38;
				BasicDBObject basic = new BasicDBObject();
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
				basic.put("monitorTime", monitorTime);
				String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
				basic.put("SN", SN);
			    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14, index_+18).toString(), 16));
			    basic.put("deviceType", deviceType);
				String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+20).toString(), 16));
				basic.put("area", area);
				String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+26).toString(), 16));
				basic.put("itemNo", itemNo);
				String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
				basic.put("commandID", commandID);
				String province=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
				basic.put("province", province);
				String city=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
				basic.put("city", city);
				B[j]=basic;
			}
			return B;
		}
		if (TEMP_().GetCMDID().equals("61446")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/12;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j < FileCount; j++) {
				int index_=j*24;
				BasicDBObject basic = new BasicDBObject();
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
				basic.put("monitorTime", monitorTime);
				String Mac=TEMP_().GetCOMTENT().subSequence(index_+12, index_+22).toString();
				basic.put("Mac", Mac);
				B[j]=basic;
			}
			return B;
		}
		if (TEMP_().GetCMDID().equals("16")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/28;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j <FileCount; j++) {				
				int index_=j*56;
				BasicDBObject basic = new BasicDBObject(); 
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0,index_+ 12));
				basic.put("monitorTime", monitorTime);
				String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
				basic.put("SN", SN);
			    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14,index_+ 18).toString(), 16));
			    basic.put("deviceType", deviceType);
				String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+20).toString(), 16));
				basic.put("area", area);
				String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+24).toString(), 16));
				basic.put("itemNo", itemNo);
				String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+24, index_+26).toString(), 16));
				basic.put("commandID", commandID);
				String province=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
				basic.put("province", province);
				String city=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
				basic.put("city", city);
				B[j]=basic;
			}
			return B;
		}
		if (TEMP_().GetCMDID().equals("4096")) {
			if (TEMP_().GetCOMTENT().length()%56==0) {
				int FileCount=TEMP_().GetCOMTENT().length()/56;
				BasicDBObject[] B = new BasicDBObject[FileCount];
				for (int j = 0; j <FileCount; j++) {
					int index_=j*56;
					BasicDBObject basic = new BasicDBObject(); 
					String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
					basic.put("monitorTime", monitorTime);
					String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
					basic.put("SN", SN);
				    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14, index_+18).toString(), 16));
				    basic.put("deviceType", deviceType);
					String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+20).toString(), 16));
					basic.put("area", area);
					String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+26).toString(), 16));
					basic.put("itemNo", itemNo);
					String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
					basic.put("commandID", commandID);
					String province=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
					basic.put("province", province);
					String city=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
					basic.put("city", city);
					
					String content=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+38).toString(), 16));
					basic.put("content", content);
					String carType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
					basic.put("carType", carType);
					
					String TEMP_=TEMP_().GetCOMTENT().substring(40, 48);
					
					byte[]T_=ProtocolUtils.hexStringToByte(TEMP_);
					int longitudeDecimal=T_[1]<<16|T_[2]<<8|T_[3];
					String Lng=String.valueOf(T_[0])+"."+longitudeDecimal;
					basic.put("Lng", Lng);
					int latitudeDecimal=T_[5]<<16|T_[6]<<8|T_[7];
					String Lat=String.valueOf(T_[4])+"."+latitudeDecimal;
					basic.put("Lat", Lat);
				
					B[j]=basic;
				}
				return B;
			}
			if (TEMP_().GetCOMTENT().length()%38==0) {
				int FileCount=TEMP_().GetCOMTENT().length()/38;
				BasicDBObject[] B = new BasicDBObject[FileCount];
				for (int j = 0; j <FileCount; j++) {
			    int index_=j*38;
			    BasicDBObject basic = new BasicDBObject(); 
				String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+0, index_+2).toString(), 16));
				basic.put("area", area);
				
				String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+2, index_+8).toString(), 16));
				basic.put("itemNo", itemNo);
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+8, index_+20));
				basic.put("monitorTime", monitorTime);
				String whiteCar=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+22).toString(), 16));
				basic.put("whiteCar", whiteCar);
				
				String TEMP_=TEMP_().GetCOMTENT().substring(index_+22, index_+38);
				
				byte[]T_=ProtocolUtils.hexStringToByte(TEMP_);
				int longitudeDecimal=T_[1]<<16|T_[2]<<8|T_[3];
				String Lng=String.valueOf(T_[0])+"."+longitudeDecimal;
				basic.put("Lng", Lng);
				int latitudeDecimal=T_[5]<<16|T_[6]<<8|T_[7];
				String Lat=String.valueOf(T_[4])+"."+latitudeDecimal;
				basic.put("Lat", Lat);
				B[j]=basic;
				}
				return B;
			}
			
		}
		if (TEMP_().GetCMDID().equals("7")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/11;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j <FileCount; j++) {
				
				int index_=j*22;
				BasicDBObject basic = new BasicDBObject(); 
				String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+0, index_+2).toString(), 16));
				basic.put("area", area);
				
				String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+2, index_+8).toString(), 16));
				basic.put("itemNo", itemNo);
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+8, index_+20));
				basic.put("monitorTime", monitorTime);
				
				String whiteCar=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+22).toString(), 16));
				basic.put("whiteCar", whiteCar);
				B[j]=basic;
			}
			return B;
		}
		if (TEMP_().GetCMDID().equals("4103")) {
			int FileCount=(TEMP_().GetCOMTENT().length()/2)/27;
			BasicDBObject[] B = new BasicDBObject[FileCount];
			for (int j = 0; j < FileCount; j++) {
				
				BasicDBObject basic = new BasicDBObject(); 
				int index_=j*54;
				String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
				basic.put("monitorTime", monitorTime);
				String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
				basic.put("SN", SN);
			    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14, index_+18).toString(), 16));
			    basic.put("deviceType", deviceType);
			    String area=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+20).toString(), 16));
				basic.put("area", area);
			    String itemNo=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+20, index_+26).toString(), 16));
				basic.put("itemNo", itemNo);
				String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
				basic.put("commandID", commandID);
				String province=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
				basic.put("province", province);
				String city=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
				basic.put("city", city);
				B[j]=basic;
			}
			return B;
		}
		
		if (TEMP_().GetCMDID().equals("61506")) {
		
				int FileCount=(TEMP_().GetCOMTENT().length()/2)/35;
				BasicDBObject[] B = new BasicDBObject[FileCount];
				for (int i = 0; i < FileCount; i++) {
					try {
						String S1=TEMP_().GetCOMTENT();
						BasicDBObject basic = new BasicDBObject(); 
						int index_=i*70;
						String monitorTime=GetTime(TEMP_().GetCOMTENT().substring(index_+0, index_+12));
						basic.put("monitorTime", monitorTime);
						String SN=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+12, index_+14).toString(), 16));
						basic.put("SN", SN);
					    String deviceType=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+14, index_+18).toString(), 16));
					    basic.put("deviceType", deviceType);
					   
					    String DeviceID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+18, index_+26).toString(), 16));
						basic.put("DeviceID", DeviceID);
						//mac地址采集
						if (deviceType.equals("4097")) {
							
						}
						if (deviceType.equals("1041")) {
							String commandID=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+26, index_+28).toString(), 16));
							basic.put("commandID", commandID);
							//进门
							if (commandID.equals("19")) {
								int devie=TEMP_().GetDEVID();
								String InRoleCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("InRoleCount", InRoleCount);
								String  InUltrasonicTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
								basic.put("InUltrasonicTime", InUltrasonicTime);
								String  DoorStopTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+34).toString(), 16));
								basic.put("DoorStopTime", DoorStopTime);
								String  InDoorTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
								basic.put("InDoorTime", InDoorTime);
								String  MaxHightTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+36, index_+38).toString(), 16));
								basic.put("MaxHightTime", MaxHightTime);
								String  OpenFirstDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
								basic.put("OpenFirstDoorVibratesTime", OpenFirstDoorVibratesTime);
								String  OpenMaxDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+40, index_+42).toString(), 16));
								basic.put("OpenMaxDoorVibratesTime", OpenMaxDoorVibratesTime);
								String  OpenEndDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
								basic.put("OpenEndDoorVibratesTime", OpenEndDoorVibratesTime);
								String  CloseFirstDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+44, index_+46).toString(), 16));
								basic.put("CloseFirstDoorVibratesTime", CloseFirstDoorVibratesTime);
								String  CloseMaxDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+46, index_+48).toString(), 16));
								basic.put("CloseMaxDoorVibratesTime", CloseMaxDoorVibratesTime);
								String  CloseEndDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								basic.put("CloseEndDoorVibratesTime", CloseEndDoorVibratesTime);
								
								String  OpenDoorVibratesValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+54).toString(), 16));
								basic.put("OpenDoorVibratesValue", OpenDoorVibratesValue);
								String  CloseDoorVibratesValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+54, index_+58).toString(), 16));
								basic.put("CloseDoorVibratesValue", CloseDoorVibratesValue);
								String  InDoorMaxHight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+58, index_+60).toString(), 16));
								basic.put("InDoorMaxHight", InDoorMaxHight);
								String  InDoorMaxHight_=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+62).toString(), 16));
								basic.put("InDoorMaxHight_", InDoorMaxHight_);
								String  calibrationValve=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+62, index_+64).toString(), 16));
								basic.put("calibrationValve", calibrationValve);
								
								String beiyong=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+64, index_+66).toString(), 16));
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
								
								String  Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
							}
							//出门
							if (commandID.equals("21")) {
								int devie=TEMP_().GetDEVID();
								String OutRoleCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("OutRoleCount", OutRoleCount);
								String  InUltrasonicTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
								basic.put("InUltrasonicTime", InUltrasonicTime);
								String  DoorStopTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+34).toString(), 16));
								basic.put("DoorStopTime", DoorStopTime);
								
								//String  InDoorTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
								//basic.put("InDoorTime", InDoorTime);
								String  MaxHightTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
								basic.put("MaxHightTime", MaxHightTime);
								String  OutUltrasonicTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+36, index_+38).toString(), 16));
								basic.put("OutUltrasonicTime", OutUltrasonicTime);
								
								String  CloseFirstDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
								basic.put("CloseFirstDoorVibratesTime", CloseFirstDoorVibratesTime);
								String  CloseMaxDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+40, index_+42).toString(), 16));
								basic.put("CloseMaxDoorVibratesTime", CloseMaxDoorVibratesTime);
								String  CloseEndDoorVibratesTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
								basic.put("CloseEndDoorVibratesTime", CloseEndDoorVibratesTime);
								String  CloseDoorVibratesValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+44, index_+48).toString(), 16));
								basic.put("CloseDoorVibratesValue", CloseDoorVibratesValue);
								String  InDoorMaxHight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								basic.put("InDoorMaxHight", InDoorMaxHight);
								String  InDoorMaxHight_=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+52).toString(), 16));
								basic.put("InDoorMaxHight_", InDoorMaxHight_);
								String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+52, index_+54).toString(), 16));
								basic.put("CalibrationLandValue", CalibrationLandValue);
								
								String beiyong=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+54, index_+66).toString(), 16));
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
							
								
								String  Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
								
							}
							//心跳
							if (commandID.equals("17")) {
								
								   String EnvironmentStatus=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								   basic.put("EnvironmentStatus", EnvironmentStatus);
								   String CalibrationRoleValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
									basic.put("CalibrationRoleValue", CalibrationRoleValue);
								
									String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+34).toString(), 16));
									basic.put("CalibrationLandValue", CalibrationLandValue);
									String WorkStatus=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
									basic.put("WorkStatus", WorkStatus);
									
									String CurTemperatureValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+36, index_+38).toString(), 16));
									basic.put("CurTemperatureValue",CurTemperatureValue);
									//String tobiary=Integer.toBinaryString(Integer.parseInt(CurTemperatureValue));
									//if (Integer.parseInt(tobiary.substring(7))>0) {
										//basic.put("CurTemperatureValue","+"+String.valueOf((Integer.parseInt(CurTemperatureValue)-128)));
									//}
									//else {
									//	basic.put("CurTemperatureValue","-"+String.valueOf((Integer.parseInt(CurTemperatureValue)-128)));
									//}
									String  Tamper=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
									basic.put("Tamper", Tamper);
									String  voltage=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+40, index_+42).toString(), 16));
									basic.put("voltage", voltage);
									//String  RoleCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
									//basic.put("RoleCount", RoleCount);
									String HotTriggerCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
									basic.put("HotTriggerCount", HotTriggerCount);
									String UltrasoundWorkTime=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+44, index_+48).toString(), 16));
									basic.put("UltrasoundWorkTime", UltrasoundWorkTime);
									String HotTriggerTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+52).toString(), 16));
									basic.put("HotTriggerTotalCount", HotTriggerTotalCount);
									String VibratesTotal=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+52, index_+56).toString(), 16));
									basic.put("VibratesTotal", VibratesTotal);
									
									String MouthPressCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+56, index_+58).toString(), 16));
									basic.put("MouthPressCount", MouthPressCount);
									
									String Status=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+58, index_+60).toString(), 16));
									basic.put("Status", Status);
									
									String beiyong=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+66).toString(), 16));
									basic.put("beiyong", beiyong);
									String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
									basic.put("RSSI", RSSI);
									
									//String beiyong=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+68).toString(), 16));
									//basic.put("beiyong", beiyong);
									String VerSion=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
									basic.put("VerSion", VerSion);
			
							}
							if (commandID.equals("35")) {
								String InDoorCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("InDoorCount", InDoorCount);
								
								String Persist=(TEMP_().GetCOMTENT().subSequence(index_+30, index_+58).toString());
								basic.put("Persist", Persist);
								String InOutDoorMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+58, index_+60).toString(), 16));
								basic.put("InOutDoorMaxHeight", InOutDoorMaxHeight);
								String InOutDoorRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+62).toString(), 16));
								basic.put("InOutDoorRealMaxHeight", InOutDoorRealMaxHeight);
								String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+62, index_+64).toString(), 16));
								basic.put("CalibrationLandValue", CalibrationLandValue);
								String beiyong=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+64, index_+66).toString(), 16));
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
								String Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
							}
							if (commandID.equals("37")) {
								String OutDoorCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("OutDoorCount", OutDoorCount);
								
								String Persist=(TEMP_().GetCOMTENT().subSequence(index_+30, index_+48).toString());
								basic.put("Persist", Persist);
								String InOutDoorMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								basic.put("InOutDoorMaxHeight", InOutDoorMaxHeight);
								String InOutDoorRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+52).toString(), 16));
								basic.put("InOutDoorRealMaxHeight", InOutDoorRealMaxHeight);
								String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+52, index_+54).toString(), 16));
								basic.put("CalibrationLandValue", CalibrationLandValue);
								String beiyong=(TEMP_().GetCOMTENT().subSequence(index_+54, index_+66).toString());
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
								String Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
							}
							if (commandID.equals("51")) {
								String OutDoorCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("InDoorCount", OutDoorCount);
								
								String Persist=(TEMP_().GetCOMTENT().subSequence(index_+30, index_+58).toString());
								basic.put("Persist", Persist);
								String InOutDoorMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+58, index_+60).toString(), 16));
								basic.put("InOutDoorMaxHeight", InOutDoorMaxHeight);
								String InOutDoorRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+62).toString(), 16));
								basic.put("InOutDoorRealMaxHeight", InOutDoorRealMaxHeight);
								String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+62, index_+64).toString(), 16));
								basic.put("CalibrationLandValue", CalibrationLandValue);
								String beiyong=(TEMP_().GetCOMTENT().subSequence(index_+64, index_+66).toString());
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
								String Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
							}
							if (commandID.equals("53")) {
								String OutDoorCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								basic.put("OutDoorCount", OutDoorCount);
								
								String Persist=(TEMP_().GetCOMTENT().subSequence(index_+30, index_+48).toString());
								basic.put("Persist", Persist);
								String InOutDoorMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								basic.put("InOutDoorMaxHeight", InOutDoorMaxHeight);
								String InOutDoorRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+52).toString(), 16));
								basic.put("InOutDoorRealMaxHeight", InOutDoorRealMaxHeight);
								String CalibrationLandValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+52, index_+54).toString(), 16));
								basic.put("CalibrationLandValue", CalibrationLandValue);
								String beiyong=(TEMP_().GetCOMTENT().subSequence(index_+54, index_+66).toString());
								basic.put("beiyong", beiyong);
								String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								basic.put("RSSI", RSSI);
								String Version=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								basic.put("Version", Version);
							}
							if (commandID.equals("67")) {
								
								   String InDoorRoleCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								   basic.put("InDoorRoleCount", InDoorRoleCount);
								   String FirstLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
								   basic.put("FirstLevelInDoorTotalCount", FirstLevelInDoorTotalCount);
								   String FirstLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+34).toString(), 16));
								   basic.put("FirstLevelOutDoorTotalCount", FirstLevelOutDoorTotalCount);
								   String ThreeLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
								   basic.put("ThreeLevelInDoorTotalCount", ThreeLevelInDoorTotalCount);
								   String ThreeLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+36, index_+38).toString(), 16));
								   basic.put("ThreeLevelOutDoorTotalCount", ThreeLevelOutDoorTotalCount);
								   String FiveLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
								   basic.put("FiveLevelInDoorTotalCount", FiveLevelInDoorTotalCount);
								   String FiveLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+40, index_+42).toString(), 16));
								   basic.put("FiveLevelOutDoorTotalCount", FiveLevelOutDoorTotalCount);
								   String ResidenceAverageHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
								   basic.put("ResidenceAverageHeight", ResidenceAverageHeight);
								   String DoorFluctuateCharacteristicValue1=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+44, index_+46).toString(), 16));
								   basic.put("DoorFluctuateCharacteristicValue1", DoorFluctuateCharacteristicValue1);
								   String DoorFluctuateCharacteristicValue2=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+46, index_+48).toString(), 16));
								   basic.put("DoorFluctuateCharacteristicValue2", DoorFluctuateCharacteristicValue2);
								   String CalibrationRoleValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								   basic.put("CalibrationRoleValue", CalibrationRoleValue);
								   String crc=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+54).toString(), 16));
								   basic.put("crc", crc);
								   String baoliu=(TEMP_().GetCOMTENT().subSequence(index_+54, index_+58).toString());
								   basic.put("baoliu", baoliu);
								   String InOutCalibrationMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+58, index_+60).toString(), 16));
								   basic.put("InOutCalibrationMaxHeight", InOutCalibrationMaxHeight);
								   String InOutCalibrationRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+60, index_+62).toString(), 16));
								   basic.put("InOutCalibrationRealMaxHeight", InOutCalibrationRealMaxHeight);
								   String LoadCalibrationValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+62, index_+64).toString(), 16));
								   basic.put("LoadCalibrationValue", LoadCalibrationValue);
								   String baoliu2=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+64, index_+66).toString(), 16));
								   basic.put("baoliu2", baoliu2);
								   String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								   basic.put("RSSI", RSSI);
								   String VerSion=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								   basic.put("VerSion", VerSion);
			
							}
							if (commandID.equals("69")) {
								   String OutDoorRoleCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+28, index_+30).toString(), 16));
								   basic.put("OutDoorRoleCount", OutDoorRoleCount);
								   String FirstLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+30, index_+32).toString(), 16));
								   basic.put("FirstLevelInDoorTotalCount", FirstLevelInDoorTotalCount);
								   String FirstLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+32, index_+34).toString(), 16));
								   basic.put("FirstLevelOutDoorTotalCount", FirstLevelOutDoorTotalCount);
								   String ThreeLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+34, index_+36).toString(), 16));
								   basic.put("ThreeLevelInDoorTotalCount", ThreeLevelInDoorTotalCount);
								   String ThreeLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+36, index_+38).toString(), 16));
								   basic.put("ThreeLevelOutDoorTotalCount", ThreeLevelOutDoorTotalCount);
								   
								   String fiveLevelInDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+38, index_+40).toString(), 16));
								   basic.put("fiveLevelInDoorTotalCount", fiveLevelInDoorTotalCount);
								   String fiveLevelOutDoorTotalCount=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+40, index_+42).toString(), 16));
								   basic.put("fiveLevelOutDoorTotalCount", fiveLevelOutDoorTotalCount);
								   String ResidenceAverageHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+42, index_+44).toString(), 16));
								   basic.put("ResidenceAverageHeight", ResidenceAverageHeight);
								   
								   String DoorFluctuateCharacteristicValue1=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+44, index_+46).toString(), 16));
								   basic.put("DoorFluctuateCharacteristicValue1", DoorFluctuateCharacteristicValue1);
								   String DoorFluctuateCharacteristicValue2=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+46, index_+48).toString(), 16));
								   basic.put("DoorFluctuateCharacteristicValue2", DoorFluctuateCharacteristicValue2);
								   
								   
								   String OutDoorMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+48, index_+50).toString(), 16));
								   basic.put("OutDoorMaxHeight", OutDoorMaxHeight);
								   String OutDoorRealMaxHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+50, index_+52).toString(), 16));
								   basic.put("OutDoorRealMaxHeight", OutDoorRealMaxHeight);
								   String LoadCalibrationValue=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+52, index_+54).toString(), 16));
								   basic.put("LoadCalibrationValue", LoadCalibrationValue);
								   
								   String LeaveUltrasonicHeight=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+54, index_+56).toString(), 16));
								   basic.put("LeaveUltrasonicHeight", LeaveUltrasonicHeight);
								   String crc=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+56, index_+60).toString(), 16));
								   basic.put("crc", crc);
								   String baoliu=(TEMP_().GetCOMTENT().subSequence(index_+60, index_+66).toString());
								   basic.put("baoliu", baoliu);
								   String RSSI=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+66, index_+68).toString(), 16));
								   basic.put("RSSI", RSSI);
								   String VerSion=Integer.toString(Integer.parseInt(TEMP_().GetCOMTENT().subSequence(index_+68, index_+70).toString(), 16));
								   basic.put("VerSion", VerSion);   	   
							}
							
							//basic.put("CRC", CRC);
							B[i]=basic;
						}
						
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("====异常="+e.getMessage());
					}
					
							
			}
				return B;
				
			}
		
	
		return null;
	}
	public String StringForm(int Num,String value)
	{
	   return	String.format("%0" + Num + "d", Integer.parseInt(value));
	}
	static Object GetTime_=new Object();
	public String GetTime(String timeByte)
    {
		synchronized (GetTime_) {
			  try {
				  int o1=Integer.parseInt(timeByte.subSequence(2,4).toString(),16);
				  String t1=String.format("%0" + 2 + "d", o1);
				  int o2=Integer.parseInt(timeByte.subSequence(4,6).toString(),16);
				  String t2=String.format("%0" + 2 + "d", o2);
				  int o3=Integer.parseInt(timeByte.subSequence(6,8).toString(),16);
				  String t3=String.format("%0" + 2 + "d", o3);
				  int o4=Integer.parseInt(timeByte.subSequence(8,10).toString(),16);
				  String t4=String.format("%0" + 2 + "d", o4);
				  int  o5=Integer.parseInt(timeByte.subSequence(10,12).toString(),16);
				  String t5=String.format("%0" + 2 + "d", o5);
				  String Dt=String.valueOf(2000+ Integer.parseInt(timeByte.substring(0, 2).toString(),16))+"-"+t1+"-"+t2+" "+t3+":"+t4+":"+t5;

			    	   return Dt;
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				return "";
		}
     
    }
}
