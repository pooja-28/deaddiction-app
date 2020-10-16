package com.example.myapplication;



public class infoadapter {
    String userid;
    int cigday;
    int startsmoke,stopsmoke;
    int cost,packno;
  String date;
    public  infoadapter(){}

    public infoadapter(String userid, int cigday, int startsmoke, int stopsmoke, int cost, int packno,String date) {
        this.userid = userid;
        this.cigday = cigday;
        this.startsmoke = startsmoke;
        this.stopsmoke = stopsmoke;
        this.cost = cost;
        this.packno = packno;
        this.date=date;
    }

    public int getCigday() {
        return cigday;
    }

    public void setCigday(int cigday) {
        this.cigday = cigday;
    }

    public int getStartsmoke() {
        return startsmoke;
    }

    public void setStartsmoke(int startsmoke) {
        this.startsmoke = startsmoke;
    }

    public int getStopsmoke() {
        return stopsmoke;
    }

    public void setStopsmoke(int stopsmoke) {
        this.stopsmoke = stopsmoke;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPackno() {
        return packno;
    }

    public void setPackno(int packno) {
        this.packno = packno;
    }
}
