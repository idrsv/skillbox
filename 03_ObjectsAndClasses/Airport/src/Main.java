import com.skillbox.airport.Airport;
import transport.*;

public class Main
{
    public static void main(String[] args)
    {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts());
        System.out.println(airport.getAllAircrafts().size());
    }
}
///aircraftsCount