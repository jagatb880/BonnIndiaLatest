package com.alysiancreative.bonnindia.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alysiancreative.bonnindia.R;
import com.alysiancreative.bonnindia.ModelClass.TodayOrderStatus;

import java.util.ArrayList;

public class TodayOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<TodayOrderStatus> orderStatusArrayList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    public TodayOrderAdapter(Context context, ArrayList<TodayOrderStatus> orderStatusArrayList) {
        this.context = context;
        this.orderStatusArrayList = orderStatusArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= null;
        if (i==TYPE_HEADER){
            view = LayoutInflater.from(context).inflate(R.layout.today_order_header_view,viewGroup,false);
            return new TodaysOrdrHeaderViewholder(view);
        }
        if (i==TYPE_NORMAL){
            view = LayoutInflater.from(context).inflate(R.layout.today_orders_view,viewGroup,false);
            return new TodaysOrdrHeaderViewholder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
         if (viewHolder instanceof TodaysOrdrHeaderViewholder){
             TodaysOrdrHeaderViewholder headerViewholder = (TodaysOrdrHeaderViewholder) viewHolder;
             TodayOrderStatus todayOrderStatus =orderStatusArrayList.get(i);
             headerViewholder.txtItemName.setText(todayOrderStatus.getItemName());
             headerViewholder.txtCustomerName.setText(todayOrderStatus.getCustomerName());
             headerViewholder.txtDeliveryDate.setText(todayOrderStatus.getDeliveryDate());
             headerViewholder.txtAssignTo.setText(todayOrderStatus.getAssignTo());
             headerViewholder.txtPayment.setText(todayOrderStatus.getPayment());
         }
        if (viewHolder instanceof TodaysOderNormalViewholder){
            TodaysOderNormalViewholder todaysOderNormalViewholder = (TodaysOderNormalViewholder) viewHolder;
            TodayOrderStatus todayOrderStatus =orderStatusArrayList.get(i);
            todaysOderNormalViewholder.txtItemName.setText(todayOrderStatus.getItemName());
            todaysOderNormalViewholder.txtCustomerName.setText(todayOrderStatus.getCustomerName());
            todaysOderNormalViewholder.txtDeliveryDate.setText(todayOrderStatus.getDeliveryDate());
            todaysOderNormalViewholder.txtAssignTo.setText(todayOrderStatus.getAssignTo());
            todaysOderNormalViewholder.txtPayment.setText(todayOrderStatus.getPayment());
        }
    }

    @Override
    public int getItemCount() {
        return orderStatusArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        TodayOrderStatus status = orderStatusArrayList.get(position);
        if (status.getType().equals("0")){
            return TYPE_HEADER;
        }else {
            return TYPE_NORMAL;
        }
    }

    public class TodaysOrdrHeaderViewholder extends RecyclerView.ViewHolder{
        TextView txtItemName,txtCustomerName,txtDeliveryDate,txtAssignTo,txtPayment;
        public TodaysOrdrHeaderViewholder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtDeliveryDate = itemView.findViewById(R.id.txtDeliveryDate);
            txtAssignTo = itemView.findViewById(R.id.txtAssignTo);
            txtPayment = itemView.findViewById(R.id.txtPayment);
        }
    }
    public class TodaysOderNormalViewholder  extends RecyclerView.ViewHolder{
        TextView txtItemName,txtCustomerName,txtDeliveryDate,txtAssignTo,txtPayment;
        public TodaysOderNormalViewholder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtDeliveryDate = itemView.findViewById(R.id.txtDeliveryDate);
            txtAssignTo = itemView.findViewById(R.id.txtAssignTo);
            txtPayment = itemView.findViewById(R.id.txtPayment);
        }
    }
}
