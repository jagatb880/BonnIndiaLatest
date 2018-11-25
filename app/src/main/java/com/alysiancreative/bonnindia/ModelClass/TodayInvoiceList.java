package com.alysiancreative.bonnindia.ModelClass;

public class TodayInvoiceList {
    private String invoice;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String amount;
    private String discount;
    private String tax;
    private String type;

    public TodayInvoiceList(String invoice, String amount, String discount, String tax, String type) {
        this.invoice = invoice;
        this.amount = amount;
        this.discount = discount;
        this.tax = tax;
        this.type = type;
    }

}
