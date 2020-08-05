package com.nexeyotest.respectcab;

public class PackageModel {
    public String initialdistance;
    public String initialcost;
    public String initialhours;
    public String nexthourcost;
    public String nexthours;
    public String packagen;
    public String vehicletype;
    public String waitingcost;
    public String pkgid;
    public String tripstatus;
    public String assignid;
    public String driverid;
    public String customerphone;
    public String nextcost;

    public PackageModel() {

    }

    public PackageModel(String initialdistance, String initialcost, String initialhours, String nexthourcost, String nexthours, String packagen, String vehicletype, String waitingcost, String pkgid, String tripstatus, String assignid, String driverid, String customerphone, String nextcost) {
        this.initialdistance = initialdistance;
        this.initialcost = initialcost;
        this.initialhours = initialhours;
        this.nexthourcost = nexthourcost;
        this.nexthours = nexthours;
        this.packagen = packagen;
        this.vehicletype = vehicletype;
        this.waitingcost = waitingcost;
        this.pkgid = pkgid;
        this.tripstatus = tripstatus;
        this.assignid = assignid;
        this.driverid = driverid;
        this.customerphone = customerphone;
        this.nextcost = nextcost;
    }

    public String getInitialdistance() {
        return initialdistance;
    }

    public void setInitialdistance(String initialdistance) {
        this.initialdistance = initialdistance;
    }

    public String getInitialcost() {
        return initialcost;
    }

    public void setInitialcost(String initialcost) {
        this.initialcost = initialcost;
    }

    public String getInitialhours() {
        return initialhours;
    }

    public void setInitialhours(String initialhours) {
        this.initialhours = initialhours;
    }

    public String getNexthourcost() {
        return nexthourcost;
    }

    public void setNexthourcost(String nexthourcost) {
        this.nexthourcost = nexthourcost;
    }

    public String getNexthours() {
        return nexthours;
    }

    public void setNexthours(String nexthours) {
        this.nexthours = nexthours;
    }

    public String getPackagen() {
        return packagen;
    }

    public void setPackagen(String packagen) {
        this.packagen = packagen;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getWaitingcost() {
        return waitingcost;
    }

    public void setWaitingcost(String waitingcost) {
        this.waitingcost = waitingcost;
    }

    public String getPkgid() {
        return pkgid;
    }

    public void setPkgid(String pkgid) {
        this.pkgid = pkgid;
    }

    public String getTripstatus() {
        return tripstatus;
    }

    public void setTripstatus(String tripstatus) {
        this.tripstatus = tripstatus;
    }

    public String getAssignid() {
        return assignid;
    }

    public void setAssignid(String assignid) {
        this.assignid = assignid;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getNextcost() {
        return nextcost;
    }

    public void setNextcost(String nextcost) {
        this.nextcost = nextcost;
    }
}
