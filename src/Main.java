import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tendency.cb.TaskEvevt.ThreadPoolStatus;
import com.tendency.cb.util.DataCenter;
import com.tendency.cb.util.ProtocolUtils;
import com.tendency.cb.util.ThreadPool;
import com.tendency.cb.xml.Config;
import com.tendency.cb.xml.ExcuteTaskXml;
import com.tendency.cb.xml.ExectQueueList;
import com.tendency.cb.xml.XMLReader;
import util.*;

public class Main implements ExectQueueList
{

	private static boolean Isfirst = false;

	public static void main(String[] args) throws InterruptedException
	{

		String urlString = System.getProperty("user.dir");

		String path = urlString + "/log4j.xml";

		Config Config_ = XMLReader.loadconfig(urlString);
		ProtocolUtils.setConfg(Config_);

		Log.Init(path);
		Log.Info("[CMDID:0]==[Config配置文件和日志系统加载成功]]");

		int ID = 0;
		try
		{

			String str = "start";
			while (true)
			{

				if (str.equals("start"))
				{

					DataCenter.threadPool = ThreadPool.init();
					Isfirst = true;
					ID++;
					DataCenter.threadPool.execute(new ExcuteTaskXml(ID));
					DataCenter.FilethreadPool = ThreadPool.init();
					DataCenter.CodethreadPool = ThreadPool.init();
					DataCenter.CodethreadPool.execute(new ThreadPoolStatus());

					Log.Info("====目前运行的线程为：" + DataCenter.threadPool.getExecutor().getActiveCount() + "===可使用的线程数目为："
							+ DataCenter.threadPool.getExecutor().getPoolSize());
				}
				if (str.equals("add"))
				{
					ID++;
					DataCenter.threadPool.execute(new ExcuteTaskXml(ID));
					Log.Info("====目前运行的线程为：" + DataCenter.threadPool.getExecutor().getActiveCount() + "===可使用的线程数目为："
							+ DataCenter.threadPool.getExecutor().getPoolSize());
				}
				if (str.equals("stop"))
				{

					DataCenter.threadPool.getExecutor().shutdownNow();
					Log.Info("程序关闭");
					System.exit(0);

				}

				str = new Scanner(System.in).nextLine();
			

				// BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
				// str = strin.readLine();
			}
		}
		catch (Exception ex)
		{

			Log.Error("外围运行错误：" + ErrorInfo.GetInfo(ex));

		}

	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see java.lang.Object#Object()
	 */

	public void tets()
	{
		for (int i = 0; i < 10000; i++)
		{
			byte[] Temp = new byte[] { 0x00, 0x01, 0x01 };

		}

	}

	@Override
	public void Close()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ExectMessage(String data)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void MutilMessage(List<String> data)
	{
		// TODO Auto-generated method stub

	}

	// region Main方法注释

	// public static void main(String[] args) throws InterruptedException
	// {
	//
	//
	//
	//
	//
	//// String Content =
	// "DC000100AB0A020000FF3106000100260000000017005F3139322e3136382e312e312f323030312f304DD6";
	////
	//// bll.CmdS.DevWayInOutNotify_Bll c =new bll.CmdS.DevWayInOutNotify_Bll();
	//// c.SetData(Content);
	////
	//
	// String urlString = System.getProperty("user.dir");
	//
	// String path = urlString + "/log4j.xml";
	//
	// Config Config_ = XMLReader.loadconfig(urlString);
	// ProtocolUtils.setConfg(Config_);
	//
	//
	// Log.Init(path);
	// Log.Info("[CMDID:0]==[Config配置文件和日志系统加载成功]]");
	//
	//
	// int ID = 0;
	// try
	// {
	//
	// String str = "start";
	// while (true)
	// {
	//
	// if (str.equals("start"))
	// {
	//
	// DataCenter.threadPool = ThreadPool.init();
	// Isfirst = true;
	// ID++;
	// DataCenter.threadPool.execute(new ExcuteTaskXml(ID));
	// DataCenter.FilethreadPool = ThreadPool.init();
	// DataCenter.CodethreadPool = ThreadPool.init();
	// DataCenter.CodethreadPool.execute(new ThreadPoolStatus());
	//
	// Log.Info("====目前运行的线程为：" +
	// DataCenter.threadPool.getExecutor().getActiveCount() + "===可使用的线程数目为："
	// + DataCenter.threadPool.getExecutor().getPoolSize());
	// }
	// if (str.equals("add"))
	// {
	// ID++;
	// DataCenter.threadPool.execute(new ExcuteTaskXml(ID));
	// Log.Info("====目前运行的线程为：" +
	// DataCenter.threadPool.getExecutor().getActiveCount() + "===可使用的线程数目为："
	// + DataCenter.threadPool.getExecutor().getPoolSize());
	// }
	// if (str.equals("stop"))
	// {
	//
	// DataCenter.threadPool.getExecutor().shutdownNow();
	// Log.Info("程序关闭");
	// System.exit(0);
	//
	// }
	//
	// BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
	//
	// str = strin.readLine();
	// }
	// }
	// catch (Exception ex)
	// {
	//
	// Log.Error("外围运行错误：" + ErrorInfo.GetInfo(ex));
	//
	// }
	//
	// }

	// endregion

}
