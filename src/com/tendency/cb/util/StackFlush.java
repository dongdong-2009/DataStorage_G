package com.tendency.cb.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.TaskEvevt.DataStackListen;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.xml.GetHashItem;
import com.tendency.cb.xml.RedisTimersch;

public class StackFlush implements DataStackListen{

	public StackFlush()
	{
		
	}

	

	



	@Override
	public void StackFlushData(MongoDBDaoImpl M_, GetHashItem C, JedisUtil J) {
		// TODO Auto-generated method stub
		DataCenter.threadPool.execute(new bll.RedisTimersch_Data(C,M_,J));
	}

}
