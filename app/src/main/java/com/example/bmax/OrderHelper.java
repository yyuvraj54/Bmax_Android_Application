package com.example.bmax;

public class OrderHelper {
    String user,pass,h,w,g,ival,ageval;


    public OrderHelper() {
    }

    public OrderHelper(String user, String pass, String h, String w, String g,String ival,String ageval) {
        this.user = user;
        this.pass = pass;
        this.h = h;
        this.w = w;
        this.g = g;
        this.ageval=ageval;
        this.ival=ival;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }


    public String getAgeval() {
        return ageval;
    }

    public void setAgeval(String ageval) {
        this.ageval = ageval;
    }


    public String getIval() {
        return ival;
    }

    public void setIval(String ival) {
        this.ival = ival;
    }


}
