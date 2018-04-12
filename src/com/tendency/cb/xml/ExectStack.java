package com.tendency.cb.xml;

import java.util.List;

import com.tendency.cb.TaskEvevt.DataStackListen;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.GetListArrayItem;
import com.tendency.cb.util.JedisUtil;

public class ExectStack {
	DataStackListen D=null;
	public ExectStack(DataStackListen D_)
	{
		this.D=D_;
	}
	public void SetEx(MongoDBDaoImpl M_,GetHashItem G,JedisUtil J)
	{
		D.StackFlushData(M_,G,J);
	}
}
