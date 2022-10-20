import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
//    public static long calculateFolderSize(String path) {
//        File source = new File(path);
//        long filesSize = 0L;
//        try {
//            if (source.isDirectory()){
//                File[] newFile = source.listFiles();
//                for (File file : newFile){
//                    filesSize += calculateFolderSize(file.getAbsolutePath());
//                }
//            }
//            else filesSize = source.length();
//        } catch (NullPointerException ex){
//            ex.getStackTrace();
//        }
//        return filesSize;
//    }

    public static long calculateFolderSize(String path) {
        try {
            long filesSize = 0L;
            filesSize = Files.walk(Paths.get(path))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .mapToLong(File::length)
                    .sum();
            return filesSize;
        } catch (IOException ex){
            ex.getStackTrace();
        }
        return 0;
    }
}
