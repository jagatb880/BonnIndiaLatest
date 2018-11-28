package com.alysiancreative.bonnindia.ModelClass;

import java.util.ArrayList;

public class Complain {
    public ArrayList<TodayOrderStatus> getList() {
        return list;
    }

    public void setList(ArrayList<TodayOrderStatus> list) {
        this.list = list;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private ArrayList<TodayOrderStatus> list;
    private Status status;
}
