import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Flight> flights = findPlanesLeavingInTheNextTwoHours(Airport.getInstance());
        flights.forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date date = new Date(System.currentTimeMillis());
        Date newDate = new Date(System.currentTimeMillis() + 7200000);

        List<Flight> flights = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getDate().after(date) && flight.getDate().before(newDate))
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .collect(Collectors.toList());
        return flights;
    }
}