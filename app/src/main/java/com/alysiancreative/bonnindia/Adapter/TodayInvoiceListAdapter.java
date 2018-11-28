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
            todaysInvoiceHeaderViewholder.txtBillNo.setText("#"+todayInvoiceList.getBillno());
            todaysInvoiceHeaderViewholder.txtTotalAmount.setText(""+todayInvoiceList.getTotalamount());
            todaysInvoiceHeaderViewholder.txtTotalDiscount.setText(""+todayInvoiceList.getTotaldiscount());
            todaysInvoiceHeaderViewholder.txtTotalTax.setText(""+todayInvoiceList.getTotaltax());
            todaysInvoiceHeaderViewholder.txtBillAmount.setText(""+todayInvoiceList.getBillamount());
        }
        if (viewHolder instanceof TodaysInvoicListNormalViewholder){
            TodaysInvoicListNormalViewholder todaysInvoicListNormalViewholder = (TodaysInvoicListNormalViewholder) viewHolder;
            TodayInvoiceList todayOrderStatus =invoiceLists.get(i);
            todaysInvoicListNormalViewholder.txtBillNo.setText("#"+todayOrderStatus.getBillno());
            todaysInvoicListNormalViewholder.txtTotalAmount.setText(""+todayOrderStatus.getTotalamount());
            todaysInvoicListNormalViewholder.txtTotalDiscount.setText(""+todayOrderStatus.getTotaldiscount());
            todaysInvoicListNormalViewholder.txtTotalTax.setText(""+todayOrderStatus.getTotaltax());
            todaysInvoicListNormalViewholder.txtBillAmount.setText(""+todayOrderStatus.getBillamount());
        }
    }

    @Override
    public int getItemCount() {
        return invoiceLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
//        TodayInvoiceList status = invoiceLists.get(position);
//        if (status.getS().equals("0")){
//            return TYPE_HEADER;
//        }else {
//            return TYPE_NORMAL;
//        }
    }

    public class TodaysInvoiceHeaderViewholder extends RecyclerView.ViewHolder{
        TextView txtBillNo, txtTotalAmount, txtTotalDiscount, txtTotalTax, txtBillAmount;
        public TodaysInvoiceHeaderViewholder(@NonNull View itemView) {
            super(itemView);
            txtBillNo = itemView.findViewById(R.id.txtBillNo);
            txtTotalAmount = itemView.findViewById(R.id.txtTotalAmount);
            txtTotalDiscount = itemView.findViewById(R.id.txtTotalDiscount);
            txtTotalTax = itemView.findViewById(R.id.txtTotalTax);
            txtBillAmount = itemView.findViewById(R.id.txtBillAmount);
        }
    }
    public class TodaysInvoicListNormalViewholder  extends RecyclerView.ViewHolder{
        TextView txtBillNo, txtTotalAmount, txtTotalDiscount, txtTotalTax, txtBillAmount;
        public TodaysInvoicListNormalViewholder(@NonNull View itemView) {
            super(itemView);
            txtBillNo = itemView.findViewById(R.id.txtBillNo);
            txtTotalAmount = itemView.findViewById(R.id.txtTotalAmount);
            txtTotalDiscount = itemView.findViewById(R.id.txtTotalDiscount);
            txtTotalTax = itemView.findViewById(R.id.txtTotalTax);
            txtBillAmount = itemView.findViewById(R.id.txtBillAmount);
        }
    }
}
