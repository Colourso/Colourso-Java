package top.colourso.MongoDB;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date 2022/4/5 19:26
 */
public class Main {

    //原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/mongodb/mongodb_java.html
    public static void main(String[] args) {
        try {

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // Now connect to your databases
            MongoDatabase mgdb = mongoClient.getDatabase("admin");
            // If MongoDB in secure mode, authentication is required.
            // boolean auth = mgdb.authenticate("", "");
            // System.out.println("Authentication: "+auth);

            System.out.println("Connect to database successfully!");
            System.out.println("MongoDatabase inof is : "+mgdb.getName());

            //
            MongoCollection<Document> collection = mgdb.getCollection("myTestCollection");

            System.out.println("Collection created successfully");

            System.out.println("当前数据库中的所有集合是：");

            for (String name : mgdb.listCollectionNames()) {
                System.out.println(name);
            }

            Document doc1 = new Document("_id", 2003)
                    .append("title", "MongoDB Insert Demo")
                    .append("description","database")
                    .append("likes", 30)
                    .append("by", "yiibai point");

            Document doc2 = new Document("_id", 2004)
                    .append("title", "MongoDB Insert Two")
                    .append("contact", new Document("phone", "604-555-0102")
                    .append("email", "bluecoffeebar@example.com")
                    .append("location", Arrays.asList(-73.97902, 40.8479556)))
                    .append("stars", 5)
                    .append("categories", Arrays.asList("Coffee", "Pastries"));

            List<Document> documents = new ArrayList<Document>();
            documents.add(doc1);
            documents.add(doc2);
            collection.insertMany(documents);
            System.out.println("Document inserted successfully");


            // 查找
            BasicDBObject queryRoot = new BasicDBObject();
            setFuzzyQueryByBaseProductName("Demo",queryRoot);
            FindIterable<Document> findIterable = collection.find(queryRoot);

            System.out.println(findIterable.first());



        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
}

    /**
     * 名称模糊查询 -- 验证ok
     *
     * @param condition
     * @param rootQueryObject
     */
    private static void setFuzzyQueryByBaseProductName(String condition, BasicDBObject rootQueryObject) {
        if(StringUtils.isNotBlank(condition)){
            String fieldName = "title";
            rootQueryObject.put(fieldName, new BasicDBObject("$regex", condition));
        }
    }
}
