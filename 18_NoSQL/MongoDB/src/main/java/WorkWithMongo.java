import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class WorkWithMongo {
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;


    public WorkWithMongo(MongoClient mongoClient, List<String> lines) {
        mongoDatabase = mongoClient.getDatabase("students");
        collection = mongoDatabase.getCollection("students");
        collection.drop();
        createCollection(collection,lines);
    }

    static void createCollection(MongoCollection<Document> collection, List<String> lines) {
        List<Document> documents = new ArrayList<>();
        for (String line : lines) {
            String[] strings = line.split(",", 3);
            String name = strings[0];
            int age = Integer.parseInt(strings[1]);
            String courses = strings[2].replace("\"", "");

            Document document = new Document()
                    .append("name", name)
                    .append("age", age)
                    .append("courses", courses);

            documents.add(document);
        }
        collection.insertMany(documents);
    }

    public long totalCountStudents() {
        return collection.countDocuments();
    }

    public long studentsOver40Years() {
        return collection.countDocuments(gt("age", 40));
    }

    public String youngestStudent() {
        Document youngestStudent = collection.find().sort(ascending("age")).first();
        return Objects.requireNonNull(youngestStudent).getString("name");
    }

    public String listOfCoursesOldestStudents() {
        Document oldestStudents = collection.find().sort(descending("age")).first();
        return Objects.requireNonNull(oldestStudents).getString("courses");
    }
}
