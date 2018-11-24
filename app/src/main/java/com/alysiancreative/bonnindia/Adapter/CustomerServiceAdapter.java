package com.alysiancreative.bonnindia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.Activities.CustomerDetailsActivity;
import com.alysiancreative.bonnindia.Activities.CustomerServiceDetailsActivity;
import com.alysiancreative.bonnindia.ModelClass.CustomerServiceList;
import com.alysiancreative.bonnindia.R;

import java.util.List;

public class CustomerServiceAdapter extends RecyclerView.Adapter<CustomerServiceAdapter.ViewHolder> {

    private Context context;
    List<CustomerServiceList> customerServiceLists;

    public CustomerServiceAdapter(List<CustomerServiceList> customerServiceLists, Context context) {
        super();

        //Getting all the customerServiceLists
        this.customerServiceLists = customerServiceLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_service_custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CustomerServiceList customerServiceList = customerServiceLists.get(position);

        if(customerServiceList.getCustomerName() != null){
            holder.serialNo.setText((String.valueOf(customerServiceList.getSerialNo())));

            holder.customerName.setText(customerServiceList.getCustomerName());
            holder.assignedTo.setText(customerServiceList.getAssignTo());
            holder.payment.setText(String.valueOf(customerServiceList.getPayment()));
            holder.status.setText(customerServiceList.getStatus());
            switch (holder.status.getText().toString()) {
                case "New":
                    holder.status.setBackgroundColor(context.getResources().getColor(R.color.red));
                    break;
                case "In progress":
                    holder.status.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                    break;
                case "Done":
                    holder.status.setBackgroundColor(context.getResources().getColor(R.color.green));
                    break;
                case "closed":
                    holder.status.setBackgroundColor(context.getResources().getColor(R.color.darkgrey));
                    break;
            }
            holder.complainId = customerServiceList.getComplainId();
            switch (customerServiceList.getPriority()){
                case "1":
                    holder.priority = "High";
                    break;
                case "2":
                    holder.priority = "Medium";
                    break;
                case "3":
                    holder.priority = "Low";
                    break;
            }
            switch (customerServiceList.getServiceType()){
                case "1":
                    holder.serviceType = "Under Warrenty";
                    break;
                case "2":
                    holder.serviceType = "Out Of Warrenty";
                    break;
            }
            holder.issue = customerServiceList.getIssue();
            holder.complainType = customerServiceList.getComplainType();
            holder.customerId = customerServiceList.getCustomerId();
            holder.customerEmail = customerServiceList.getCustomerEmail();
            holder.customerPhone = customerServiceList.getCustomerPhone();
            holder.customerAddress = customerServiceList.getCustomerAddress();
            holder.createdOn = customerServiceList.getCreatedOn();
            holder.modifiedOn = customerServiceList.getModifiedOn();
        }
    }

    @Override
    public int getItemCount() {
        return customerServiceLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView serialNo;
        public TextView customerName;
        public TextView assignedTo;
        public TextView payment;
        public TextView status;

        public String complainId;
        public String priority;
        public String serviceType;
        public String issue;
        public String complainType;
        public int customerId;
        public String customerEmail;
        public String customerPhone;
        public String customerAddress;
        public String createdOn;
        public String modifiedOn;
        public TextView viewDetails;

        public ViewHolder(View itemView) {
            super(itemView);

            serialNo = itemView.findViewById(R.id.serialNo);
            customerName = itemView.findViewById(R.id.customerName);
            assignedTo = itemView.findViewById(R.id.assignTo);
            payment = itemView.findViewById(R.id.payment);
            status = itemView.findViewById(R.id.status);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            viewDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.viewDetails:
                    Intent intent = new Intent(view.getContext(),CustomerServiceDetailsActivity.class);
                    intent.putExtra("JOBCARDNUMBER",complainId);
                    intent.putExtra("ASSIGNEDTO",assignedTo.getText().toString());
                    intent.putExtra("ESTIMATEDSERVICEPAYMENT",payment.getText().toString());
                    intent.putExtra("PRIORITYPERIOD",priority);
                    intent.putExtra("SERVICETYPE",serviceType);
                    intent.putExtra("ISSUE",issue);
                    intent.putExtra("COMPLAINTYPE",complainType);
                    intent.putExtra("SERVICESTATUS",status.getText().toString());
                    intent.putExtra("CUSTOMERID",customerId);
                    intent.putExtra("CUSTOMERNAME",customerName.getText().toString());
                    intent.putExtra("CUSTOMEREMAIL",customerEmail);
                    intent.putExtra("CUSTOMERMOBILE",customerPhone);
                    intent.putExtra("CUSTOMERPHONE",customerAddress);
                    intent.putExtra("CREATEDON",createdOn);
                    intent.putExtra("MODIFIEDON",modifiedOn);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
