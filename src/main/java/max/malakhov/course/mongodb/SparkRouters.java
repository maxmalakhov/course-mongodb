package max.malakhov.course.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-02-28
 */
public class SparkRouters {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "Hello World";
            }
        });

        Spark.get(new Route("/test") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "This is a test page";
            }
        });

        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(final Request request, final Response response) {
                return request.params(":thing");
            }
        });
    }
}
