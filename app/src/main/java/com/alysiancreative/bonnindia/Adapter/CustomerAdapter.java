package com.alysiancreative.bonnindia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.Activities.CustomerDetailsActivity;
import com.alysiancreative.bonnindia.ModelClass.CustomerList;
import com.alysiancreative.bonnindia.R;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private Context context;
    List<CustomerList> customerLists;
    public CustomerAdapter(List<CustomerList> customerLists,Context context) {
        super();

        //Getting all the customerlists
        this.customerLists = customerLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CustomerList customerList = customerLists.get(position);

        holder.serialNo.setText((String.valueOf(customerList.getSerialNo())));
        holder.customerId = customerList.getCustomerId();
        holder.customerName.setText(customerList.getCustomerName());
        holder.mobileNumber.setText(customerList.getMobileNumber());
        holder.complainDate.setText(customerList.getDate());
        holder.address.setText(customerList.getAddress());
        holder.email = customerList.getEmail();
    }

    @Override
    public int getItemCount() {
        return customerLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView serialNo;
        public TextView customerName;
        public TextView mobileNumber;
        public TextView complainDate;
        public TextView address;
        public TextView viewDetails;
        public String customerId;
        public String email;

        public ViewHolder(View itemView) {
            super(itemView);

            serialNo = itemView.findViewById(R.id.serialNo);
            customerName = itemView.findViewById(R.id.customerName);
            mobileNumber = itemView.findViewById(R.id.mobileNumber);
            complainDate = itemView.findViewById(R.id.complainDate);
            address = itemView.findViewById(R.id.address);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            viewDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.viewDetails:
                    Intent intent = new Intent(view.getContext(),CustomerDetailsActivity.class);
                    intent.putExtra("CustomerId",customerId);
                    intent.putExtra("CustomerName",customerName.getText().toString());
                    intent.putExtra("Mobile",mobileNumber.getText().toString());
                    intent.putExtra("Email",email);
                    intent.putExtra("Address",address.getText().toString());
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
