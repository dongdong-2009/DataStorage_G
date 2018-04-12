package com.tendency.cb.xml;

public class DeviceInfo {
	/**
	 * 前置机头文件
	 */
	private int Prefix;
	/**
	 * 版本号
	 */
	//
	private int Version;
	/**
	 * 加密类型
	 */
	//
    private int EncryptType;
    /**
	 * 流水号
	 */
	//
    private int SN;
	/**
	 *通道
	 */
   //
    private int Channel;
	/**
	 * 错误码
	 */
  //
    private int ErrorCode;
	/**
	 * 设备ID
	 */
  //
    private int DeviceID;
	/**
	 * 消息类型
	 */
  //
    private int MessageType;
	/**
	 * 流命令字
	 */
  //
    private byte[] CommandID;
	/**
	 * 保留
	 */
    private int Reserve;
   	/**
   	 * 内容长度
   	 */
    private int ContentSize;
	/**
	 * 校验
	 */
    //
    private int HeadVerifyCode;
	/**
	 * 内容
	 */
    //
    private byte[] Content;
    /**
	 * CRC校验
	 */
    //
    private int VerifyCode;
    /**
   	 * CRC校验
   	 */
    
    private byte[] AllContent;
    
    public void setPrefix(byte[] setPrefix) {
		this.AllContent = setPrefix;
	}
  
    public void setPrefix(int setPrefix) {
		this.Prefix = setPrefix;
	}
    public void setVersion(int Version) {
		this.Version = Version;
	}
    public void setEncryptType(int EncryptType) {
		this.EncryptType = EncryptType;
	}
    public void setSN(int SN) {
		this.SN = SN;
	}
    public void setChannel(int Channel) {
		this.Channel = Channel;
	}
    public void setErrorCode(int ErrorCode) {
		this.ErrorCode = ErrorCode;
	}
    public void setDeviceID(int DeviceID) {
		this.DeviceID = DeviceID;
	}
    public void setMessageType(int MessageType) {
		this.MessageType = MessageType;
	}
    public void setCommandID(byte[] CommandID) {
		this.CommandID = CommandID;
	}
    public void setReserve(int Reserve) {
		this.Reserve = Reserve;
	}
   
    public void setContent(byte[] Content) {
		this.Content = Content;
	}
  
    public void setHeadVerifyCode(int HeadVerifyCode) {
		this.HeadVerifyCode = HeadVerifyCode;
	}
    public void setVerifyCode(int VerifyCode) {
		this.VerifyCode = VerifyCode;
	}
    public void setContentSize(int d)
    {
    	this.ContentSize=d;
    }
    
    public int getPrefix() {
  		return this.Prefix;
  	}
      public int getVersion() {
  		return this.Version;
  	}
      public int getEncryptType() {
  		return this.EncryptType;
  	}
      public int getSN() {
    		return this.SN;
    	}
      public int getChannel() {
  		return this.Channel;
  	}
      public int getErrorCode() {
  		return this.ErrorCode;
  	}
      public int getDeviceID() {
  		return this.DeviceID;
  	}
      public int getMessageType() {
  		return this.MessageType;
  	}
      public byte[] getCommandID() {
  		return this.CommandID;
  	}
      public int getReserve() {
    		return this.Reserve;
    	}
    
      public byte[] getContent() {
	        return	this.Content;
	}
      public int getHeadVerifyCode() {
    	  return this.HeadVerifyCode;
  	}
      public int getVerifyCode() {
    	  return this.VerifyCode;
  	}
      public int getContentSize()
      {
    	  return this.ContentSize;
      }
      public byte[] getAllContent()
      {
    	  return this.AllContent;
      }
     
      public static String StringTokenizer(String s)
      {
    	  String tempString="";
    	  tempString+=s.substring(2, 4);
    	  tempString+=s.substring(0, 2);
    	  return tempString;
      }
      public static String addZeroForNum(String str, int strLength) {
    	    int strLen = str.length();
    	    StringBuffer sb = null;
    	     while (strLen < strLength) {
    	           sb = new StringBuffer();
    	           sb.append("0").append(str);// 左补0
    	        // sb.append(str).append("0");//右补0
    	           str = sb.toString();
    	           strLen = str.length();
    	     }
    	    return str;
    	}
}
