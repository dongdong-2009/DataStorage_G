package bll.CmdS;

import com.tendency.cb.util.ProtocolUtils;

public class DevProtocol_Bll
{

	public void Format(String content)
	{

		String Prefix = content.substring(0, 2);
		String Version = content.substring(2, 6);
		String EncryptType = content.substring(6, 8);
		String SN = content.substring(8, 12);
		String Channel = content.substring(12, 14);
		String ErrorCode = content.substring(14, 18);
		String DeviceId = content.substring(18, 26);
		String MessageType = content.substring(26, 28);
		String CommandId = content.substring(28, 32);
		String Reserve = content.substring(32, 40);
		String ContentSize = content.substring(40, 44);

		int lenght = ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(ContentSize));
		String HeadVerify = content.substring(44, 46);
		String Content = content.substring(46, 46 + (lenght * 2));
		String VerifyCode = content.substring(46 + (lenght * 2), 46 + (lenght * 2) + 4);

	}

}
