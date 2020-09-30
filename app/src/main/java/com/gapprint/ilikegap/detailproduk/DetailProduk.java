package com.gapprint.ilikegap.detailproduk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.cart.Cart;
import com.gapprint.ilikegap.database.MyDatabaseHelper;
import com.gapprint.ilikegap.detailproduk.produkserupa.AdapterProductSerupa;
import com.gapprint.ilikegap.detailproduk.produkserupa.ModelProdukserupa;
import com.gapprint.ilikegap.ui.home.produk.adapter.AdapterHomeProduct;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class DetailProduk extends AppCompatActivity {
    Button buttonbuy,buttoncart,buttonchat;
    String namaproduk,namaproduktrimm;
    String gambarproduk,id;
    ImageView shopingcartbt;
    //Home produk
    ArrayList<ModelProdukserupa> modelProdukserupas;
    RecyclerView rvProdukserupa;
    AdapterProductSerupa adapterProductSerupa;
    RequestQueue requestQueue;
    //
    TextView tvNamaProduk;
    ImageView ivGambarProduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        DetailProduk.this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Context context;
        requestQueue = Volley.newRequestQueue(DetailProduk.this);
        buttonbuy = (Button)findViewById(R.id.buttonbuy);
        buttoncart = (Button)findViewById(R.id.buttoncart);
        shopingcartbt = (ImageView)findViewById(R.id.shoping_cart);
        buttonchat = (Button)findViewById(R.id.chatbutton);
        tvNamaProduk = (TextView)findViewById(R.id.tvjudulprduk);
        ivGambarProduk = (ImageView) findViewById(R.id.ivProductDetailImage);
        namaproduk = getIntent().getStringExtra("nama_produk");
        gambarproduk = getIntent().getStringExtra("gambar_produk");
        id = getIntent().getStringExtra("id_produk");
        Toast.makeText(DetailProduk.this,id,Toast.LENGTH_SHORT).show();
        namaproduktrimm = namaproduk.substring(0,6);
        Picasso.get().load(gambarproduk).into(ivGambarProduk);
        tvNamaProduk.setText(namaproduk);
        //====================Homeproduk ACTION/inisialisai========================//
        rvProdukserupa = findViewById(R.id.rvProdukserupa);
        modelProdukserupas = new ArrayList<>();
        rvProdukserupa.setHasFixedSize(true);
        GridLayoutManager layoutManagerhomeproduk =
                new GridLayoutManager(DetailProduk.this, 1, GridLayoutManager.HORIZONTAL, false);
        rvProdukserupa.setLayoutManager(layoutManagerhomeproduk);
        //=======================================================================//
        buttonbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailProduk.this,"Oncomming", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(DetailProduk.this);
                myDB.addCart(id,
                        gambarproduk,
                        "10000",
                        namaproduk,
                        "1",
                        "true"
                );

                Intent a = new Intent(DetailProduk.this, Cart.class);
                startActivity(a);
            }
        });
        shopingcartbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailProduk.this,Cart.class);
                startActivity(a);

            }
        });
        buttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(DetailProduk.this,"Sudah dimasukan ke keranjang", Toast.LENGTH_SHORT).show();
                String testimage = "1";
                String qty = "1";
                String price = "10000";
                    String title2 = "4";
                MyDatabaseHelper myDB = new MyDatabaseHelper(DetailProduk.this);
                myDB.addCart(id,
                        gambarproduk,
                        "10000",
                        namaproduk,
                        "1",
                        "true"
                        );
            }
        });
        buttonchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailProduk.this,"Oncomming", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(DetailProduk.this);
                myDB.deleteAllData();
            }
        });
        parseprodukserupa();
    }
    private void parseprodukserupa() {
        String url = "https://suma.geloraaksara.co.id/search/produk?keyword="+namaproduktrimm;
        System.out.print(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("PaRSE JSON", response + "");
                        try {
                            JSONArray jsonArray = response.getJSONArray("produk");

                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                String id = data.getString("id");
                                String nama = data.getString("nama_produk");
                                String gambar = data.getString("gambar");
                                String link = data.getString("link");
                                modelProdukserupas.add(new ModelProdukserupa(id,nama, gambar,link));
                                adapterProductSerupa = new AdapterProductSerupa(DetailProduk.this, modelProdukserupas);
                                rvProdukserupa.setAdapter(adapterProductSerupa);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}