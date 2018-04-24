package bll.CmdS;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;

import util.ErrorInfo;
import util.Log;

/**
 * 处理设备到前置机的数据。
 *
 */
public class Data_Bll implements ICmd
{

	public final String NAME = "命令_数据";

	private MongoDBDaoImpl MK;
	private int DataSize;
	private String SpeId;
	private boolean IsGetLid = true;

	private List<DBObject> LDB = new ArrayList<DBObject>();

	/**
	 * 处理设备到前置机的数据
	 * 
	 * @param content
	 *            = 数据内容
	 */
	public Data_Bll(MongoDBDaoImpl mk, int dataSize, String speId)
	{
		this.MK = mk;
		this.DataSize = dataSize;
		this.SpeId = speId;

	}

	/*
	 * 设置数据。
	 */
	public void SetData(String content)
	{

		try
		{
				
			int length = content.length();
			if (length >= 25 * 2)
			{
				String SubContent = content.substring(23 * 2, length - 4);
				if (!SubContent.equals(""))
				{
					
					DBObject info = new BasicDBObject();
					
				//	Long CurrentCount = GetLastId(DataSize, SpeId);
					Long CurrentCount = GetLastId(1, SpeId);
					CurrentCount += 1;
					info.put("LID", CurrentCount);
					
					info.put("CREATED", GetDate());
							
					info.put("RN", content.substring(12, 16));
									
					String devIdStr = content.substring(18, 26);
					int devId = (ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(devIdStr)));
					info.put("DEVID", devId);
								
					String Reserve = content.substring(32, 40);
					info.put("RESERVE", Reserve);
					String SUBDEVTYPE = Reserve.substring(4, 8);
					info.put("SUBDEVTYPE", SUBDEVTYPE);
														
					String Speid = SubContent.substring(0, 8);
					Speid = String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
					info.put("SPEID", Speid);
										
					String Ver = SubContent.substring(8, 12);
					String Version = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Ver)));
					info.put("VERSION", Version);
													
					String subCmd = SubContent.substring(12, 16);
					String subCmdStr = String
							.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subCmd)));
					info.put("CMDID", subCmdStr);
					
					String Comment = SubContent.substring(16, SubContent.length());
					info.put("COMTENT", Comment);
									
					LDB.add(info);
					
				
				}

			}
			
		
		}
		catch (Exception ex)
		{

			Log.Error(NAME + "_设置数据错误：" + ErrorInfo.GetInfo(ex));

		}

	}
	
	
//region  注释	
	
//	public void SetData(String content)
//	{
//
//		try
//		{
//			Date currnrtTime = GetDate();
//
//			DBObject basic = new BasicDBObject();
//			final ObjectInfo OI = new ObjectInfo();
//
//			Long CurrentCount = GetLastId(DataSize, SpeId);
//			CurrentCount += 1;
//			OI.SetLid(CurrentCount);
//			basic.put("LID", CurrentCount);
//
//			basic.put("CREATED", currnrtTime);
//
//			String SN = content.substring(12, 16);
//			OI.SetRN(SN);
//			basic.put("RN", SN);
//
//			String deviceid = content.substring(18, 26);
//			int deviceid_ = (ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(deviceid)));
//			OI.SetDEVID(deviceid_);
//			basic.put("DEVID", deviceid_);
//
//			String Reserve = content.substring(32, 40);
//			basic.put("RESERVE", Reserve);
//			String SUBDEVTYPE = Reserve.substring(4, 8);
//			basic.put("SUBDEVTYPE", SUBDEVTYPE);
//			OI.SetSUBDEVTYPE(SUBDEVTYPE);
//
//			String ContentSize = content.substring(40, 44);
//			int Datasize = ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(ContentSize));
//			int SunTruct = content.length() - 50;
//			if (Datasize * 2 == SunTruct)
//			{
//				if (Datasize > 8)
//				{
//
//					String SubContent = content.substring(46, 46 + Datasize * 2);
//					String Speid = SubContent.substring(0, 8);
//					Speid = String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
//					basic.put("SPEID", Speid);
//					OI.SetSPEID(Speid);
//
//					String Version = SubContent.substring(8, 12);
//					String Version1 = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Version)));
//					basic.put("VERSION", Version1);
//					OI.SetVERSION(Version);
//
//					String subcommadn = SubContent.substring(12, 16);
//					String subcommadn1 = String
//							.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subcommadn)));
//					basic.put("CMDID", subcommadn1);
//					OI.SetCMDID(subcommadn1);
//
//					String Comment = SubContent.substring(16, Datasize * 2);
//					basic.put("COMTENT", Comment);
//					OI.SetCOMTENT(Comment);
//					LDB.add(basic);
//
//				}
//
//			}
//		}
//		catch (Exception ex)
//		{
//
//			Log.Error(NAME + "_设置数据错误：" + ErrorInfo.GetInfo(ex));
//
//		}
//
//	}
	
	
	//endregion
	

	/*
	 * 处理数据。
	 */
	public void Execute()
	{

		try
		{
			if (LDB.size() > 0)
			{
				if (!IsGetLid)
				{
					long endTime2 = System.currentTimeMillis();
					DB db = MK.GetMongoClient().getDB("tendency");
					DBCollection dbCollection = db.getCollection("tendencydata");
					WriteResult W = dbCollection.insert(LDB);
					long endTime = System.currentTimeMillis();

					long Time2 = endTime - endTime2;
					DataCenter.MongoStatus = true;
									
					Log.Info(NAME+"_批量插入Mongo 数量："+ LDB.size()+" mongodb插入时间："+Time2);
										
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

					long Time2 = endTime - endTime2;
					DataCenter.MongoStatus = true;
					
					Log.Info(NAME+"_批量插入Mongo_databak 数量："+ LDB.size()+" mongodb插入时间："+Time2);
					LDB.clear();
					LDB = null;
				}

			}
		}
		catch (Exception ex)
		{

			Log.Error(NAME + "_批量插入Mongo错误：" + ErrorInfo.GetInfo(ex));	
			DataCenter.MongoStatus = false;

		}

	}

	private Long GetLastId(int size, String speid)
	{
		long LastId = 0;
		try
		{

			MysqHelp MysqHelp_ = new MysqHelp();
			for (int i = 0; i < 3; i++)
			{
				LastId = MysqHelp_.GetCallCount("GetLID(?,?,?)", size, speid);
				if (LastId != 9999)
				{
					IsGetLid = false;
					break;
				}
				else
				{
					IsGetLid = true;
					Thread.sleep(1000);
				}

			}
			return LastId;
		}
		catch (Exception ex)
		{
			return LastId;
		}

	}

	private Date GetDate()
	{
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTime();
	}

}
