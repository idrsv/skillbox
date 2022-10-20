import com.mongodb.MongoClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static final String path = "src/main/resources/mongo.csv";
    public static final String url =  "127.0.0.1";
    public static final int port = 27017;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        MongoClient mongoClient = new MongoClient(url, port);

        WorkWithMongo workWithMongo = new WorkWithMongo(mongoClient, lines);

        System.out.println("Общее количество студентов в базе: " + workWithMongo.totalCountStudents());

        System.out.println("Количество студентов старше 40 лет: " + workWithMongo.studentsOver40Years());

        System.out.println("Имя самого молодого студента: " + workWithMongo.youngestStudent());

        System.out.println("Список курсов самого старого студента: " + workWithMongo.listOfCoursesOldestStudents());
    }
}