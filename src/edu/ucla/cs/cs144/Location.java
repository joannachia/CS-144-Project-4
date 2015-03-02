package edu.ucla.cs.cs144;

/**
 * Created by kevin on 2/25/15.
 */
public class Location {
    private Double latitude = null;
    private Double longitude = null;
    private String name = null;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
