package com.practice.sqlitecrud.model;

import java.io.Serializable;


public class Person implements Serializable {
    int id;
    String pname;
    String paddress;
    String timedate;
    String pqualification;

    public Person() {
    }

    public Person(int id, String pname, String paddress, String timedate, String pqualification) {
        this.id = id;
        this.pname = pname;
        this.paddress = paddress;
        this.timedate = timedate;
        this.pqualification = pqualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    public String getPqualification() {
        return pqualification;
    }

    public void setPqualification(String pqualification) {
        this.pqualification = pqualification;
    }
}
