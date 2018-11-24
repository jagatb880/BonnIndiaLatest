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

import com.alysiancreative.bonnindia.Adapter.CustomerServiceAdapter;
import com.alysiancreative.bonnindia.ModelClass.CustomerServiceList;
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

public class CustomerServiceFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    private SwipeRefreshLayout swipeRefreshLayout;

    //Creating a List of CustomerServiceList
    private List<CustomerServiceList> customerServiceLists;

    //Creating Views
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    JSONObject logInJsonObj;
    String getListCustomerServiceUrl;
    SharedPreferences sharedPreferences;;
    private String token;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        view = inflater.inflate(R.layout.fragment_customer_service, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        token = sharedPreferences.getString("TOKEN", "");

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        //Initializing Views
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our companyJobLists list
        customerServiceLists = new ArrayList<>();

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
            getListCustomerService();
        }

//        //Finally initializing our adapter
//        adapter = new CustomerAdapter(getActivity());
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);
//        swipeRefreshLayout.setRefreshing(false);
    }

    public void getListCustomerService(){
//        final android.app.AlertDialog loading = new SpotsDialog(getContext(), R.style.Custom);
//        loading.show();
//        loading.setCancelable(false);
        //Creating a json array request

        //if everything is fine
        Log.d("website: ", ">>> " + WebAddress.getLoginUrl());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebAddress.getListCustomerServiceUrl(),
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
            CustomerServiceList customerServiceList = new CustomerServiceList();
            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                customerServiceList.setSerialNo(i+1);
                customerServiceList.setComplainId(json.getString("complainid"));
                customerServiceList.setAssignTo(json.getString("assigned_to"));
                customerServiceList.setPayment(json.getInt("payment"));
                customerServiceList.setPriority(json.getString("priority"));
                customerServiceList.setServiceType(json.getString("service_type"));
                customerServiceList.setIssue(json.getString("issue"));
                customerServiceList.setComplainType(json.getString("complain_type"));
                customerServiceList.setStatus(json.getString("status"));
                customerServiceList.setCustomerId(json.getJSONObject("customer").getInt("id"));
                customerServiceList.setCustomerName(json.getJSONObject("customer").getString("name"));
                customerServiceList.setCustomerEmail(json.getJSONObject("customer").getString("email"));
                customerServiceList.setCustomerPhone(json.getJSONObject("customer").getString("phone"));
                customerServiceList.setCustomerAddress(json.getJSONObject("customer").getString("address"));
                customerServiceList.setCreatedOn(json.getString("complain_date").equals("null")?"N/A":json.getString("complain_date").substring(0,10));
                customerServiceList.setModifiedOn(json.getString("ced").equals("null")?"N/A":json.getString("ced").substring(0,10));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            customerServiceLists.add(customerServiceList);
        }

        //Finally initializing our adapter
        adapter = new CustomerServiceAdapter(customerServiceLists, getActivity());
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
