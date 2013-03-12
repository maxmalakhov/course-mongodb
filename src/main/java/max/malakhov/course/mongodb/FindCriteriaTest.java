package max.malakhov.course.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class FindCriteriaTest {
          public static void main(String[] args) throws UnknownHostException {
              MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

              DB courseDB = client.getDB("course");
              DBCollection collection = courseDB.getCollection("findTest");

              collection.drop();

              for(int i = 0; i < 10; i++) {
                collection.insert(new BasicDBObject("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
              }

              QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(90);
              DBObject query = new BasicDBObject("x",0)
                      .append("y", new BasicDBObject("$gt",10).append("$lt",90));

              System.out.println("Find One: "+collection.findOne(query));

              System.out.println("Find All: ");
              DBCursor cursor = collection.find(builder.get());
              try {
                 while (cursor.hasNext()) {
                     System.out.println(cursor.next());
                 }
              } finally {
                  cursor.close();
              }

              System.out.println("Find count: "+collection.count(query));

              DB db = client.getDB("test");
              DBCollection scores = db.getCollection("scores");

              System.out.println("Find All: ");
              DBCursor cursor2 = scores.find(QueryBuilder.start("type").is("quiz").and("score").greaterThan(20).lessThan(90).get());
              try {
                  while (cursor2.hasNext()) {
                      System.out.println(cursor2.next());
                  }
              } finally {
                  cursor2.close();
              }

          }
}
