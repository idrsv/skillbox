import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class WorkWithMongoTest {

    private WorkWithMongo workWithMongo;

    @Before
    public void setUp() throws IOException {
        MongoClient mongoClient = new MongoClient(Main.url, Main.port);
        List<String> lines = Files.readAllLines(Paths.get(Main.path));
        workWithMongo = new WorkWithMongo(mongoClient, lines);
    }

    @Test
    public void totalCountStudentsTest(){
        long actual = workWithMongo.totalCountStudents();
        assertEquals(100, actual);
    }

    @Test
    public void studentsOver40YearsTest(){
        long actual = workWithMongo.studentsOver40Years();
        assertEquals(5, actual);
    }

    @Test
    public void youngestStudentTest(){
        String actual = workWithMongo.youngestStudent();
        assertEquals("Opal Squires", actual);
    }

    @Test
    public void listOfCoursesOldestStudentsTest(){
        String actual = workWithMongo.listOfCoursesOldestStudents();
        assertEquals("Web,Python", actual);
    }
}
