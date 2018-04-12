package bll.CmdS;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;

import util.ErrorInfo;
import util.Log;

public class ControlReply_Bll implements ICmd
{

	public final String NAME = "命令_控制应答";

	private MongoDBDaoImpl Mk;

	public ControlReply_Bll()
	{

	}

	/**
	 * 命令_控制应答
	 */
	public ControlReply_Bll(MongoDBDaoImpl mk)
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
			if (length >= 43 * 2)
			{

				String SubContent = content.substring(23 * 2, length - 4);
				String Guid = SubContent.substring(0, 32);

				DBObject Select = new BasicDBObject();
				Select.put("GUID", Guid);
				List.add(Select);

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
				long StartTime = System.currentTimeMillis();
				DB db = Mk.GetMongoClient().getDB("tendency");
				DBCollection dbCollection = db.getCollection("tendencyControl");

				DBObject Update = new BasicDBObject();
				DBObject SubUpdate = new BasicDBObject();
				SubUpdate.put("RESULT", 1);
				Update.put("$set", SubUpdate);

				for (DBObject Select : List)
				{
					dbCollection.update(Select, Update);
				}

				long RunTime = System.currentTimeMillis() - StartTime;
				DataCenter.MongoStatus = true;
				Log.Info(NAME + "：批量更新Mongo 数量：" + List.size() + " mongodb更新时间：" + RunTime);
				List.clear();
				List = null;

			}
		}
		catch (Exception ex)
		{
		
			Log.Error(NAME + "_批量更新Mongo错误：" + ErrorInfo.GetInfo(ex));
			DataCenter.MongoStatus = false;

		}

	}

//	private int GetLenght(String content)
//	{
//		String ContentSize = content.substring(40, 44);
//		return ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(ContentSize));
//	}

	// region 注释 SetData
	// public void SetData(String content)
	// {
	// int lenght = GetLenght(content);
	// if ((content.length() - 50) == lenght * 2)
	// {
	//
	// String SubContent = content.substring(46, 46 + (lenght * 2));
	// String Guid = SubContent.substring(0, 32);
	//
	// DBObject Select = new BasicDBObject();
	// Select.put("GUID", Guid);
	//
	// List.add(Select);
	//
	// }
	//
	// }
	// endregion
}
