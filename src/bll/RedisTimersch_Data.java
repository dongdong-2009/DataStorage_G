package bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mongodb.DBObject;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.xml.GetHashItem;
import util.*;
import bll.CmdS.*;

public class RedisTimersch_Data implements Runnable
{

	private GetHashItem G;
	MongoDBDaoImpl MK;
	JedisUtil J;

	public RedisTimersch_Data(GetHashItem A, MongoDBDaoImpl M, JedisUtil J)
	{
		this.G = A;
		this.MK = M;
		this.J = J;

	}
	
	


	private void Execute(List<String> list, String speid)
	{
		try
		{
			Log.Info("开始执行Redis数据");
			String DevCmd = "";
			int Data_Size = 0;
			for (String con : list)
			{
				DevCmd = con.substring(28, 32) ;
				if (DevCmd.equals("0007"))
				{		
					Data_Size+=1 ;
				}
				
			}

			ICmd Data_Cmd = new Data_Bll(MK,Data_Size, speid);
			ICmd Control_Cmd = new Control_Bll(MK);
			ICmd ControlReply_Cmd =new ControlReply_Bll(MK);
			ICmd ControlResultData_Cmd =new ControlResultData_Bll(MK);
			ICmd DevWayInOutNotify_Cmd =new DevWayInOutNotify_Bll(MK);
			for (String content : list)
			{

				DevCmd = content.substring(28, 32);
				Integer DevCmdInt = Integer.parseInt(DevCmd,16);
						
				switch (DevCmdInt)
				{
			
				case 7: //0007_数据
					Data_Cmd.SetData(content);
					break;
				case 31: //001F_控制
					Control_Cmd.SetData(content);
					break;
				case 32: //0020_控制应答
					ControlReply_Cmd.SetData(content);
					break;		
				case 43: // 002B_控制结果数据 
					ControlResultData_Cmd.SetData(content);
					break;
				case 38: //0026_设备通道上下线通知
					DevWayInOutNotify_Cmd.SetData(content);
					break;
				default:
					break;
				}

			}
			
			Data_Cmd.Execute();
			Control_Cmd.Execute();
			ControlReply_Cmd.Execute();
			ControlResultData_Cmd.Execute();
			DevWayInOutNotify_Cmd.Execute();
			
			Log.Info("执行Redis数据结束");
		}
		catch (Exception ex)
		{

			Log.Error("处理Redis数据错误：" + ErrorInfo.GetInfo(ex));

		}
	

	}

	/*
	 * 处理从Redis中读取的数据。
	 * 
	 */
	public void run()
	{
		try
		{
			Log.Info("从Redis中读取数据开始");

			HashMap<String, List<String>> G1 = G.CopyHashMap();
			
			
			if (G1.size() > 0)
			{

				Iterator<Entry<String, List<String>>> iterator = G1.entrySet().iterator();
				while (iterator.hasNext())
				{

					Entry<String, List<String>> entry = iterator.next();
					List<String> listDataList = entry.getValue();
					Log.Info("Redis内容条数："+listDataList.size());
					Execute(listDataList, entry.getKey());

				}

			}
			
			Log.Info("从Redis中读取数据结束");
		} catch (Exception ex)
		{
			Log.Error("处理Redis数据错误：" + ErrorInfo.GetInfo(ex));
		}
	}
	
	
   //region 注释
	
	private static void RRR( MongoDBDaoImpl mDB,List<String> list, String speid)
	{
		try
		{
			Log.Info("开始执行Redis数据");
			String DevCmd = "";
			int Data_Size = 0;
			for (String con : list)
			{
				DevCmd = con.substring(28, 32) ;
				if (DevCmd.equals("0007"))
				{		
					Data_Size+=1 ;
				}
				
			}

			ICmd Data_Cmd = new Data_Bll(mDB,Data_Size, speid);
			ICmd Control_Cmd = new Control_Bll(mDB);
			ICmd ControlReply_Cmd =new ControlReply_Bll(mDB);
			ICmd ControlResultData_Cmd =new ControlResultData_Bll(mDB);
			ICmd DevWayInOutNotify_Cmd =new DevWayInOutNotify_Bll(mDB);
			for (String content : list)
			{

				DevCmd = content.substring(28, 32);
				Integer DevCmdInt = Integer.parseInt(DevCmd,16);
						
				switch (DevCmdInt)
				{
			
				case 7: //0007_数据
					Data_Cmd.SetData(content);
					break;
				case 31: //001F_控制
					Control_Cmd.SetData(content);
					break;
				case 32: //0020_控制应答
					ControlReply_Cmd.SetData(content);
					break;		
				case 43: // 002B_控制结果数据 
					ControlResultData_Cmd.SetData(content);
					break;
				case 38: //0026_设备通道上下线通知
					DevWayInOutNotify_Cmd.SetData(content);
					break;
				default:
					break;
				}

			}
			
			Data_Cmd.Execute();
			Control_Cmd.Execute();
			ControlReply_Cmd.Execute();
			ControlResultData_Cmd.Execute();
			DevWayInOutNotify_Cmd.Execute();
			
			Log.Info("执行Redis数据结束");
		}
		catch (Exception ex)
		{

			Log.Error("处理Redis数据错误：" + ErrorInfo.GetInfo(ex));

		}
	}
	
	//endregion

}
