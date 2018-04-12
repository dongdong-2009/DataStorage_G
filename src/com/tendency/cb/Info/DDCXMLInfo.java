package com.tendency.cb.Info;

import java.util.ArrayList;

public class DDCXMLInfo {
	private static ArrayList<ObjectInfo>P_=new ArrayList<ObjectInfo>();
	public static void AddItem(ObjectInfo T)
	{
		synchronized (P_) {
		P_.add(T);
		
		}
	}
	public static ArrayList<ObjectInfo> CopyList()
	{
		synchronized (P_) {
			ArrayList<ObjectInfo>C_=new ArrayList<ObjectInfo>();
			C_.addAll(P_);
			P_.clear();
			return C_;
			
		}
	}
	public static int GetSize()
	{
		synchronized (P_) {
		return P_.size();
		}
	}

}
