package com.tendency.cb.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HashCeurentQueue {
	  public static BlockingQueue<String> basket = new LinkedBlockingQueue<String>();
	  public static void AddBlockingQueue(String Data)
	  {
		  try {
			  basket.put(Data);
		} catch (Exception e) {
			// TODO: handle exception
		}
		  
	  }
}
