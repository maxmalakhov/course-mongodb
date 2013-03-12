package max.malakhov.course.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class SortSkipLimitTest {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB courseDB = client.getDB("course");
              DBCollection collection = courseDB.getCollection("dotNotationTest");

              collection.drop();

              Random rand = new Random();

              for(int i = 0; i < 10; i++) {
                collection.insert(new BasicDBObject("_id", i)
                    .append("start", new BasicDBObject("x",rand.nextInt(2)).append("y", rand.nextInt(90)+10))
                    .append("end", new BasicDBObject("x", rand.nextInt(2)).append("y", rand.nextInt(90) + 10)));
              }

              QueryBuilder builder = QueryBuilder.start();

              System.out.println("Find All: ");
              DBCursor cursor = collection.find(builder.get(), new BasicDBObject("start.y", true)).sort(new BasicDBObject("start.y",-1)).skip(2).limit(3);
              // /.append("_id",false));
              try {
                 while (cursor.hasNext()) {
                     System.out.println(cursor.next());
                 }
              } finally {
                  cursor.close();
              }

          }
}
