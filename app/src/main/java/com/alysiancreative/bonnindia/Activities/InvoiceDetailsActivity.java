package com.alysiancreative.bonnindia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alysiancreative.bonnindia.R;

public class InvoiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fontColor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView id = findViewById(R.id.id);
        TextView customerId = findViewById(R.id.customerId);
        TextView billNo = findViewById(R.id.billNo);
        TextView billDate = findViewById(R.id.billDate);
        TextView paymentTerms = findViewById(R.id.paymentTerms);
        TextView dueDate = findViewById(R.id.dueDate);
        TextView referenceNo = findViewById(R.id.referenceNo);
        TextView referenceDate = findViewById(R.id.referenceDate);
        TextView pos = findViewById(R.id.pos);
        TextView billType = findViewById(R.id.billType);
        TextView totalAmount = findViewById(R.id.totalAmount);
        TextView totalDiscount = findViewById(R.id.totalDiscount);
        TextView totalTaxableValue = findViewById(R.id.totalTaxableValue);
        TextView totalTax = findViewById(R.id.totalTax);
        TextView totalSubtotal = findViewById(R.id.totalSubtotal);
        TextView tradeDiscount = findViewById(R.id.tradeDiscount);
        TextView billAmount = findViewById(R.id.billAmount);
        TextView createdOn = findViewById(R.id.createdOn);
        TextView modifiedOn = findViewById(R.id.modifiedOn);

        Intent intent = getIntent();
        id.setText(intent.getStringExtra("ID"));
        customerId.setText(intent.getStringExtra("CUSTOMERID"));
        billNo.setText(intent.getStringExtra("BILLNO").substring(9));
        billDate.setText(intent.getStringExtra("BILLDATE"));
        paymentTerms.setText(intent.getStringExtra("PAYMENTTERMS"));
        dueDate.setText(intent.getStringExtra("DUEDATE"));
        referenceNo.setText(intent.getStringExtra("REFERENCENO"));
        referenceDate.setText(intent.getStringExtra("REFERENCEDATE"));
        pos.setText(intent.getStringExtra("POS"));
        billType.setText(intent.getStringExtra("BILLTYPE"));
        totalAmount.setText(intent.getStringExtra("TOTALAMOUNT"));
        totalDiscount.setText(intent.getStringExtra("TOTALDISCOUNT"));
        totalTaxableValue.setText(intent.getStringExtra("TOTALTAXABLEVALUE"));
        totalTax.setText(intent.getStringExtra("TOTALTAX").substring(11));
        totalSubtotal.setText(intent.getStringExtra("TOTALSUBTOTAL"));
        tradeDiscount.setText(intent.getStringExtra("TRADEDISCOUNT"));
        billAmount.setText(intent.getStringExtra("BILLAMOUNT").substring(13));
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
