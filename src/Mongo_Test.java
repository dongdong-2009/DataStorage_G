import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;



public class Mongo_Test
{


    private String Host = "192.168.137.21";
    private int Port = 27017;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;

    public void Run()
    {

        Connect();

        GetCollection("show");

       // Insert();

       // Select();

       // Update();

        Delete();
    }

    private void Connect()
    {
        try
        {
            mongoClient = new MongoClient(Host, Port);

            mongoDatabase = mongoClient.getDatabase("Test");

            System.out.println("Connect to database successfully");

        }
        catch (Exception ex)
        {
            System.err.println(("连接错误：" + ex.getMessage()));
        }
    }

    private void CreateCollect(String name)
    {
        try
        {
            mongoDatabase.createCollection(name);
            System.out.println("集合创建成功");
        }
        catch (Exception ex)
        {
            System.err.println(("创建集合错误：" + ex.getMessage()));
        }

    }

    private void GetCollection(String name)
    {
        try
        {

            collection = mongoDatabase.getCollection(name);

            System.out.println("得到集合成功");


        }
        catch (Exception ex)
        {
            System.err.println(("得到集合错误：" + ex.getMessage()));
        }

    }


    private void Insert()
    {
        try
        {


            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");

            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");


        }
        catch (Exception ex)
        {
            System.err.println(("得到集合错误：" + ex.getMessage()));
        }

    }

    private void Select()
    {
        try
        {


            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext())
            {
                System.out.println(mongoCursor.next());
            }


        }
        catch (Exception ex)
        {
            System.err.println(("得到集合错误：" + ex.getMessage()));
        }
    }


    private void Update()
    {
        try
        {



            //更新文档   将文档中likes=100的文档修改为likes=200
            collection.updateMany(Filters.eq("likes", 200), new Document("$set",new Document("likes",3000)));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }


        }
        catch (Exception ex)
        {
            System.err.println(("得到集合错误：" + ex.getMessage()));
        }
    }

    private void Delete()
    {
        try
        {





            //删除符合条件的第一个文档
            collection.deleteOne(Filters.eq("likes", 3000));
            //删除所有符合条件的文档
            collection.deleteMany (Filters.eq("likes", 3000));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }


        }
        catch (Exception ex)
        {
            System.err.println(("得到集合错误：" + ex.getMessage()));
        }
    }


}
