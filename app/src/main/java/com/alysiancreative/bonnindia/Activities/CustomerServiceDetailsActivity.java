package com.alysiancreative.bonnindia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alysiancreative.bonnindia.R;

public class CustomerServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fontColor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView jobCardNumber = findViewById(R.id.jobCardNumber);
        TextView assignedTo = findViewById(R.id.assignedTo);
        TextView estimatedServicePayment = findViewById(R.id.estmatedServicePayment);
        TextView priorityPeriod = findViewById(R.id.priorityPeriod);
        TextView serviceType = findViewById(R.id.serviceType);
        TextView issue = findViewById(R.id.issue);
        TextView complaniType = findViewById(R.id.complainType);
        TextView serviceStatus = findViewById(R.id.serviceStatus);
        TextView customerId = findViewById(R.id.customerId);
        TextView customerName = findViewById(R.id.customerName);
        TextView customerEmail = findViewById(R.id.customerEmail);
        TextView customerMobile = findViewById(R.id.customerMobile);
        TextView customerAddress = findViewById(R.id.customerAddress);
        TextView createdOn = findViewById(R.id.createdOn);
        TextView modifiedOn = findViewById(R.id.modifiedOn);

        Intent intent = getIntent();
        jobCardNumber.setText(intent.getStringExtra("JOBCARDNUMBER"));
        assignedTo.setText(intent.getStringExtra("ASSIGNEDTO"));
        estimatedServicePayment.setText(intent.getStringExtra("ESTIMATEDSERVICEPAYMENT"));
        priorityPeriod.setText(intent.getStringExtra("PRIORITYPERIOD"));
        serviceType.setText(intent.getStringExtra("SERVICETYPE"));
        issue.setText(intent.getStringExtra("ISSUE"));
        complaniType.setText(intent.getStringExtra("COMPLAINTYPE"));
        serviceStatus.setText(intent.getStringExtra("SERVICESTATUS"));
        switch (intent.getStringExtra("SERVICESTATUS")) {
            case "New":
                serviceStatus.setBackgroundColor(this.getResources().getColor(R.color.red));
                break;
            case "In progress":
                serviceStatus.setBackgroundColor(this.getResources().getColor(R.color.yellow));
                break;
            case "Done":
                serviceStatus.setBackgroundColor(this.getResources().getColor(R.color.green));
                break;
            case "closed":
                serviceStatus.setBackgroundColor(this.getResources().getColor(R.color.darkgrey));
                break;
        }
        customerId.setText(intent.getStringExtra("CUSTOMERID"));
        customerName.setText(intent.getStringExtra("CUSTOMERNAME"));
        customerEmail.setText(intent.getStringExtra("CUSTOMEREMAIL"));
        customerMobile.setText(intent.getStringExtra("CUSTOMERMOBILE"));
        customerAddress.setText(intent.getStringExtra("CUSTOMERPHONE"));
        createdOn.setText(intent.getStringExtra("CREATEDON"));
        modifiedOn.setText(intent.getStringExtra("MODIFIEDON"));
    }

    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
