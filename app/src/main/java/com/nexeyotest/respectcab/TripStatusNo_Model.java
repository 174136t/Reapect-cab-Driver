package com.nexeyotest.respectcab;

class TripStatusNo_Model {

    public int driverid;
    public String date;
    public String startaddress;
    public String endaddress;
    public int tripid;
    public String starttime;
    public String triptime;
    public double tripcost;
    public String totaldistance;
    public String waitingcost;
    public String waitingtime;

    public TripStatusNo_Model() {
    }



    public TripStatusNo_Model(int driverid, int tripid,
                              String totaldistance,
                              String waitingtime, String startaddress, String waitingcost,
                              String endaddress, double tripcost , String date, String starttime , String triptime){
        this.driverid = driverid;
        this.date = date;
        this.startaddress = startaddress;
        this.endaddress = endaddress;
        this.tripid = tripid;
        this.tripcost = tripcost;
        this.starttime = starttime;
        this.triptime = triptime;
        this.totaldistance = totaldistance;
        this.waitingcost = waitingcost;
        this.waitingtime = waitingtime;
    }


    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
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

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid) {
        this.tripid = tripid;
    }
    //
    public double getTripcost() {
        return tripcost;
    }

    public void setTripcost(double tripcost) {
        this.tripcost = tripcost;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getTripstarttime() {
        return starttime;
    }

    public void setTripstarttime(String tripstarttime) {
        this.starttime = tripstarttime;
    }
    //
    public String getTriptime() {
        return triptime;
    }

    public void setTriptime(String triptime) {
        this.triptime = triptime;
    }

    public String getTotaldistance() {
        return totaldistance;
    }

    public void setTotaldistance(String totaldistance) {
        this.totaldistance = totaldistance;
    }

    public String getWaitingcost() {
        return waitingcost;
    }

    public void setWaitingcost(String waitingcost) {
        this.waitingcost = waitingcost;
    }

    public String getWaitingtime() {
        return waitingtime;
    }

    public void setWaitingtime(String waitingtime) {
        this.waitingtime = waitingtime;
    }

}