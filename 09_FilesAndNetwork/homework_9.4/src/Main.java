import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;
        import java.io.*;
        import java.net.MalformedURLException;
        import java.net.URL;

public class Main {
    private static final String url = "https://www.instagram.com/idrsvv/";
    private static final String destinationDirectory = "/Users/idrsv/IdeaProjects/java_basics/09_FilesAndNetwork/homework_9.4/photos";

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("a[href]");
        for (Element el : elements) {
            String link = el.attr("href");
            System.out.println(link);
//            getImages(link);
        }
    }

//    private static void getImages(String src) throws IOException {
//        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationDirectory + src.substring(src.lastIndexOf("/"))))) {
//            URL url = new URL(src);
//            InputStream inputStream = url.openStream();
//            for (int i; (i = inputStream.read()) != -1; ) {
//                outputStream.write(i);
//            }
//        }
//    }
}