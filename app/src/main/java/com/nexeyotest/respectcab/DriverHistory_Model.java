package com.nexeyotest.respectcab;

public class DriverHistory_Model {

    public String driverid;
    public String tripstartdate;
    public String startaddress;
    public String endaddress;
    public String tripid;
    public String tripstarttime;
    public String tripendtime;
    public double triptotalcost;
    public double tripcommission;
    public String paymentmethod;


    public DriverHistory_Model() {
    }

    public DriverHistory_Model(String driverid, String tripstartdate, String startaddress, String endaddress, String tripid, String tripstarttime, String tripendtime, double triptotalcost, double tripcommission, String paymentmethod) {

        this.driverid = driverid;
        this.tripstartdate = tripstartdate;
        this.startaddress = startaddress;
        this.endaddress = endaddress;
        this.tripid = tripid;
        this.triptotalcost = triptotalcost;
        this.tripcommission = tripcommission;
        this.tripstarttime = tripstarttime;
        this.tripendtime = tripendtime;
        this.paymentmethod = paymentmethod;
    }


    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }


    public String getStartaddress() {
        return startaddress;
    }

    public void setStartaddress(String startaddress) {
        this.startaddress = startaddress;
    }

    public String getEndaddress() {
        return endaddress;
    }

    public void setEndaddress(String endaddress) {
        this.endaddress = endaddress;
    }

    public String getTripid() {
        return tripid;
    }

    public void setTripid(String tripid) {
        this.tripid = tripid;
    }

    public double getTriptotalcost() {
        return triptotalcost;
    }

    public void setTriptotalcost(double triptotalcost) {
        this.triptotalcost = triptotalcost;
    }

    public double getTripcommission() {
        return tripcommission;
    }

    public void setTripcommission(double tripcommission) {
        this.tripcommission = tripcommission;
    }

    public String getTripstartdate() {
        return tripstartdate;
    }

    public void setTripstartdate(String tripstartdate) {
        this.tripstartdate = tripstartdate;
    }

    public String getTripstarttime() {
        return tripstarttime;
    }

    public void setTripstarttime(String tripstarttime) {
        this.tripstarttime = tripstarttime;
    }

    public String getTripendtime() {
        return tripendtime;
    }

    public void setTripendtime(String tripendtime) {
        this.tripendtime = tripendtime;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
