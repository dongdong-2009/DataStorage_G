package com.tendency.cb.xml;

import java.util.TimerTask;

import com.tendency.cb.Info.DDCXMLInfo;
import com.tendency.cb.TaskEvevt.diandongcheXMLTask;
import com.tendency.cb.util.DataCenter;

public class XmlEncodeTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (DDCXMLInfo.GetSize()>0) {
			DataCenter.threadPool.execute(new diandongcheXMLTask());
		}
	}

}
