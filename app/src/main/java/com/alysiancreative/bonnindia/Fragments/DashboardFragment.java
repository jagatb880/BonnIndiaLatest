package com.alysiancreative.bonnindia.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alysiancreative.bonnindia.Adapter.TodayInvoiceListAdapter;
import com.alysiancreative.bonnindia.Adapter.TodayOrderAdapter;
import com.alysiancreative.bonnindia.ModelClass.TodayInvoiceList;
import com.alysiancreative.bonnindia.ModelClass.TodayOrderStatus;
import com.alysiancreative.bonnindia.R;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

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
        todayOrderStatusArrayList.add(new TodayOrderStatus("Item","Customer","Delivery Date","Assign To","Payment","0"));
        todayOrderStatusArrayList.add(new TodayOrderStatus("Fan","Santosh","10-05-18","#0123","Rs 250.00","1"));
        todayOrderStatusArrayList.add(new TodayOrderStatus("Fan","Santosh","10-05-18","#0123","Rs 250.00","1"));
        todayOrderStatusArrayList.add(new TodayOrderStatus("Fan","Santosh","10-05-18","#0123","Rs 250.00","1"));
        /*todayOrderStatusArrayList.add(new TodayOrderStatus("Fan","Santosh","10-05-18","#0123","Rs 250.00","1"));
        todayOrderStatusArrayList.add(new TodayOrderStatus("Fan","Santosh","10-05-18","#0123","Rs 250.00","1"));*/
        todayOrderAdapter = new TodayOrderAdapter(getActivity(),todayOrderStatusArrayList);
        rcvTodayOrderStatus.setAdapter(todayOrderAdapter);

        rcvTodayINvoiceList = view.findViewById(R.id.rcvTodayINvoiceList);
        rcvTodayINvoiceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvTodayINvoiceList.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        invoiceLists = new ArrayList<>();
        invoiceLists.add(new TodayInvoiceList("Invoice","Description","Amount","Status","Issue","View","0"));
        invoiceLists.add(new TodayInvoiceList("#5010","Lorem Ipsum","Rs 548","0","15-Jan","View","1"));
        invoiceLists.add(new TodayInvoiceList("#5010","Lorem Ipsum","Rs 548","1","15-Jan","View","1"));
        invoiceLists.add(new TodayInvoiceList("#5010","Lorem Ipsum","Rs 548","0","15-Jan","View","1"));
        todayInvoiceListAdapter=new TodayInvoiceListAdapter(getActivity(),invoiceLists);
        rcvTodayINvoiceList.setAdapter(todayInvoiceListAdapter);
        return view;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
