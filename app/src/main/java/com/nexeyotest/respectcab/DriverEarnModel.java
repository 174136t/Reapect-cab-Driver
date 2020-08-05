package com.nexeyotest.respectcab;

public class DriverEarnModel {
    Object balance;
    String date;
    String driverid;
    String paidcommission;
    Object totalcommission;
    String totalearning;

    public DriverEarnModel() {

    }

    public DriverEarnModel(Object balance, String date, String driverid, String paidcommission, Object totalcommission, String totalearning) {
        this.balance = balance;
        this.date = date;
        this.driverid = driverid;
        this.paidcommission = paidcommission;
        this.totalcommission = totalcommission;
        this.totalearning = totalearning;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getPaidcommission() {
        return paidcommission;
    }

    public void setPaidcommission(String paidcommission) {
        this.paidcommission = paidcommission;
    }

    public Object getTotalcommission() {
        return totalcommission;
    }

    public void setTotalcommission(Object totalcommission) {
        this.totalcommission = totalcommission;
    }

    public String getTotalearning() {
        return totalearning;
    }

    public void setTotalearning(String totalearning) {
        this.totalearning = totalearning;
    }
}
