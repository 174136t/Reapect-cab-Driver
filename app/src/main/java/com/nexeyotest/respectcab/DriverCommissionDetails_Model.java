package com.nexeyotest.respectcab;

public class DriverCommissionDetails_Model {
    public String tripstartdate;
    public double tripcommission;
    public double triptotalcost;
    public String tripid;

    public DriverCommissionDetails_Model() {

    }

    public DriverCommissionDetails_Model(String tripstartdate, double tripcommission, double triptotalcost, String tripid) {
        this.tripstartdate = tripstartdate;
        this.tripcommission = tripcommission;
        this.triptotalcost = triptotalcost;
        this.tripid = tripid;
    }

    public String getTripstartdate() {
        return tripstartdate;
    }

    public void setTripstartdate(String tripstartdate) {
        this.tripstartdate = tripstartdate;
    }

    public double getTripcommission() {
        return tripcommission;
    }

    public void setTripcommission(double tripcommission) {
        this.tripcommission = tripcommission;
    }

    public double getTriptotalcost() {
        return triptotalcost;
    }

    public void setTriptotalcost(double triptotalcost) {
        this.triptotalcost = triptotalcost;
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid) {
        this.tripid = tripid;
    }
}
