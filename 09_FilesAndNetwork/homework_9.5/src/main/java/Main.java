import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import core.Line;
import core.Metro;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static final String URL = "https://www.moscowmap.ru/metro.html#lines";
    private static final String PATH = "src/main/resources/mosMetro.json";


    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect(URL).maxBodySize(0).get();
            Elements elementsOfLines = document.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
            List<Line> listOfLines = new ArrayList<>();

            Map<String, String> namesOfLines = elementsOfLines.stream()
                    .collect(Collectors.toMap((k) -> k.attr("data-line"), Element::text));
            namesOfLines.forEach((k, v) -> listOfLines.add(new Line(k, v)));

            Elements elementsOfStations = document.getElementsByClass("js-metro-stations");
            Map<String, List<Station>> listOfStations = new HashMap<>();
            Map<String, Integer> countStationsOnLine = new HashMap<>();
            elementsOfStations.forEach(el -> el.children().forEach(element -> {
                if (!listOfStations.containsKey(el.attr("data-line"))) {
                    listOfStations.put((el.attr("data-line")), new ArrayList<>());
                }
                listOfStations.get(el.attr("data-line")).add(new Station(element.getElementsByClass("name").text()));
                countStationsOnLine.put((el.attr("data-line")), (listOfStations.get(el.attr("data-line")).size()));
            }));
            for(String entry : countStationsOnLine.keySet() ){
                String key = entry.toString();
                String value = countStationsOnLine.get(entry).toString();
                System.out.println("Номер ветки: " + key + "\t Количество станций: " + value);
            }

            Metro metro = new Metro(listOfLines, listOfStations);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(PATH), metro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}