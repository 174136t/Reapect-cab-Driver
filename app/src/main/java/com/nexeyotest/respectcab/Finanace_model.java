package com.nexeyotest.respectcab;

public class Finanace_model {
    public String tripstartdate;
    public double totalcommission;
    public double totalearning;

    public Finanace_model() {

    }

    public Finanace_model(String tripstartdate, double totalcommission, double totalearning) {
        this.tripstartdate = tripstartdate;
        this.totalcommission = totalcommission;
        this.totalearning = totalearning;
    }

    public String getTripstartdate() {
        return tripstartdate;
    }

    public void setTripstartdate(String tripstartdate) {
        this.tripstartdate = tripstartdate;
    }

    public double getTotalcommission() {
        return totalcommission;
    }

    public void setTotalcommission(double totalcommission) {
        this.totalcommission = totalcommission;
    }

    public double getTotalearning() {
        return totalearning;
    }

    public void setTotalearning(double totalearning) {
        this.totalearning = totalearning;
    }
}
