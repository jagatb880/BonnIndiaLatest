package com.alysiancreative.bonnindia.WebService;

public class WebAddress {

//    public static String bonnIndiaUrl = "http://bonnindia.com/bonnIndia_dev/API/";
    public static String bonnIndiaUrl = "http://bonnindia.com/API/";

    public static String getLoginUrl()
    {
        return bonnIndiaUrl+"users/login";
    }

    public static String getDashboardDataUrl()
    {
        return bonnIndiaUrl+"users/dashboardData";
    }

    public static String getListCustomerUrl()
    {
        return bonnIndiaUrl+"customer/listCustomer";
    }

    public static String getListCustomerServiceUrl()
    {
        return bonnIndiaUrl+"complain/listComplain";
    }

    public static String getInvoiceUrl()
    {
        return bonnIndiaUrl+"bill/listBill";
    }

    public static String getChangePasswordUrl()
    {
        return bonnIndiaUrl+"users/changePassword";
    }
}
