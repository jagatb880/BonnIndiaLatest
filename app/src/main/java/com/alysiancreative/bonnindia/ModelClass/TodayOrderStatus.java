package com.alysiancreative.bonnindia.ModelClass;

public class TodayOrderStatus {

    private String jobCard;
    private String assignTo;
    private String payment;
    private String status;

    public String getJobCard() {
        return jobCard;
    }

    public void setJobCard(String jobCard) {
        this.jobCard = jobCard;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public TodayOrderStatus(String jobCard, String assignTo, String payment, String status) {
        this.jobCard = jobCard;
        this.assignTo = assignTo;
        this.payment = payment;
        this.status = status;
    }

}
