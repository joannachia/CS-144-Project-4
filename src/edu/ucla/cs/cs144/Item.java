package edu.ucla.cs.cs144;

import java.util.Date;

/**
 * Created by kevin on 2/25/15.
 */
public class Item {
    private int id;
    private String name;
    private String[] categories;
    private double currently;
    private double buyPrice;
    private double firstBid;
    private int numberOfBids;
    private Bid[] bids;
    private Location location;
    private String country;
    private Date started;
    private Date ends;
    private User seller;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrently() {
        return currently;
    }

    public void setCurrently(double currently) {
        this.currently = currently;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnds() {
        return ends;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(double firstBid) {
        this.firstBid = firstBid;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Bid[] getBids() {
        return bids;
    }

    public void setBids(Bid[] bids) {
        this.bids = bids;
    }
}
