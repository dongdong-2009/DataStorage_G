package com.tendency.cb.util;

import java.util.ArrayList;
import java.util.List;

public class ChildCallbackListen {
 private static List<String>COject=new ArrayList<String>();
 public static void AddChileCallBack(String ChildCallBackObject_)
 {
	 synchronized (COject) {
		 COject.add(ChildCallBackObject_);
	}
 }
 public static String CopeChildCallBackObject()
 {
	 synchronized (COject) {
    String Oyt_ = "";
	for (int i = 0; i < COject.size(); i++) {
		if (i==COject.size()-1) {
			Oyt_+=COject.get(i);
		}
		else {
			Oyt_+=COject.get(i)+"|";
		}
	}
		COject.clear();
		return Oyt_;
	}
	
 }
}
