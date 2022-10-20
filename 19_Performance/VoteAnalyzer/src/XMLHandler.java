import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {

//    private Voter voter;
//    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
//    private HashMap<Voter, Byte> voterIntegerHashMap;
//
//    public XMLHandler(){
//        voterIntegerHashMap = new HashMap<>();
//    }
    int limit = 5000000;
    int number = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("voter") && number < limit) {
                    String name = attributes.getValue("name");
                    String birthDate = attributes.getValue("birthDay");
//                    Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
//                    voter = new Voter(attributes.getValue("name"), birthDay);
                    try {
                        DBConnection.countVoter(name, birthDate);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    number++;
                }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if(qName.equals("voter")){
//            voter = null;
//        }
//    }
//
//    public void printDuplicatedVoters(){
//        for (Voter voter : voterIntegerHashMap.keySet()){
//            int count = voterIntegerHashMap.get(voter);
//            if (count > 1){
//                System.out.println(voter.toString() + " - " + count);
//            }
//        }
//    }
}
