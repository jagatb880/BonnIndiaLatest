package com.alysiancreative.bonnindia.ModelClass;

public class TodayInvoiceList {

    private String billNo;
    private String totalAmount;
    private String totalDiscount;
    private String totalTax;

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    private String billAmount;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public TodayInvoiceList(String billNo, String totalAmount, String totalDiscount, String totalTax, String billAmount) {
        this.billNo = billNo;
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
        this.totalTax = totalTax;
        this.billAmount = billAmount;
    }

}
