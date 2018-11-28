package com.alysiancreative.bonnindia.ModelClass;

public class TodayInvoiceList {

    private String id;
    private String customer_id;
    private String billno;
    private String billDate;
    private String paymentTerms;
    private String dueDate;
    private String referenceNo;
    private String referenceDate;
    private String pos;
    private String billType;
    private String totalamount;
    private String totaldiscount;
    private String totaltaxablevalue;
    private String totaltax;
    private String totalsubtotal;
    private String tradediscount;
    private String billamount;
    private String created;
    private String modified;

    public TodayInvoiceList(String bill_no, String total_amount, String total_discount, String total_tax, String bill_amount) {
        this.billno = bill_no;
        this.totalamount = total_amount;
        this.totaldiscount = total_discount;
        this.totaltax = total_tax;
        this.billamount = bill_amount;
    }
    public TodayInvoiceList(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(String referenceDate) {
        this.referenceDate = referenceDate;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTotaldiscount() {
        return totaldiscount;
    }

    public void setTotaldiscount(String totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public String getTotaltaxablevalue() {
        return totaltaxablevalue;
    }

    public void setTotaltaxablevalue(String totaltaxablevalue) {
        this.totaltaxablevalue = totaltaxablevalue;
    }

    public String getTotaltax() {
        return totaltax;
    }

    public void setTotaltax(String totaltax) {
        this.totaltax = totaltax;
    }

    public String getTotalsubtotal() {
        return totalsubtotal;
    }

    public void setTotalsubtotal(String totalsubtotal) {
        this.totalsubtotal = totalsubtotal;
    }

    public String getTradediscount() {
        return tradediscount;
    }

    public void setTradediscount(String tradediscount) {
        this.tradediscount = tradediscount;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
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
}
