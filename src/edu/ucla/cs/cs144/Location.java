package edu.ucla.cs.cs144;

/**
 * Created by kevin on 2/25/15.
 */
public class Location {
    private double latitude;
    private double longitude;
    private String name;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
