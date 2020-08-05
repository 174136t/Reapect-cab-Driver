package com.nexeyotest.respectcab;

public class Payment_model {
    public String paid_date;
    public double totalcommission;
    public double totalearning;
    public double paid_amount;

    public Payment_model() {

    }

    public Payment_model(String paid_date, double totalcommission, double totalearning, double paid_amount) {
        this.paid_date = paid_date;
        this.totalcommission = totalcommission;
        this.totalearning = totalearning;
        this.paid_amount = paid_amount;
    }

    public String getpaid_date() {
        return paid_date;
    }

    public void setpaid_date(String paid_date) {
        this.paid_date = paid_date;
    }

    public double getTotalcommission() {
        return totalcommission;
    }

    public void setTotalcommission(double totalcommission) {
        this.totalcommission = totalcommission;
    }

    public double getpaid_amount() {
        return paid_amount;
    }

    public void setpaid_amount(double paid_amount) {
        this.paid_amount = paid_amount;
    }


    public double getTotalearning() {
        return totalearning;
    }

    public void setTotalearning(double totalearning) {
        this.totalearning = totalearning;
    }
}
