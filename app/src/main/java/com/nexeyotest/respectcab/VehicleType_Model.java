package com.nexeyotest.respectcab;

public class VehicleType_Model {
    public String vehicletype;
    public String charges;
    public String initialcharge;
    public String initialdistance;
    public String waitingcost;

    public VehicleType_Model(){

    }

    public VehicleType_Model(String vehicletype, String charges, String initialcharge, String initialdistance, String waitingcost) {
        this.vehicletype = vehicletype;
        this.charges = charges;
        this.initialcharge = initialcharge;
        this.initialdistance = initialdistance;
        this.waitingcost = waitingcost;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getInitialcharge() {
        return initialcharge;
    }

    public void setInitialcharge(String initialcharge) {
        this.initialcharge = initialcharge;
    }

    public String getInitialdistance() {
        return initialdistance;
    }

    public void setInitialdistance(String initialdistance) {
        this.initialdistance = initialdistance;
    }

    public String getWaitingcost() {
        return waitingcost;
    }

    public void setWaitingcost(String waitingcost) {
        this.waitingcost = waitingcost;
    }
}



