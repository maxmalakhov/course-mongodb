package max.malakhov.course.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class FieldSelectionTest {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB courseDB = client.getDB("course");
              DBCollection collection = courseDB.getCollection("fieldSelectionTest");

              collection.drop();

              for(int i = 0; i < 10; i++) {
                collection.insert(new BasicDBObject("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("z", new Random().nextInt(1000)));
              }

              QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70);

              System.out.println("Find All: ");
              DBCursor cursor = collection.find(builder.get(), new BasicDBObject("y",true).append("_id", false));
              try {
                 while (cursor.hasNext()) {
                     System.out.println(cursor.next());
                 }
              } finally {
                  cursor.close();
              }

          }
}
