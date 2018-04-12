package com.tendency.cb.util;

import java.util.HashMap;
import java.util.Map;

public class DBLIDValue {
	private static Map<String, Integer> dicMap = new HashMap<String, Integer>(); 
	 public static boolean AddDBtic(String Key_,Integer value)
	 {
		 synchronized (dicMap) {
			if (!dicMap.containsKey(Key_)) {
				dicMap.put(Key_, value);
				return true;
			}
			return false;
		}
	 }
	 public static int GetValue(String S,int count)
	 {
		 synchronized (dicMap) {
			 try {
				 if (dicMap.containsKey(S)) {
						int C=dicMap.get(S);
						return C;
					}
				 else {
					 dicMap.put(S, count);
					 return 0;
				}
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			 finally
			 {
				 int C=dicMap.get(S);
				 int X=C+count;
				 dicMap.put(S, X);
				 
			 }
			 return 0;
			
		}
	 }

}
