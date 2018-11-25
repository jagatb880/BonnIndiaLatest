package com.alysiancreative.bonnindia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.Activities.CustomerDetailsActivity;
import com.alysiancreative.bonnindia.Activities.InvoiceDetailsActivity;
import com.alysiancreative.bonnindia.ModelClass.CustomerList;
import com.alysiancreative.bonnindia.ModelClass.InvoiceList;
import com.alysiancreative.bonnindia.R;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private Context context;
    List<InvoiceList> invoiceLists;
    public InvoiceAdapter(List<InvoiceList> invoiceLists, Context context) {
        super();

        //Getting all the invoicelists
        this.invoiceLists = invoiceLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        InvoiceList invoiceList = invoiceLists.get(position);

        holder.serialNo.setText((String.valueOf(invoiceList.getSerialNo())));
        holder.billNo.setText("Bill No #"+invoiceList.getBillNo());
        holder.billAmount.setText("Bill Amount #"+(String.valueOf(invoiceList.getBillAmount())));
        holder.totalTax.setText("Total Tax #"+(String.valueOf(invoiceList.getTotalTax())));
        holder.billDate.setText(invoiceList.getBillDate());
        holder.id = String.valueOf(invoiceList.getId());
        holder.customerId = String.valueOf(invoiceList.getCustomerId());
        holder.paymentTerms = invoiceList.getPaymentTerms();
        holder.dueDate = invoiceList.getDueDate();
        holder.referenceNo = invoiceList.getReferenceNo();
        holder.referenceDate = invoiceList.getReferenceDate();
        holder.pos = invoiceList.getPos();
        holder.billType = invoiceList.getBillType();
        holder.totalAmount = invoiceList.getTotalAmount();
        holder.totalDiscount = invoiceList.getTotalDiscount();
        holder.totaltaxableValue = invoiceList.getTotalTaxableValue();
        holder.totalSubtotal = invoiceList.getTotalSubtotal();
        holder.tradeDiscount = invoiceList.getTradeDiscount();
        holder.created = invoiceList.getCreated();
        holder.modified = invoiceList.getModified();
    }

    @Override
    public int getItemCount() {
        return invoiceLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView serialNo;
        public TextView billNo;
        public TextView billAmount;
        public TextView totalTax;
        public TextView billDate;
        public TextView viewDetails;
        public String id;
        public String customerId;
        public String paymentTerms;
        public String dueDate;
        public String referenceNo;
        public String referenceDate;
        public String pos;
        public String billType;
        public String totalAmount;
        public String totalDiscount;
        public String totaltaxableValue;
        public String totalSubtotal;
        public String tradeDiscount;
        public String created;
        public String modified;

        public ViewHolder(View itemView) {
            super(itemView);

            serialNo = itemView.findViewById(R.id.serialNo);
            billNo = itemView.findViewById(R.id.billNo);
            billAmount = itemView.findViewById(R.id.billAmount);
            totalTax = itemView.findViewById(R.id.totalTax);
            billDate = itemView.findViewById(R.id.billDate);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            viewDetails.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.viewDetails:
                    Intent intent = new Intent(view.getContext(),InvoiceDetailsActivity.class);
                    intent.putExtra("ID",id);
                    intent.putExtra("CUSTOMERID",customerId);
                    intent.putExtra("BILLNO",billNo.getText().toString());
                    intent.putExtra("BILLDATE",billDate.getText().toString());
                    intent.putExtra("PAYMENTTERMS",paymentTerms);
                    intent.putExtra("DUEDATE",dueDate);
                    intent.putExtra("REFERENCENO",referenceNo);
                    intent.putExtra("REFERENCEDATE",referenceDate);
                    intent.putExtra("POS",pos);
                    intent.putExtra("BILLTYPE",billType);
                    intent.putExtra("TOTALAMOUNT",totalAmount);
                    intent.putExtra("TOTALDISCOUNT",totalDiscount);
                    intent.putExtra("TOTALTAXABLEVALUE",totaltaxableValue);
                    intent.putExtra("TOTALTAX",totalTax.getText().toString());
                    intent.putExtra("TOTALSUBTOTAL",totalSubtotal);
                    intent.putExtra("TRADEDISCOUNT",tradeDiscount);
                    intent.putExtra("BILLAMOUNT",billAmount.getText().toString());
                    intent.putExtra("CREATEDON",created);
                    intent.putExtra("MODIFIEDON",modified);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
