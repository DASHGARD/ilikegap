package com.gapprint.ilikegap.cart;



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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gapprint.ilikegap.checkout.Checkout;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.cart.adapter.CartAdapter;
import com.gapprint.ilikegap.database.MyDatabaseHelper;
import com.gapprint.ilikegap.helper.SharedPrefManager;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ArrayList<String> produk_id, produk_image, produl_price, produk_title,produk_qty,produk_selected;
    CartAdapter cartadapter;
    Button pesan;
    RecyclerView cartrv ;
    private CheckBox checkBox;
    TextView totalprice,backbtn;
    SharedPrefManager sharedPrefManager;
    MyDatabaseHelper myDB;
    int totalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));




        LocalBroadcastManager.getInstance(this).registerReceiver(yes,
                new IntentFilter("yes"));
        cartrv = (RecyclerView) findViewById(R.id.rvcart);
        totalprice = (TextView) findViewById(R.id.tvharga);
        backbtn = (TextView) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

pesan = (Button)findViewById(R.id.pesanbt);
pesan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Cart.this,String.valueOf(produk_selected),Toast.LENGTH_SHORT).show();
        Intent a = new Intent(Cart.this, Checkout.class);
        startActivity(a);
    }
});
        getdata();
        totalprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });

        int produksize = produk_id.size();
//        for (int i =0 ; i < produksize;i++){
//            MyDatabaseHelper myDB = new MyDatabaseHelper(Cart.this);
//            String selected = "true";
//            String id = String.valueOf(i);
//            myDB.cartonstart(id,selected);
//
//        }
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String totalpricetext = intent.getStringExtra("hasil");

//
//
////            String qty = intent
//            if(yes.equals("yes")) {
////
//                getdata();
//            }
////            }.getStringExtra("quantity");
////            Toast.makeText(Cart.this, totalpricetext , Toast.LENGTH_SHORT).show();
            NumberFormat formatter = new DecimalFormat("#,###");
            double myNumber = Double.parseDouble(String.valueOf(totalpricetext));
            String price = formatter.format(myNumber);
            totalprice.setText("Rp."+String.valueOf(price));

        }
    };
    public BroadcastReceiver yes = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            String yes = intent.getStringExtra("yes");
            getdata();

        }
    };

    @Override
    protected void onStart() {
//        addcheck();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        addcheck();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        addcheck();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        addcheck();
        super.onBackPressed();
    }

    public void addcheck(){
        for (int i=0 ; i <produk_id.size();i++) {
            MyDatabaseHelper myDB = new MyDatabaseHelper(Cart.this);

            String id = String.valueOf(produk_id.get(i));
            String selected = "true";
            myDB.updateData2(id, "true");

        }




    }
    private void getdata() {

        myDB = new MyDatabaseHelper(Cart.this);
        produk_id = new ArrayList<>();
        produk_image = new ArrayList<>();
        produl_price = new ArrayList<>();
        produk_title = new ArrayList<>();
        produk_qty = new ArrayList<>();
        produk_selected = new ArrayList<>();

        storeDataInArrays();

        cartadapter = new CartAdapter(Cart.this,this, produk_id, produk_image, produl_price, produk_title,produk_qty,produk_selected);
        cartrv.setAdapter(cartadapter);
        cartrv.setLayoutManager(new LinearLayoutManager(Cart.this));
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


}