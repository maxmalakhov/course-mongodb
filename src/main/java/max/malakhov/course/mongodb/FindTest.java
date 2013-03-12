package max.malakhov.course.mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class FindTest {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB courseDB = client.getDB("course");
              DBCollection collection = courseDB.getCollection("findTest");

              collection.drop();

              for(int i = 0; i < 10; i++) {
                collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
              }

              System.out.println("Find One: "+collection.findOne());

              System.out.println("Find All: ");
              DBCursor cursor = collection.find();
              try {
                 while (cursor.hasNext()) {
                     System.out.println(cursor.next());
                 }
              } finally {
                  cursor.close();
              }

              System.out.println("Find count: "+collection.count());

              DB db = client.getDB("school");
              DBCollection people = db.getCollection("people");
              DBObject doc;
              doc = people.findOne();
              System.out.println(doc);
          }
}
