package com.alysiancreative.bonnindia.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alysiancreative.bonnindia.Adapter.CustomerAdapter;
import com.alysiancreative.bonnindia.Adapter.InvoiceAdapter;
import com.alysiancreative.bonnindia.ModelClass.CustomerList;
import com.alysiancreative.bonnindia.ModelClass.InvoiceList;
import com.alysiancreative.bonnindia.R;
import com.alysiancreative.bonnindia.WebService.VolleySingleton;
import com.alysiancreative.bonnindia.WebService.WebAddress;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    private SwipeRefreshLayout swipeRefreshLayout;

    //Creating a List of invoiceLists
    private List<InvoiceList> invoiceLists;

    //Creating Views
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    SharedPreferences sharedPreferences;;
    private String token;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        view = inflater.inflate(R.layout.fragment_customer, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        token = sharedPreferences.getString("TOKEN", "");

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        //Initializing Views
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our invoiceLists list
        invoiceLists = new ArrayList<>();

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getData();
                                    }
                                }
        );

        return view;
    }

    public void getData(){

        if (!checkNetwork()) {
            Toast.makeText(getContext(),"No active internet connection",Toast.LENGTH_SHORT).show();
            return;
        } else {
            getListCustomer();
        }

//        //Finally initializing our adapter
//        adapter = new CustomerAdapter(getActivity());
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);
//        swipeRefreshLayout.setRefreshing(false);
    }

    public void getListCustomer(){
//        final android.app.AlertDialog loading = new SpotsDialog(getContext(), R.style.Custom);
//        loading.show();
//        loading.setCancelable(false);
        //Creating a json array request

        //if everything is fine
        Log.d("website: ", ">>> " + WebAddress.getInvoiceUrl());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebAddress.getInvoiceUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        loading.dismiss();
                        Log.d("response: ", ">>> " + response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);


                            //if no error in response
                            try {
                                if (obj.getString("status").equals("SUCCESS")) {
                                    setDataToUi(obj.getJSONArray("data"));
                                } else {
                                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError.networkResponse == null) {
                            if (volleyError.getClass().equals(TimeoutError.class)) {
                                // stopping swipe refresh
                                swipeRefreshLayout.setRefreshing(false);
                                // Show timeout error message
                                Toast.makeText(getContext(),
                                        "Oops. Timeout error!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                return params;
            }
        };

        // Access the RequestQueue
        Log.d("stringRequest", "" + stringRequest);
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    public void setDataToUi(JSONArray jsonArray){
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d("ArraySize", "" + jsonArray.length());
            InvoiceList invoiceList = new InvoiceList();
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                invoiceList.setSerialNo(i+1);
                invoiceList.setId(json.getInt("id"));
                invoiceList.setCustomerId(json.getInt("customer_id"));
                invoiceList.setBillNo(json.getString("billno"));
                invoiceList.setBillDate(json.getString("billDate").substring(0,10));
                invoiceList.setPaymentTerms(json.getString("paymentTerms"));
                invoiceList.setDueDate(json.getString("dueDate").substring(0,10));
                invoiceList.setReferenceNo(json.getString("referenceNo"));
                invoiceList.setReferenceDate(json.getString("referenceDate"));
                invoiceList.setPos(json.getString("pos"));
                invoiceList.setBillType(json.getString("billType"));
                invoiceList.setTotalAmount(json.getString("totalamount"));
                invoiceList.setTotalDiscount(json.getString("totaldiscount"));
                invoiceList.setTotalTaxableValue(json.getString("totaltaxablevalue"));
                invoiceList.setTotalTax(json.getString("totaltax"));
                invoiceList.setTotalSubtotal(json.getString("totalsubtotal"));
                invoiceList.setTradeDiscount(json.getString("tradediscount"));
                invoiceList.setBillAmount(json.getString("billamount"));
                invoiceList.setCreated(json.getString("created").substring(0,10));
                invoiceList.setModified(json.getString("modified").substring(0,10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            invoiceLists.add(invoiceList);
        }

        //Finally initializing our adapter
        adapter = new InvoiceAdapter(invoiceLists, getActivity());
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

        // stopping swipe refresh
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        getData();
    }

    private boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                wifiAvailable = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                mobileAvailable = true;
            }
        }
        return wifiAvailable || mobileAvailable;
    }
}
