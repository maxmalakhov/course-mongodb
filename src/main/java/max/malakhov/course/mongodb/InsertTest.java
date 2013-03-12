package max.malakhov.course.mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class InsertTest {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB courseDB = client.getDB("course");
              DBCollection collection = courseDB.getCollection("insertTest");

              collection.drop();

              DBObject doc = new BasicDBObject("_id", new ObjectId()).append("x",1);
              DBObject doc2 = new BasicDBObject().append("x",2);

              System.out.println(doc);

              collection.insert(Arrays.asList(doc, doc2));

              System.out.println(doc);

              DB db = client.getDB("school");
              DBCollection people = db.getCollection("people");

              DBObject doc3 = new BasicDBObject("name", "Andrew Erlichson")
                      .append("company", "10gen");

              try {
                  people.insert(doc3);      // first insert
                  doc3.removeField("_id");  // remove the "_id" field
                  people.insert(doc3);      // second insert
              } catch (Exception e) {
                  e.printStackTrace();
              }

          }
}
