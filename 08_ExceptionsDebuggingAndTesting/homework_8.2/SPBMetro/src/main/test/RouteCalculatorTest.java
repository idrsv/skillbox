import core.Line;
import core.Station;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("RouteCalculatorTest")
public class RouteCalculatorTest {
    /**
     *
     *                                 line 2
     *
     *      line 1      st1 -- st2 -- st3/st5 -- st4
     *                                   |
     *                                   |
     *                                  st6
     *                                   |
     *                                   |
     *      line 3             st8 -- st9/st7 -- st10
     *
     */
    StationIndex stationIndex;

    List<Station> route;
    List<Station> routeWithOneConnection;
    List<Station> routeWithTwoConnection;
    List<Station> connection12;
    List<Station> connection23;

    RouteCalculator routeCalculator;

    Line line1, line2, line3;
    Station st1, st2, st3, st4, st5, st6, st7, st8, st9, st10;

    @Before
    public void setUp() throws Exception {
        route = new ArrayList<>();
        stationIndex = new StationIndex();
        routeWithOneConnection = new ArrayList<>();
        routeWithTwoConnection = new ArrayList<>();
        connection12 = new ArrayList<>();
        connection23 = new ArrayList<>();
        //Создаю линии
        line1 = new Line(1,"Первая линия");
        line2 = new Line(2,"Вторая линия");
        line3 = new Line(3,"Третья линия");
        //В индекс добавляю линии
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        //В линии добавляю станции
        line1.addStation(st1 = new Station("Первая станция",line1));
        line1.addStation(st2 = new Station("Вторая станция", line1));
        line1.addStation(st3 = new Station("Третья станция",line1));
        line1.addStation(st4 = new Station("Четвертая станция", line1));
        line2.addStation(st5 = new Station("Пятая станция",line2));
        line2.addStation(st6 = new Station("Шестая станция", line2));
        line2.addStation(st7 = new Station("Седьмая станция",line2));
        line3.addStation(st8 = new Station("Восьмая станция", line3));
        line3.addStation(st9 = new Station("Девятая станция", line3));
        line3.addStation(st10 = new Station("Десятая станция",line3));
        //В индекс добавляю станции
        stationIndex.addStation(st1);
        stationIndex.addStation(st2);
        stationIndex.addStation(st3);
        stationIndex.addStation(st4);
        stationIndex.addStation(st5);
        stationIndex.addStation(st6);
        stationIndex.addStation(st7);
        stationIndex.addStation(st8);
        stationIndex.addStation(st9);
        //Пересадки
        connection12.add(stationIndex.getStation("Третья станция"));
        connection12.add(stationIndex.getStation("Пятая станция"));
        connection23.add(stationIndex.getStation("Девятая станция"));
        connection23.add(stationIndex.getStation("Седьмая станция"));
        //Добавляю пересадки в индекс
        stationIndex.addConnection(connection12);
        stationIndex.addConnection(connection23);
        //
        routeCalculator = new RouteCalculator(stationIndex);
        //Маршрут с двумя пересадками
        routeWithTwoConnection.add(st1);
        routeWithTwoConnection.add(st2);
        routeWithTwoConnection.add(st3);
        routeWithTwoConnection.add(st5);
        routeWithTwoConnection.add(st6);
        routeWithTwoConnection.add(st7);
        routeWithTwoConnection.add(st9);
        routeWithTwoConnection.add(st10);
    }

    @Test
    @DisplayName("Продолжительность маршрута")
    public void testCalculatorDuration(){
        // 2.5 + 2.5 + 3.5 + 2.5 + 2.5 + 3.5 + 2.5 = 2.5 * 5 + 3.5 * 2 = 15 + 7 = 19.5;
        double actual = RouteCalculator.calculateDuration(routeWithTwoConnection);
        double expected = 19.5;
        double delta = 0.0;
        assertEquals(expected,actual,delta);
    }

    @Test
    @DisplayName("Кратчайший маршрут, когда станции на одной линии")
    public void shouldReturnShortestRouteWhenStationsOnOneLine() {
        int actual = routeCalculator.getShortestRoute(stationIndex.getStation("Первая станция"),stationIndex.getStation("Третья станция")).size();
        int expected = 3;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Кратчайший маршрут, с одной пересадкой")
    public void shouldReturnShortestRouteWithOneConnect() {
        int actual = routeCalculator.getShortestRoute(stationIndex.getStation("Первая станция"),stationIndex.getStation("Четвертая станция")).size();
        int expected = 4;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Кратчайший маршрут, с двумя пересадками")
    public void shouldReturnShortestRouteWithTwoConnect() {
        int actual = routeCalculator.getShortestRoute(stationIndex.getStation("Первая станция"),stationIndex.getStation("Восьмая станция")).size();
        int expected = 8;
        assertEquals(expected,actual);
    }
}
