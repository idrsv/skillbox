package core;

import java.util.List;
import java.util.Map;

public class Metro {
    private List<Line> lines;
    private Map<String, List<Station>> stations;

    public Metro(List<Line> lines, Map<String, List<Station>> stations) {
        this.lines = lines;
        this.stations = stations;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Map<String, List<Station>> getStations() {
        return stations;
    }

    public void setStations(Map<String, List<Station>> stations) {
        this.stations = stations;
    }
}
