import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class LinkMapper extends RecursiveTask<String> {
    private String url;
    private CopyOnWriteArraySet<String> links;

    public LinkMapper(String url) {
        this.url = url;
        this.links = new CopyOnWriteArraySet<>();
    }

    public LinkMapper(String url, CopyOnWriteArraySet<String> links) {
        this.url = url;
        this.links = links;
    }

    @Override
    protected String compute() {
        String tabulate = StringUtils.repeat("\t",
                url.lastIndexOf("/") != url.length() - 1 ? StringUtils.countMatches(url, "/") - 2
                        : StringUtils.countMatches(url, "/") - 3);

        StringBuilder stringBuffer = new StringBuilder(tabulate + url + "\n");
        Set<LinkMapper> task = new TreeSet<>(Comparator.comparing(o -> o.url));

        getChildrenLinks(task);

        for (LinkMapper linkMapper : task) {
            stringBuffer.append(linkMapper.join());
        }
        return stringBuffer.toString();
    }

    private void getChildrenLinks(Set<LinkMapper> task) {
        Document document;
        Elements elements;
        try {
            Thread.sleep(100);
            document = Jsoup.connect(url).ignoreContentType(true).maxBodySize(0).get();
            elements = document.select("a[href]");
            for (Element element : elements) {
                String attr = element.absUrl("href");
                if (attr.startsWith(url) && !attr.isEmpty()
                        && !attr.contains("#") && !links.contains(attr)) {
                    LinkMapper linkMapper = new LinkMapper(attr, links);
                    linkMapper.fork();
                    task.add(linkMapper);
                    links.add(attr);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

