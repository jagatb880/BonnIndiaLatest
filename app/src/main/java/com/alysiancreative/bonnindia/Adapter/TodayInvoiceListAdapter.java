package com.alysiancreative.bonnindia.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.ModelClass.TodayInvoiceList;
import com.alysiancreative.bonnindia.R;

import java.util.ArrayList;

public class TodayInvoiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<TodayInvoiceList> invoiceLists;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    public TodayInvoiceListAdapter(Context context, ArrayList<TodayInvoiceList> orderStatusArrayList) {
        this.context = context;
        this.invoiceLists = orderStatusArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= null;
        if (i==TYPE_HEADER){
            view = LayoutInflater.from(context).inflate(R.layout.today_invoice_list_header_view,viewGroup,false);
            return new TodaysInvoiceHeaderViewholder(view);
        }
        if (i==TYPE_NORMAL){
            view = LayoutInflater.from(context).inflate(R.layout.today_invoice_list,viewGroup,false);
            return new TodaysInvoicListNormalViewholder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TodaysInvoiceHeaderViewholder){
            TodaysInvoiceHeaderViewholder todaysInvoiceHeaderViewholder = (TodaysInvoiceHeaderViewholder) viewHolder;
            TodayInvoiceList todayInvoiceList =invoiceLists.get(i);
            todaysInvoiceHeaderViewholder.txtItemName.setText(todayInvoiceList.getInvoice());
            todaysInvoiceHeaderViewholder.txtCustomerName.setText(todayInvoiceList.getDescription());
            todaysInvoiceHeaderViewholder.txtDeliveryDate.setText(todayInvoiceList.getAmount());
            todaysInvoiceHeaderViewholder.txtAssignTo.setText(todayInvoiceList.getStatus());
            todaysInvoiceHeaderViewholder.txtPayment.setText(todayInvoiceList.getIssue());
        }
        if (viewHolder instanceof TodaysInvoicListNormalViewholder){
            TodaysInvoicListNormalViewholder todaysInvoicListNormalViewholder = (TodaysInvoicListNormalViewholder) viewHolder;
            TodayInvoiceList todayOrderStatus =invoiceLists.get(i);
            todaysInvoicListNormalViewholder.txtItemName.setText(todayOrderStatus.getInvoice());
            todaysInvoicListNormalViewholder.txtCustomerName.setText(todayOrderStatus.getDescription());
            todaysInvoicListNormalViewholder.txtDeliveryDate.setText(todayOrderStatus.getAmount());
            todaysInvoicListNormalViewholder.txtAssignTo.setText(todayOrderStatus.getStatus());
            todaysInvoicListNormalViewholder.txtPayment.setText(todayOrderStatus.getIssue());
            if (todayOrderStatus.getStatus().equals("0")){
                todaysInvoicListNormalViewholder.txtAssignTo.setText("Paid");
                todaysInvoicListNormalViewholder.txtAssignTo.setTextColor(context.getResources().getColor(R.color.greenColor));
            }else {
                todaysInvoicListNormalViewholder.txtAssignTo.setText("Un-Paid");
                todaysInvoicListNormalViewholder.txtAssignTo.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        }
    }

    @Override
    public int getItemCount() {
        return invoiceLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        TodayInvoiceList status = invoiceLists.get(position);
        if (status.getType().equals("0")){
            return TYPE_HEADER;
        }else {
            return TYPE_NORMAL;
        }
    }

    public class TodaysInvoiceHeaderViewholder extends RecyclerView.ViewHolder{
        TextView txtItemName,txtCustomerName,txtDeliveryDate,txtAssignTo,txtPayment,txtView;
        public TodaysInvoiceHeaderViewholder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtDeliveryDate = itemView.findViewById(R.id.txtDeliveryDate);
            txtAssignTo = itemView.findViewById(R.id.txtAssignTo);
            txtPayment = itemView.findViewById(R.id.txtPayment);
            txtView = itemView.findViewById(R.id.txtView);
        }
    }
    public class TodaysInvoicListNormalViewholder  extends RecyclerView.ViewHolder{
        TextView txtItemName,txtCustomerName,txtDeliveryDate,txtAssignTo,txtPayment;
        public TodaysInvoicListNormalViewholder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtDeliveryDate = itemView.findViewById(R.id.txtDeliveryDate);
            txtAssignTo = itemView.findViewById(R.id.txtAssignTo);
            txtPayment = itemView.findViewById(R.id.txtPayment);
        }
    }
}
