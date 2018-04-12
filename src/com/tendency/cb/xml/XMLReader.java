package com.tendency.cb.xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tendency.cb.util.ProtocolUtils;

public class XMLReader
{
    private static String filename = "config.xml";
    private static Config config;

    /**
     * 从配置文件中读取参数并保存到Config类中,
     * 很多时候程序中会多次使用到配置中的参数,
     * 于是设置成静态方法,读取一次后就一直保存其中的参数，
     * 不再反复读取
     *
     * @return
     */
    public static Config loadconfig()
    {
        if (config == null)
        {
            config = getconfig();
        }
        return config;
    }

    public static Config loadconfig(String path)
    {
        if (config == null)
        {
            config = getconfig(path);
        }
        return config;
    }




    private static Config getconfig()
    {
        Config config = new Config();
        try
        {

            File f = new File(ProtocolUtils.getProjectPath() + "/" + filename);

            if (!f.exists())
            {
                System.out.println("  Error : Config file doesn't exist!");
            }
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(f);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("VALUE");
            data = (Element) itr.next();
            config.MongoIP = data.elementText("MongoIP").trim();
            config.MongoPipleTme = data.elementText("MongoPipleTme").trim();
            config.MongoPort = data.elementText("MongoPort").trim();

            config.MysqlPassWord = data.elementText("MysqlPassWord").trim();
            config.MysqlUrl = data.elementText("MysqlUrl").trim();
            config.MysqlUser = data.elementText("MysqlUser").trim();
            config.RedisIP = data.elementText("RedisIP").trim();
            config.RedisPipleTme = data.elementText("RedisPipleTme").trim();
            config.RedisPort = data.elementText("RedisPort").trim();
            config.CodethreadPoolCount = data.elementText("CodethreadPoolCount").trim();
            config.PatchMongoItemCount = data.elementText("PatchMongoItemCount").trim();
            //config.FTPURL=data.elementText("FTPURL").trim();
            config.ID = data.elementText("ID").trim();
        }
        catch (Exception ex)
        {
            System.out.println("Error : " + ex.toString());
        }
        return config;

    }

    private static Config getconfig(String path)
    {
        Config config = new Config();
        try
        {

            File f = new File(path + "/" + filename);

            if (!f.exists())
            {
                System.out.println("  Error : Config file doesn't exist!");
            }
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(f);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("VALUE");
            data = (Element) itr.next();
            config.MongoIP = data.elementText("MongoIP").trim();
            config.MongoPipleTme = data.elementText("MongoPipleTme").trim();
            config.MongoPort = data.elementText("MongoPort").trim();

            config.MysqlPassWord = data.elementText("MysqlPassWord").trim();
            config.MysqlUrl = data.elementText("MysqlUrl").trim();
            config.MysqlUser = data.elementText("MysqlUser").trim();
            config.RedisIP = data.elementText("RedisIP").trim();
            config.RedisPipleTme = data.elementText("RedisPipleTme").trim();
            config.RedisPort = data.elementText("RedisPort").trim();
            config.CodethreadPoolCount = data.elementText("CodethreadPoolCount").trim();
            config.PatchMongoItemCount = data.elementText("PatchMongoItemCount").trim();
            //config.FTPURL=data.elementText("FTPURL").trim();
            config.ID = data.elementText("ID").trim();
        }
        catch (Exception ex)
        {
            System.out.println("Error : " + ex.toString());
        }
        return config;

    }
}
