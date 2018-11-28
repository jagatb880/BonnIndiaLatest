package com.alysiancreative.bonnindia.ModelClass;

public class TodayOrderStatus {

    private String id;
    private String issue;
    private String complainid;
    private String  customer_id;
    private String product_id;
    private String user_id;
    private String priority;
    private String feedback;
    private String agent_feedback;
    private String payment;
    private String service_type;
    private String status;
    private String flag;
    private String attend_date;
    private String complain_date;
    private String payment_received;
    private String state;
    private String country;
    private String ced;
    private String complain_type;
    private String assigned_to;
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getComplainid() {
        return complainid;
    }

    public void setComplainid(String complainid) {
        this.complainid = complainid;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAgent_feedback() {
        return agent_feedback;
    }

    public void setAgent_feedback(String agent_feedback) {
        this.agent_feedback = agent_feedback;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAttend_date() {
        return attend_date;
    }

    public void setAttend_date(String attend_date) {
        this.attend_date = attend_date;
    }

    public String getComplain_date() {
        return complain_date;
    }

    public void setComplain_date(String complain_date) {
        this.complain_date = complain_date;
    }

    public String getPayment_received() {
        return payment_received;
    }

    public void setPayment_received(String payment_received) {
        this.payment_received = payment_received;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getComplain_type() {
        return complain_type;
    }

    public void setComplain_type(String complain_type) {
        this.complain_type = complain_type;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    private String modified;
    public TodayOrderStatus(String job_card, String assign_to, String payment, String status) {
        this.complainid = job_card;
        this.assigned_to = assign_to;
        this.payment = payment;
        this.status = status;
    }




}
