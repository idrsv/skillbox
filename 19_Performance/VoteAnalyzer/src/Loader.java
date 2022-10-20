import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Loader
{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        String fileName = "/Users/idrsv/IdeaProjects/java_basics/19_Performance/VoteAnalyzer/res/data-1572M.xml";
        String name = "Тувыкин Радим";



//        parseFileDom(fileName);

        parseFileSAX(fileName);
        long start = System.currentTimeMillis();
//        DBConnection.executeMultiInsert();
        DBConnection.customSelect(name);

        System.out.println("\n\tParsing file duration: " + (System.currentTimeMillis() - start) + " ms");
//
//        DBConnection.customSelect("Деревягин Иларион");

//        results();

//        usage =  23.079.336

//        parseFileSAX(fileName);

//        usage = 15.688.784 Integer
//        usage = 16.363.096 Byte

//        usage = 15.688.920 Byte

//        -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
//        usage = 15.467.168 Integer

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("------------------------------------------");
        System.out.println("\n\tИспользуемая память: " + usage);


    }

    private static void results(){
        //Printing results
        System.out.println("Voting station work times: ");
        for(Integer votingStation : voteStationWorkTimes.keySet())
        {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for(Voter voter : voterCounts.keySet())
        {
            Integer count = voterCounts.get(voter);
            if(count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }

    private static void parseFileSAX(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        parser.parse(new File(fileName), xmlHandler);
//        xmlHandler.printDuplicatedVoters();
    }

//    private static void parseFileDom(String fileName) throws Exception
//    {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        Document doc = db.parse(new File(fileName));
//
//        findEqualVoters(doc);
//        fixWorkTimes(doc);
//    }
//
//    private static void findEqualVoters(Document doc) throws Exception
//    {
//        NodeList voters = doc.getElementsByTagName("voter");
//        int votersCount = voters.getLength();
//        for(int i = 0; i < votersCount; i++)
//        {
//            Node node = voters.item(i);
//            NamedNodeMap attributes = node.getAttributes();
//
//            String name = attributes.getNamedItem("name").getNodeValue();
//            Date birthDay = birthDayFormat.parse(attributes.getNamedItem("birthDay").getNodeValue());
//            String birthDay = attributes.getNamedItem("birthDay").getNodeValue();
//
//            DBConnection.countVoter(name , birthDay);
//            Voter voter = new Voter(name, birthDay);
//            Integer count = voterCounts.get(voter);
//            voterCounts.put(voter, count == null ? 1 : count + 1);
//        }
//        DBConnection.executeMultiInsert();
//    }
//
//    private static void fixWorkTimes(Document doc) throws Exception
//    {
//        NodeList visits = doc.getElementsByTagName("visit");
//        int visitCount = visits.getLength();
//        for(int i = 0; i < visitCount; i++)
//        {
//            Node node = visits.item(i);
//            NamedNodeMap attributes = node.getAttributes();
//
//            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
//            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
//            WorkTime workTime = voteStationWorkTimes.get(station);
//            if(workTime == null)
//            {
//                workTime = new WorkTime();
//                voteStationWorkTimes.put(station, workTime);
//            }
//            workTime.addVisitTime(time.getTime());
//        }
//    }
}