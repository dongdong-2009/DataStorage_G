package bll.CmdS;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;

import util.ErrorInfo;
import util.Log;

public class Control_Bll implements ICmd
{

	public final String NAME = "命令_控制";

	private MongoDBDaoImpl Mk;

	


	
	/**
	 * 命令_控制
	 */
	public Control_Bll(MongoDBDaoImpl mk)
	{

		this.Mk = mk;
	}

	private List<DBObject> List = new ArrayList<DBObject>();



	@Override
	public void SetData(String content)
	{
		
		try
		{
			int length = content.length();	
			if(length >= 43 *2)
			{
					
				String SubContent= content.substring(23*2,length-4);
				
				String Guid = SubContent.substring(0, 32);
				
				int SubCmdIdInt = ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(SubContent.substring(32, 36)));
				String SubCmdIdStr= String.valueOf(SubCmdIdInt);	
				
				String SubData=SubContent.substring(18*2,SubContent.length());
				
				DBObject info = new BasicDBObject();
				info.put("GUID", Guid);

				int DevId = ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(content.substring(18, 26)));
				info.put("DEVID", DevId);

				info.put("COMMAND", String.valueOf(Integer.parseInt(content.substring(28, 32), 16)));

				info.put("RESULT", 0);
				info.put("CREATED", GetDate());
				info.put("RESULTDATA", "");
				info.put("CONTENT", SubData);
				info.put("JSONDATA", "");
				info.put("RESULTCOMMAND", SubCmdIdStr);
				info.put("RESERVE", content.substring(32, 40));

				List.add(info);
				
				
			}
		}
		catch (Exception ex)
		{

			Log.Error(NAME + "_设置数据错误：" + ErrorInfo.GetInfo(ex));

		}
		
	

	}

		

	@Override
	public void Execute()
	{
		try
		{
			if (List.size() > 0)
			{
				
					long endTime2 = System.currentTimeMillis();
					DB db = Mk.GetMongoClient().getDB("tendency");
					DBCollection dbCollection = db.getCollection("tendencyControl");
					WriteResult W = dbCollection.insert(List);
					long endTime = System.currentTimeMillis();
					long Time2 = endTime - endTime2;
					DataCenter.MongoStatus = true;	
					Log.Info(NAME+"：批量插入Mongo 数量："+ List.size()+" mongodb插入时间："+Time2);
					List.clear();
					List = null;
			
			}
		}
		catch (Exception ex)
		{

			Log.Error(NAME + "_批量插入Mongo错误：" + ErrorInfo.GetInfo(ex));	
			DataCenter.MongoStatus = false;
	

		}
		
		

	}
	
	private Date GetDate()
	{
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTime();
	}
	
//	private int GetLenght(String content)
//	{
//		String ContentSize = content.substring(40, 44);
//		return ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(ContentSize));
//	}

	
	

	
	
//region 注释SetData

//	public void SetData(String content)
//	{
//		int le = content.length();
//		
//		if(le >= 43 *2)
//		{
//			
//			System.out.println("大于等于43字节");
//			
//			String subsdddd= content.substring(23*2,le-4);
//			
//			System.out.println(subsdddd);
//		}
//		
//
//		int lenght = GetLenght(content);
//		if ((content.length() - 50) == lenght * 2)
//		{
//
//			String SubContent = content.substring(46, 46 + (lenght * 2));
//			String Guid = SubContent.substring(0, 32);
//			String SubCmdId = String.valueOf(Integer.parseInt(SubContent.substring(32, 36), 16));
//			
//		
//			
//			String DataContent = SubContent.substring(36, SubContent.length());
//
//			DBObject info = new BasicDBObject();
//
//			info.put("GUID", Guid);
//
//			String deviceid = content.substring(18, 26);
//			int DevId = ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(deviceid));
//			info.put("DEVID", DevId);
//
//			info.put("COMMAND", String.valueOf(Integer.parseInt(content.substring(28, 32), 16)));
//
//			info.put("RESULT", 0);
//			info.put("CREATED", GetDate());
//			info.put("RESULTDATA", "");
//			info.put("CONTENT", DataContent);
//			info.put("JSONDATA", "");
//			info.put("RESULTCOMMAND", SubCmdId);
//			info.put("RESERVE", content.substring(32, 40));
//
//			List.add(info);
//
//		}
//
//	}
	
	
	//endregion
	
}
