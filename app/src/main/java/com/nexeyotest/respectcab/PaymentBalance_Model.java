package com.nexeyotest.respectcab;

public class PaymentBalance_Model {
    public double balance;
    public double totalcommission;
    public double paidcommission;

    public PaymentBalance_Model() {
    }

    public PaymentBalance_Model(double balance, double totalcommission, double paidcommission) {
        this.balance = balance;
        this.totalcommission = totalcommission;
        this.paidcommission = paidcommission;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalcommission() {
        return totalcommission;
    }

    public void setTotalcommission(double totalcommission) {
        this.totalcommission = totalcommission;
    }

    public double getPaidcommission() {
        return paidcommission;
    }

    public void setPaidcommission(double paidcommission) {
        this.paidcommission = paidcommission;
    }
}
