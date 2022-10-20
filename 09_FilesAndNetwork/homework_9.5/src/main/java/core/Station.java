package core;

import com.fasterxml.jackson.annotation.JsonValue;

public class Station {
    @JsonValue
    private String nameStation;

    public Station(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    @Override
    public String toString() {
        return nameStation;
    }
}
