import java.io.File;
import java.util.ArrayList;

public class Main {
    public static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "D:/src";
        String dstFolder = "D:/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles(); //39

        ArrayList<File[]> fileList = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        int processors = Runtime.getRuntime().availableProcessors(); //12

        int size = files.length / processors; //3

        for (int i = 0; i <= processors; i++) {
            if (i == processors) {
                int newSize = files.length - size * processors;
                File[] newFiles = new File[newSize];
                System.arraycopy(files, size * i, newFiles, 0, newSize);
                fileList.add(newFiles);
                continue;
            }
            File[] newFiles = new File[size];
            System.arraycopy(files, size * i, newFiles, 0, size);
            fileList.add(newFiles);
        }

        for (File[] filesPart : fileList) {
            Thread thread = new Thread(new ImageResizer(filesPart, newWidth, dstFolder, start));
            threads.add(thread);
            thread.start();
        }
        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}