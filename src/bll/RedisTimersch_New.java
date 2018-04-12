package bll;

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
import com.tendency.cb.xml.GetHashItem;
import util.*;

public class RedisTimersch_New implements Runnable
{

	private GetHashItem G;
	MongoDBDaoImpl MK;
	JedisUtil J;
	Long CurrentCount = Long.parseLong("0");

	public RedisTimersch_New(GetHashItem A, MongoDBDaoImpl M, JedisUtil J)
	{
		this.G = A;
		this.MK = M;
		this.J = J;

	}

	@Override
	public void run()
	{
		try
		{

			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			Date currnrtTime = ca.getTime();
			HashMap<String, List<String>> G1 = G.CopyHashMap();

			long starTime = System.currentTimeMillis();
			if (G1.size() > 0)
			{
				List<DBObject> LDB = new ArrayList<DBObject>();

				MysqHelp MysqHelp_ = new MysqHelp();
				Iterator<Entry<String, List<String>>> iterator = G1.entrySet().iterator();
				String speid_ = "";
				long Time1 = 0;
				int count_ = 0;
				boolean IsGetLid = true;
				while (iterator.hasNext())
				{
					Long CurrentCount = Long.parseLong("0");
					Entry<String, List<String>> entry = iterator.next();
					List<String> listDataList = entry.getValue();
					String speid = entry.getKey();

					long starTime1 = System.currentTimeMillis();

					for (int i = 0; i < 3; i++)
					{
						CurrentCount = MysqHelp_.GetCallCount("GetLID(?,?,?)", listDataList.size(), speid);
						if (CurrentCount != 9999)
						{
							IsGetLid = false;
							break;
						} else
						{
							IsGetLid = true;
							Thread.sleep(1000);
						}

					}

					long endTime1 = System.currentTimeMillis();
					Time1 = endTime1 - starTime1;

					for (String data : listDataList)
					{
						try
						{

							DBObject basic = new BasicDBObject();
							final ObjectInfo OI = new ObjectInfo();
							CurrentCount += 1;
							OI.SetLid(CurrentCount);
							basic.put("LID", CurrentCount);
							basic.put("CREATED", currnrtTime);
							String SN = data.substring(12, 16);
							OI.SetRN(SN);
							basic.put("RN", SN);
							String deviceid = data.substring(18, 26);
							int deviceid_ = (ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(deviceid)));
							OI.SetDEVID(deviceid_);
							basic.put("DEVID", deviceid_);
							String Reserve = data.substring(32, 40);
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

									String Content = data.substring(46, 46 + Datasize * 2);
									String Speid = Content.substring(0, 8);
									Speid = String
											.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));
									basic.put("SPEID", Speid);
									OI.SetSPEID(Speid);

									String Version = Content.substring(8, 12);
									String Version1 = String
											.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Version)));
									basic.put("VERSION", Version1);
									OI.SetVERSION(Version);

									String subcommadn = Content.substring(12, 16);
									String subcommadn1 = String.valueOf(
											ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subcommadn)));
									basic.put("CMDID", subcommadn1);
									OI.SetCMDID(subcommadn1);
									String Comment = Content.substring(16, Datasize * 2);
									basic.put("COMTENT", Comment);
									OI.SetCOMTENT(Comment);

									LDB.add(basic);

								} else
								{

								}
							}
						} 
						catch (Exception ex)
						{

							Log.Error("CMDID:2==具体操作：解析异常(数据段异常) == 异常原因：" + ErrorInfo.GetInfo(ex) + " 原始数据：" + data);
						}
					}
				}
				if (LDB.size() > 0)
				{
					try
					{
						if (!IsGetLid)
						{
							long endTime2 = System.currentTimeMillis();
							DB db = MK.GetMongoClient().getDB("tendency");
							DBCollection dbCollection = db.getCollection("tendencydata");
							WriteResult W = dbCollection.insert(LDB);
							long endTime = System.currentTimeMillis();

							long Time = endTime - starTime;
							long Time2 = endTime - endTime2;
							DataCenter.MongoStatus = true;

							Log.Info(MessageFormat.format(
									"CMDID:10==具体操作:{0}==数量:{1}==执行时间:{2}===MYSQL执行时间:{3}==mongodb插入时间:{4}",
									"批量插入Mongo", LDB.size(), Time, Time1, Time2));
							LDB.clear();
							LDB = null;
						} else
						{
							long endTime2 = System.currentTimeMillis();
							DB db = MK.GetMongoClient().getDB("tendency");
							DBCollection dbCollection = db.getCollection("tendencydatabak");
							WriteResult W = dbCollection.insert(LDB);
							long endTime = System.currentTimeMillis();

							long Time = endTime - starTime;
							long Time2 = endTime - endTime2;
							DataCenter.MongoStatus = true;

							Log.Info(MessageFormat.format(
									"CMDID:10==具体操作:{0}==数量:{1}==执行时间:{2}===MYSQL执行时间:{3}==mongodb插入时间:{4}",
									"批量插入Mongo", LDB.size(), Time, Time1, Time2));
							LDB.clear();
							LDB = null;
						}
					} catch (Exception ex)
					{
						Log.Error(MessageFormat.format("CMDID:9==具体操作:{0}==异常原因:{1}", "批量插入Mongo异常",
								ErrorInfo.GetInfo(ex)));
						DataCenter.MongoStatus = false;
					}

				}

			}
		} catch (Exception ex)
		{
			Log.Error(MessageFormat.format("CMDID:4==具体操作:{0}==异常原因:{1}", "解析异常", ErrorInfo.GetInfo(ex)));
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
			String Dt = String.valueOf(2000 + Integer.parseInt(timeByte.substring(0, 2).toString(), 16)) + "-" + t1
					+ "-" + t2 + " " + t3 + ":" + t4 + ":" + t5;

			return Dt;

		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return "";

	}
}
