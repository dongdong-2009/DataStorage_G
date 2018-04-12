package bll.CmdS;

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

public class DevWayInOutNotify_Bll implements ICmd
{

	
	public final String NAME = "命令_设备通道上下线通知";

	private MongoDBDaoImpl Mk;

	
	
	public DevWayInOutNotify_Bll()
	{

	
	}
	
	
	/**
	 * 命令_设备通道上下线通知
	 */
	public DevWayInOutNotify_Bll(MongoDBDaoImpl mk)
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
				String[] array = HexStrToStr(SubContent).split("/");

				DBObject info = new BasicDBObject();
				
				info.put("RECID", GetGuid());
				
				int DevId = ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(content.substring(18, 26)));
				info.put("DEVID", DevId);
				
				String CHANNEL=content.substring(12, 14);
				info.put("CHANNEL", CHANNEL);	
				
				info.put("IPENDPOINTS", array[0]+":"+array[1]);	
				info.put("UP_TIME", GetDate());	
				info.put("STATUS", array[2]);
			
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
					DBCollection dbCollection = db.getCollection("tendencyDevWayInOutNotify");
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
	
	
	/**
	 * 16进制转换成为string类型字符串
	 * @param s
	 * @return
	 */
	private  String HexStrToStr(String s)
	{
		if (s == null || s.equals(""))
		{
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++)
		{
			try
			{
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			s = new String(baKeyword, "UTF-8");
			new String();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		return s;
	}
	
	
	private String GetGuid()
	{
		return java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
	private Date GetDate()
	{
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTime();
	}
}
