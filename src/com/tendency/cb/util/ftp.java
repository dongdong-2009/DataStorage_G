package com.tendency.cb.util;

import java.util.TimerTask;

import com.tendency.cb.TaskEvevt.ActiveCallBackDataRun;
import com.tendency.cb.TaskEvevt.ByteTOFile;
import com.tendency.cb.TaskEvevt.ExcuteQueuHttp;
import com.tendency.cb.xml.FlashDataList;

public class ftp extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (FlashDataList.GetCount()>0) {
			 DataCenter.FilethreadPool.execute(new ByteTOFile());
		}
		
	}

}
