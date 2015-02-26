package edu.ucla.cs.cs144;

import java.util.Date;

/**
 * Created by kevin on 2/25/15.
 */
public class Bid {
    private User bidder;
    private Date time;
    private double amount;

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
