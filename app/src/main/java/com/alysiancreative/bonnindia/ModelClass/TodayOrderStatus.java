package com.alysiancreative.bonnindia.ModelClass;

public class TodayOrderStatus {
    private String itemName;
    private String customerName;
    private String deliveryDate;

    public TodayOrderStatus(String itemName, String customerName, String deliveryDate, String assignTo, String payment, String type) {
        this.itemName = itemName;
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.assignTo = assignTo;
        this.payment = payment;
        this.type = type;
    }

    private String assignTo;
    private String payment;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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
}
