package max.malakhov.course.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-03-01
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        // Configure Freemarker
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWordFreemarkerStyle.class, "/");

        // Configure Routers
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try{
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple","orange","banana","peach"));

                    Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");

                    fruitPickerTemplate.process(fruitsMap, writer);

                } catch (Exception e) {
                    e.printStackTrace(new PrintWriter(writer));
                }
                return writer;
            }
        });

        Spark.post(new Route("/favorite_fruit"){
            @Override
            public Object handle(final Request request, final Response response) {
                final String fruit = request.params("fruit");
                if (fruit == null) {
                    return "Why don't you pick one?";
                } else {
                    return "You favorite fruit is "+fruit;
                }
            }
        });
    }
}
