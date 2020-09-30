package com.gapprint.ilikegap.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gapprint.ilikegap.R;


import com.gapprint.ilikegap.database.MyDatabaseHelper;
import com.gapprint.ilikegap.helper.SharedPrefManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
    ArrayList<String> produk_id, produk_image, produl_price, produk_title,produk_qty,produk_selected;
    CheckoutAdapter checkoutadapter;
    Button pesan;
    RecyclerView checkoutrv ;
    private CheckBox checkBox;
    TextView totalprice,backbtn;
    SharedPrefManager sharedPrefManager;
    MyDatabaseHelper myDB;
    int totalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        checkoutrv = (RecyclerView) findViewById(R.id.rvcheckout);
        totalprice = (TextView)findViewById(R.id.tvharga);
        backbtn = (TextView) findViewById(R.id.backbt);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
        getdata();

    }
    private void getdata() {

        myDB = new MyDatabaseHelper(Checkout.this);
        produk_id = new ArrayList<>();
        produk_image = new ArrayList<>();
        produl_price = new ArrayList<>();
        produk_title = new ArrayList<>();
        produk_qty = new ArrayList<>();
        produk_selected = new ArrayList<>();

        storeDataInArrays();

        checkoutadapter = new CheckoutAdapter(Checkout.this,this, produk_id, produk_image, produl_price, produk_title,produk_qty,produk_selected);
        checkoutrv.setAdapter(checkoutadapter);
        checkoutrv.setLayoutManager(new LinearLayoutManager(Checkout.this));
    }
    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                produk_id.add(cursor.getString(0));
                produk_image.add(cursor.getString(1));
                produl_price.add(cursor.getString(2));
                produk_title.add(cursor.getString(3));
                produk_qty.add(cursor.getString(4));
                produk_selected.add(cursor.getString(5));
            }
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
        }
    }


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String totalpricetext = intent.getStringExtra("hasil");
//
////
////
//////            String qty = intent
//            if(yes.equals("yes")) {
//////
////                getdata();
////            }
//////            }.getStringExtra("quantity");
//////            Toast.makeText(Cart.this, totalpricetext , Toast.LENGTH_SHORT).show();
            NumberFormat formatter = new DecimalFormat("#,###");
            double myNumber = Double.parseDouble(String.valueOf(totalpricetext));
            String price = formatter.format(myNumber);
            totalprice.setText("Rp."+String.valueOf(price));


        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}