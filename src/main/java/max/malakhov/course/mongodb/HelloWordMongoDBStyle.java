package max.malakhov.course.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class HelloWordMongoDBStyle {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB database = client.getDB("course");
              DBCollection collection = database.getCollection("hello");
              DBObject document = collection.findOne();
              
              System.out.println(document);
          }
}
