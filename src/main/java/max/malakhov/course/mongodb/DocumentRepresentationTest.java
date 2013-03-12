package max.malakhov.course.mongodb;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Max Malakhov <malakhovbox@gmail.com>
 * @version 0.1
 * @since 2013-03-12
 */
public class DocumentRepresentationTest {
    public static void main(String[] args) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("userName","Max");
        doc.put("birthDate", new Date(234234234));
        doc.put("programmer", true);
        doc.put("age", 29);
        doc.put("languages", Arrays.asList("Java","JavaScript"));
        doc.put("address", new BasicDBObject("street", "3-ya Peskovskaya")
        .append("town", "Kursk").append("zip","205023"));
    }
}
