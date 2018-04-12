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

import mod.ControlResultData_Mod;
import util.ErrorInfo;
import util.Log;

public class ControlResultData_Bll implements ICmd
{
	public final String NAME = "命令_控制结果数据";

	private MongoDBDaoImpl Mk;

	public ControlResultData_Bll()
	{

	}

	/**
	 * 命令_控制结果数据
	 */
	public ControlResultData_Bll(MongoDBDaoImpl mk)
	{

		this.Mk = mk;
	}

	private List<ControlResultData_Mod> List = new ArrayList<ControlResultData_Mod>();

	@Override
	public void SetData(String content)
	{

		try
		{
			int length = content.length();
			if (length >= 43 * 2)
			{

				String SubContent = content.substring(23 * 2, length - 4);

				ControlResultData_Mod info = new ControlResultData_Mod();
				info.setGuid(SubContent.substring(0, 32));
				info.setResultData(SubContent.substring(36, SubContent.length()));
				// info.setResultDataJson("");
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

				long StartTime = System.currentTimeMillis();
				DB db = Mk.GetMongoClient().getDB("tendency");
				DBCollection dbCollection = db.getCollection("tendencyControl");

				for (ControlResultData_Mod info : List)
				{
					DBObject Select = new BasicDBObject();
					Select.put("GUID", info.getGuid());

					DBObject Update = new BasicDBObject();
					DBObject SubUpdate = new BasicDBObject();
					SubUpdate.put("RESULTDATA", info.getResultData());
					// SubUpdate.put("JSONDATA", info.getResultDataJson());
					Update.put("$set", SubUpdate);

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

	// region 注释 SetData

	// public void SetData(String content)
	// {
	// int lenght = GetLenght(content);
	// if ((content.length() - 50) == lenght * 2)
	// {
	//
	// String SubContent = content.substring(46, 46 + (lenght * 2));
	//
	// ControlResultData_Mod info = new ControlResultData_Mod();
	// info.setGuid(SubContent.substring(0, 32));
	// info.setResultData(SubContent.substring(36, SubContent.length()));
	// info.setResultDataJson("");
	// List.add(info);
	//
	// }
	//
	// }

	// endregion
}
