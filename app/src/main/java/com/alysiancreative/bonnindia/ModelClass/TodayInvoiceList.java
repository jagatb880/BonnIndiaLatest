package com.alysiancreative.bonnindia.ModelClass;

public class TodayInvoiceList {
    private String invoice;
    private String description;
    private String amount;
    private String status;
    private String viewValue;

    public String getViewValue() {
        return viewValue;
    }

    public void setViewValue(String viewValue) {
        this.viewValue = viewValue;
    }

    public TodayInvoiceList(String invoice, String description, String amount, String status, String issue, String viewValue,String type) {
        this.invoice = invoice;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.issue = issue;
        this.type = type;
        this.viewValue = viewValue;
    }

    private String issue;
    private String type;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
