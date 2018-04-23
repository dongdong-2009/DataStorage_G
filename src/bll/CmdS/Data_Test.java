package bll.CmdS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tendency.cb.Info.ObjectInfo;
import com.tendency.cb.mogo.MongoDBDaoImpl;
import com.tendency.cb.mysql.MysqHelp;
import com.tendency.cb.util.ProtocolUtils;

public class Data_Test
{

	public final String NAME = "命令_数据";

	private MongoDBDaoImpl MK;
	private int DataSize;
	private String SpeId;
	private boolean IsGetLid = true;

	private List<DBObject> LDB = new ArrayList<DBObject>();

	public void Run(String content)
	{

		int length = content.length();

		if (length >= 25 * 2)
		{
			String SubContent = content.substring(23 * 2, length - 4);

			if (!SubContent.equals(""))
			{

				String T_Sn = content.substring(12, 16);

				String T_deviceid = content.substring(18, 26);
				int T_deviceid_ = (ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(T_deviceid)));

				String T_Reserve = content.substring(32, 40);

				String T_SUBDEVTYPE = T_Reserve.substring(4, 8);

				String Speid = SubContent.substring(0, 8);
				Speid = String.valueOf(ProtocolUtils.byte4int(ProtocolUtils.hexStringToByte(Speid)));

				String Version = SubContent.substring(8, 12);
				String Version1 = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(Version)));

				String subcommadn = SubContent.substring(12, 16);
				String subcommadn1 = String.valueOf(ProtocolUtils.byteToInt(ProtocolUtils.hexStringToByte(subcommadn)));

				String Comment = SubContent.substring(16, SubContent.length());

			}

		}

	}

	private Long GetLastId(int size, String speid)
	{
		long LastId = 0;
		try
		{

			MysqHelp MysqHelp_ = new MysqHelp();
			for (int i = 0; i < 3; i++)
			{
				LastId = MysqHelp_.GetCallCount("GetLID(?,?,?)", size, speid);
				if (LastId != 9999)
				{
					IsGetLid = false;
					break;
				}
				else
				{
					IsGetLid = true;
					Thread.sleep(1000);
				}

			}
			return LastId;
		}
		catch (Exception ex)
		{
			return LastId;
		}

	}

	private Date GetDate()
	{
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTime();
	}

}
