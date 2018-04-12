package com.tendency.cb.mogo;


import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.tendency.cb.util.ProtocolUtils;

import util.Log;

public class MongoDBDaoImpl
{

    public MongoClient mongoClient = null;

    @SuppressWarnings("deprecation")
    public MongoDBDaoImpl()
    {
        if (mongoClient == null)
        {
            try
            {
                MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
                buide.connectionsPerHost(100);// 与目标数据库可以建立的最大链接数
                buide.connectTimeout(1000 * 60 * 20);// 与数据库建立链接的超时时间
                buide.maxWaitTime(100 * 60 * 5);// 一个线程成功获取到一个可用数据库之前的最大等待时间
                buide.threadsAllowedToBlockForConnectionMultiplier(100);

                //buide.socketTimeout(0);
                buide.socketKeepAlive(true);
                MongoClientOptions myOptions = buide.build();
                mongoClient = new MongoClient(ProtocolUtils.GetConfg().MongoIP + ":" + ProtocolUtils.GetConfg().MongoPort, myOptions);
            }
            catch (Exception e)
            {
                     
                Log.Error(MessageFormat.format("CMDID:9==具体操作:{0}==异常原因:{1}", "初始化Mogo异常", e.getMessage()));
            }
        }
    }

    public MongoClient GetMongoClient()
    {
        return mongoClient;
    }

    public DB getDb(String dbName)
    {
        // TODO Auto-generated method stub
        return mongoClient.getDB(dbName);
    }

    public DBCollection getCollection(String dbName, String collectionName)
    {
        // TODO Auto-generated method stub
        DB db = mongoClient.getDB(dbName);
        return db.getCollection(collectionName);
    }

    public boolean inSert(String dbName, String collectionName, String keys, Object values)
    {
        // TODO Auto-generated method stub
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        long num = dbCollection.count();
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        dbCollection.insert(doc);
        if (dbCollection.count() - num > 0)
        {
            System.out.println("添加数据成功！！！");
            return true;
        }
        return false;
    }

    public boolean delete(String dbName, String collectionName, String keys, Object values)
    {
        // TODO Auto-generated method stub
        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        writeResult = dbCollection.remove(doc);
        if (writeResult.getN() > 0)
        {
            System.out.println("删除数据成功!!!!");
            return true;
        }
        return false;
    }

    public ArrayList<DBObject> find(String dbName, String collectionName, int num)
    {
        // TODO Auto-generated method stub
        int count = num;
        ArrayList<DBObject> list = new ArrayList<DBObject>();
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor dbCursor = dbCollection.find();
        if (num == -1)
        {
            while (dbCursor.hasNext())
            {
                list.add(dbCursor.next());
            }
        }
        else
        {
            while (dbCursor.hasNext())
            {
                if (count == 0)
                {
                    break;
                }
                list.add(dbCursor.next());
                count--;
            }
        }
        return list;
    }

    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue)
    {
        // TODO Auto-generated method stub

        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        writeResult = dbCollection.update(oldValue, newValue);
        if (writeResult.getN() > 0)
        {
            System.out.println("数据更新成功");
            return true;
        }
        return false;
    }

    public boolean isExit(String dbName, String collectionName, String key, Object value)
    {
        // TODO Auto-generated method stub
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(key, value);
        if (dbCollection.count(doc) > 0)
        {
            return true;
        }
        return false;
    }


    public boolean PipeWatch(String dbName, String collectionName, List<DBObject> BD, String time_)
    {
        // TODO Auto-generated method stub
        try
        {
            long starTime = System.currentTimeMillis();
            DB db = mongoClient.getDB(dbName);
            DBCollection dbCollection = db.getCollection(collectionName);

            WriteResult W = dbCollection.insert(BD);
            long endTime = System.currentTimeMillis();
            long Time = endTime - starTime;
          
            Log.Info(MessageFormat.format("CMDID:10==具体操作:{0}==数量:{1}==执行时间:{2}", "批量插入Mongo", BD.size(), Time));
            return true;
        }
        catch (Exception e)
        {
                     
            Log.Error(MessageFormat.format("CMDID:9==具体操作:{0}==异常原因:{1}", "批量插入Mongo异常", e.getMessage()));
        }
        return false;

    }

    public boolean isConnect()
    {
        // TODO Auto-generated method stub
        DB db = mongoClient.getDB("tendency");

        return true;

    }

}
