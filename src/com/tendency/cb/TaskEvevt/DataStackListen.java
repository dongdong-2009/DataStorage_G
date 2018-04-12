package com.tendency.cb.TaskEvevt;

import java.util.List;

import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.util.GetListArrayItem;
import com.tendency.cb.util.JedisUtil;
import com.tendency.cb.xml.GetHashItem;

public interface DataStackListen {
public void StackFlushData(MongoDBDaoImpl M_,GetHashItem C,JedisUtil J);
}
