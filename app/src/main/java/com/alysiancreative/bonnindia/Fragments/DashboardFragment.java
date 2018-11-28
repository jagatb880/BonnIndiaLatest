package com.alysiancreative.bonnindia.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.Adapter.TodayInvoiceListAdapter;
import com.alysiancreative.bonnindia.Adapter.TodayOrderAdapter;
import com.alysiancreative.bonnindia.ModelClass.Data;
import com.alysiancreative.bonnindia.ModelClass.TodayInvoiceList;
import com.alysiancreative.bonnindia.ModelClass.TodayOrderStatus;
import com.alysiancreative.bonnindia.R;
import com.alysiancreative.bonnindia.Utilities.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<TodayOrderStatus> todayOrderStatusArrayList;
    private TodayOrderAdapter todayOrderAdapter;
    private RecyclerView rcvTodayOrderStatus;

    private ArrayList<TodayInvoiceList> invoiceLists;
    private TodayInvoiceListAdapter todayInvoiceListAdapter;
    private RecyclerView rcvTodayINvoiceList;
    private TextView txtComplainReceived,txtCompleted,txtPending;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        txtComplainReceived = view.findViewById(R.id.txtComplainReceived);
        txtCompleted = view.findViewById(R.id.txtCompleted);
        txtPending = view.findViewById(R.id.txtPending);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        //                                        getData();
                                        // stopping swipe refresh
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
        );
        rcvTodayOrderStatus = view.findViewById(R.id.rcvTodayOrderStatus);
        rcvTodayOrderStatus.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvTodayOrderStatus.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        todayOrderStatusArrayList = new ArrayList<>();


        rcvTodayINvoiceList = view.findViewById(R.id.rcvTodayINvoiceList);
        rcvTodayINvoiceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvTodayINvoiceList.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        invoiceLists = new ArrayList<>();
        todayInvoiceListAdapter=new TodayInvoiceListAdapter(getActivity(),invoiceLists);
        rcvTodayINvoiceList.setAdapter(todayInvoiceListAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Utils.checkConnection(getActivity())){
            new GetAllDataTask(DashboardFragment.this).execute("http://bonnindia.com/bonnIndia_dev/API/users/dashboardData");
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
    private class GetAllDataTask extends AsyncTask<String,Void,String>{
        private ProgressDialog progressDialog;
        private WeakReference<DashboardFragment> weakReference;
        public GetAllDataTask(DashboardFragment dashboardFragment){
            weakReference = new WeakReference<>(dashboardFragment);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            String result = "";
            try{
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(30 * 1000);
                httpURLConnection.setReadTimeout(30 * 1000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    result = readStream(httpURLConnection.getInputStream());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (weakReference.get()!=null){
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                if (!TextUtils.isEmpty(result)){
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String status = jsonObject.getString("status");

                        if ("SUCCESS".equals(status)){
                            String dataValue = jsonObject.getString("data");
                            Gson gson = new Gson();
                            Type type = new TypeToken<Data>() {}.getType();
                            Data data = gson.fromJson(dataValue,type);
                            if (data!=null){
                                txtComplainReceived.setText(""+data.getComplain().getStatus().getNew());
                                txtCompleted.setText(""+data.getComplain().getStatus().getClosed());
                                txtPending.setText(""+data.getComplain().getStatus().getInProgress());
                                todayOrderStatusArrayList.add(new TodayOrderStatus("Job Card","Assign To","Payment","Status"));
                                todayOrderStatusArrayList.addAll(data.getComplain().getList());
                                todayOrderAdapter = new TodayOrderAdapter(getActivity(),todayOrderStatusArrayList);
                                rcvTodayOrderStatus.setAdapter(todayOrderAdapter);
                                invoiceLists.add(new TodayInvoiceList("Bill No","Total Amount","Total Discount","Total Tax","Bill Amount"));
                                invoiceLists.addAll(data.getBilling());
                                todayInvoiceListAdapter=new TodayInvoiceListAdapter(getActivity(),invoiceLists);
                                rcvTodayINvoiceList.setAdapter(todayInvoiceListAdapter);
                            }
                            Log.v("sjbhsd",""+data);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
