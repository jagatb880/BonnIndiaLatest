package com.alysiancreative.bonnindia.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    AppCompatButton login;
    EditText username, password;
    SharedPreferences sharedPreferences;;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        token = sharedPreferences.getString("TOKEN", "");
        if(!token.isEmpty()){
            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            finish();
        }

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the username", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCredentials();
                }
            }
        });


    }

    public void verifyCredentials() {
        if (!checkNetwork()) {
            showDialog("No active internet connection");
            return;
        } else {
            getLoginCheck();
        }
    }

    public void getLoginCheck() {
        final android.app.AlertDialog loading = new SpotsDialog(this, R.style.Custom);
        loading.show();
        loading.setCancelable(false);
        //Creating a json array request

        //if everything is fine
        Log.d("website: ", ">>> " + WebAddress.getLoginUrl());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, WebAddress.getLoginUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response: ", ">>> " + response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);


                            //if no error in response
                            try {
                                if (obj.getString("status").equals("SUCCESS")) {
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    sharedPreferences.edit().putString("TOKEN", obj.getString("token")).apply();
                                    sharedPreferences.edit().putString("DATA", obj.getString("data")).commit();
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                    finish();
                                } else {
                                    loading.dismiss();
                                    Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_SHORT).show();
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
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };

        // Access the RequestQueue
        Log.d("stringRequest", "" + stringRequest);
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void showDialog(final String message) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                alertDialogBuilder
                        .setTitle(message);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();

                            }
                        })
                        .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    };


}
