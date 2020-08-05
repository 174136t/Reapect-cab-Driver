package com.nexeyotest.respectcab;

public class User_Model {
    public String mobile;
    public String email;
    public Integer index;
    public String totalearn;
    public String dname;
    public String dmiddlename;


    public User_Model() {

    }


    public User_Model(String mobile, String email, Integer index, String totalearn, String dname, String dmiddlename) {
        this.mobile = mobile;
        this.email = email;
        this.index = index;
        this.totalearn = totalearn;
        this.dname = dname;
        this.dmiddlename = dmiddlename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    public String getTotalearn() {
        return totalearn;
    }

    public void setTotalearn(String totalearn) {
        this.totalearn = totalearn;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDmiddlename() {
        return dmiddlename;
    }

    public void setDmiddlename(String dmiddlename) {
        this.dmiddlename = dmiddlename;
    }
}
