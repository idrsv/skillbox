import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            Path source = Paths.get(sourceDirectory);
            Path destination = Paths.get(destinationDirectory);
            CopyOption[] options = {StandardCopyOption.REPLACE_EXISTING};
            List<Path> newSource = Files.walk(source)
                    .collect(Collectors.toList());
            List<Path> newDestination = newSource.stream()
                    .map(source::relativize)
                    .map(destination::resolve)
                    .collect(Collectors.toList());

            for (int i = 0; i < newSource.size(); i++) {
                Files.copy(newSource.get(i), newDestination.get(i), options);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
//
//}

