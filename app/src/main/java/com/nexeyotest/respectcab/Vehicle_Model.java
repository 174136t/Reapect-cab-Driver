package com.nexeyotest.respectcab;

public class Vehicle_Model {
    public String vdidname;
    public String vehicleid;
    public String name;
    public String plateno;
    public String vehicletype;
    public String charges;


    public Vehicle_Model() {

    }

    public Vehicle_Model(String vdidname, String vehicleid, String name, String plateno, String vehicletype, String charges) {
        this.vdidname = vdidname;
        this.vehicleid = vehicleid;
        this.name = name;
        this.plateno = plateno;
        this.vehicletype = vehicletype;
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDriverid() {
        return vdidname;
    }

    public void setDriverid(String vdidname) {
        this.vdidname = vdidname;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getName2() {
        return name;
    }

    public void setName2(String name) {
        this.name = name;
    }

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }
}
