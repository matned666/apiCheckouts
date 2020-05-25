package eu.mrndesign.matned.weatherApi.model;

public class Coord {
    double lon;
    double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
