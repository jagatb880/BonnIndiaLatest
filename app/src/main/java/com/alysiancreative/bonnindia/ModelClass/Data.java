package com.alysiancreative.bonnindia.ModelClass;

import java.util.ArrayList;

public class Data {
    private ArrayList<TodayInvoiceList> billing;
    private Complain complain;

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public ArrayList<TodayInvoiceList> getBilling() {
        return billing;
    }

    public void setBilling(ArrayList<TodayInvoiceList> billing) {
        this.billing = billing;
    }
}
