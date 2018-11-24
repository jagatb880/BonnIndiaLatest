package com.alysiancreative.bonnindia.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alysiancreative.bonnindia.Activities.DashboardActivity;
import com.alysiancreative.bonnindia.R;
import com.alysiancreative.bonnindia.WebService.VolleySingleton;
import com.alysiancreative.bonnindia.WebService.WebAddress;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class ChangePasswordFragment extends Fragment {

    View view;
    SharedPreferences sharedPreferences;
    String data, token, adminId, password;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        data = sharedPreferences.getString("DATA", "");
        token = sharedPreferences.getString("TOKEN", "");

        try {
            JSONObject jsonObj = new JSONObject(data);
            adminId = jsonObj.getString("id");
            Toast.makeText(getContext(),adminId,Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final TextView newPassword = view.findViewById(R.id.newPassword);
        final TextView confirmPassword = view.findViewById(R.id.confirmPassword);
        Button submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Please enter the new password",Toast.LENGTH_SHORT).show();
                }else if(confirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Please enter the confirm password",Toast.LENGTH_SHORT).show();
                }else if(!newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    Toast.makeText(getContext(),"Confirm Password is not matching with New Password",Toast.LENGTH_SHORT).show();
                }else{
                    password = newPassword.getText().toString();
                    checkNetworkConnection();
                }
            }
        });
        return view;
    }

    public void checkNetworkConnection(){
        if (!checkNetwork()) {
            Toast.makeText(getContext(),"No active internet connection",Toast.LENGTH_SHORT).show();
            return;
        } else {
            checkChangePasswordurl();
        }
    }

    public void checkChangePasswordurl(){
        final android.app.AlertDialog loading = new SpotsDialog(getContext(), R.style.Custom);
        loading.show();
        loading.setCancelable(false);
        //Creating a json array request

        //if everything is fine
        Log.d("website: ", ">>> " + WebAddress.getLoginUrl());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebAddress.getChangePasswordUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response: ", ">>> " + response);
                        loading.dismiss();
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            try {
                                if (obj.getString("status").equals("SUCCESS")) {
                                    Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("RVA", "error:" + error);

                        int errorCode = 0;
                        if (error instanceof TimeoutError) {
                            errorCode = -7;
                        } else if (error instanceof NoConnectionError) {
                            errorCode = -1;
                        } else if (error instanceof AuthFailureError) {
                            errorCode = -6;
                        } else if (error instanceof ServerError) {
                            errorCode = 0;
                        } else if (error instanceof NetworkError) {
                            errorCode = -1;
                        } else if (error instanceof ParseError) {
                            errorCode = -8;
                        }
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                params.put("id", adminId);
                params.put("password", password);
                return params;
            }
        };

        // Access the RequestQueue
        Log.d("stringRequest", "" + stringRequest);
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
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
