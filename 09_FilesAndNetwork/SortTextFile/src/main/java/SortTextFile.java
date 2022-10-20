import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTextFile {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\ksit\\java_basics\\09_FilesAndNetwork\\SortTextFile\\src\\main\\resources\\Films.txt");
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        Collections.sort(lines);
        try{
            FileWriter writer = new FileWriter("C:\\Users\\ksit\\java_basics\\09_FilesAndNetwork\\SortTextFile\\src\\main\\resources\\final.txt");

            for(String line : lines)
            {
                writer.write(line);
                writer.write(System.getProperty("line.separator"));

            }

            writer.close();
        }catch (IOException ex){ex.printStackTrace();}
    }
}
