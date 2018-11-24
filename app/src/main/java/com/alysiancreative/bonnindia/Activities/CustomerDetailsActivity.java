package com.alysiancreative.bonnindia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.alysiancreative.bonnindia.R;

public class CustomerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fontColor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView customerId = findViewById(R.id.customerId);
        TextView customerName = findViewById(R.id.customerName);
        TextView phone = findViewById(R.id.phone);
        TextView email = findViewById(R.id.email);
        TextView addresss = findViewById(R.id.address);

        Intent intent = getIntent();
        customerId.setText(intent.getStringExtra("CustomerId"));
        customerName.setText(intent.getStringExtra("CustomerName"));
        phone.setText(intent.getStringExtra("Mobile"));
        email.setText(intent.getStringExtra("Email"));
        addresss.setText(intent.getStringExtra("Address"));
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
