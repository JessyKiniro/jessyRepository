package cn;

import java.util.ArrayList;

public class Location {

    private ArrayList<String> addresses;
    private ArrayList<Points> points;
    private ArrayList<ArrayList<String>> places;

    public ArrayList<String> getAddress() {
        return addresses;
    }

    public void setAddresses(ArrayList<String> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<Points> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Points> points) {
        this.points = points;
    }

    public ArrayList<ArrayList<String>> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<ArrayList<String>> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Location{" +
                "addresses=" + addresses +
                ", points=" + points +
                ", places=" + places +
                '}';
    }
}
