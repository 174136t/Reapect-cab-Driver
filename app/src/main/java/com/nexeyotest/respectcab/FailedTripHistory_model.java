package com.nexeyotest.respectcab;

public class FailedTripHistory_model {

    public String driverid;
    public String date;
    public String startaddress;
    public String endaddress;
    public Long tripid;
    public String starttime;
    public String endtime;
    public double tripcost;
//    public double tripcommission;
//    public String paymentmethod;


    public FailedTripHistory_model() {
    }

    public FailedTripHistory_model(String driverid, String tripstartdate, String startaddress, String endaddress, Long tripid, String tripstarttime,
                                   String tripendtime,
                                   double triptotalcost
//                                   double tripcommission,
//                                   String paymentmethod
    ) {

        this.driverid = driverid;
        this.date = tripstartdate;
        this.startaddress = startaddress;
        this.endaddress = endaddress;
        this.tripid = tripid;
        this.tripcost = triptotalcost;
//        this.tripcommission = tripcommission;
        this.starttime = tripstarttime;
        this.endtime = tripendtime;
//        this.paymentmethod = paymentmethod;
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

    public Long getTripid() {
        return tripid;
    }

    public void setTripid(Long tripid) {
        this.tripid = tripid;
    }

    public double getTriptotalcost() {
        return tripcost;
    }

    public void setTriptotalcost(double triptotalcost) {
        this.tripcost = triptotalcost;
    }

//    public double getTripcommission() {
//        return tripcommission;
//    }
//
//    public void setTripcommission(double tripcommission) {
//        this.tripcommission = tripcommission;
//    }

    public String getTripstartdate() {
        return date;
    }

    public void setTripstartdate(String tripstartdate) {
        this.date = tripstartdate;
    }

    public String getTripstarttime() {
        return starttime;
    }

    public void setTripstarttime(String tripstarttime) {
        this.starttime = tripstarttime;
    }

    public String getTripendtime() {
        return endtime;
    }

    public void setTripendtime(String tripendtime) {
        this.endtime = tripendtime;
    }

//    public String getPaymentmethod() {
//        return paymentmethod;
//    }
//
//    public void setPaymentmethod(String paymentmethod) {
//        this.paymentmethod = paymentmethod;
//    }
}
