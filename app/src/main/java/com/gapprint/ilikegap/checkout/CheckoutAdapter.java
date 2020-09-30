package com.gapprint.ilikegap.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.database.MyDatabaseHelper;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList produk_id,produk_image,produk_price,produk_title,produk_qty,produk_selected;
    private CheckoutAdapter adapter;
    public CheckoutAdapter(Activity activity, Context context, ArrayList produk_id, ArrayList produk_image, ArrayList produk_price,
                           ArrayList produk_title, ArrayList produk_qty, ArrayList produk_selected){
        this.activity = activity;
        this.context = context;
        this.produk_id = produk_id;
        this.produk_image = produk_image;
        this.produk_price = produk_price;
        this.produk_title = produk_title;
        this.produk_qty = produk_qty;
        this.produk_selected = produk_selected;
        this.adapter = this;
        int hasil = 0;
        int produksize = produk_id.size();

//        addcheck();
        calculate();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_checkout, parent, false);
//        addcheck();
        calculate();


        return new MyViewHolder(view);
    }

    private void calculate() {
        int hasil = 0;
        int produksize = produk_id.size();



        for (int i =0 ; i <produksize;i++ ){

            if (Boolean.parseBoolean(String.valueOf(produk_selected.get(i)))==true) {
                int produk = Integer.parseInt(String.valueOf(produk_price.get(i))) * Integer.parseInt(String.valueOf(produk_qty.get(i)));
                hasil += produk;
            }

        }

//        Toast.makeText(activity,String.valueOf(hasil),Toast.LENGTH_SHORT).show();
        //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));

        Intent intent = new Intent("custom-message");
        intent.putExtra("hasil",String.valueOf(hasil));


        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        String selected= String.valueOf(produk_selected.get(position));
        if (selected.equals("true")) {
            Picasso.get().load(String.valueOf(produk_image.get(position))).into(holder.ivproductimage);
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                calculate();
//
//            }
//        });
            holder.ivproductimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, String.valueOf(produk_id.get(position)), Toast.LENGTH_SHORT).show();
                }
            });


            holder.produk_name.setText(String.valueOf(produk_title.get(position)));
            holder.quantity_produk.setText(String.valueOf(produk_qty.get(position))+" Barang");
//        holder.produkqty.setRange(0,1000);
            NumberFormat formatter = new DecimalFormat("#,###");
            double myNumber = Double.parseDouble(String.valueOf(produk_price.get(position)))*Double.parseDouble(String.valueOf(produk_qty.get(position)));
            String price = formatter.format(myNumber);
            holder.produk_price.setText("Rp."+price);


        }
        else {
            holder.checkoutitem.setVisibility(View.GONE);
            holder.checkoutitem.setLayoutParams(new RecyclerView.LayoutParams(0,0));

        }
    }

    private void send() {
        Intent intent = new Intent("yes");
        intent.putExtra("yes","yes");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
        calculate();
    }


    private void clear() {
        produk_id.clear();
        produk_price.clear();
        produk_qty.clear();
        produk_title.clear();
        produk_image.clear();




    }


    @Override
    public int getItemCount() {
        return produk_id.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView  produk_name, produk_price, quantity_produk;
       LinearLayout checkoutitem;
        ImageView ivproductimage;

//        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkoutitem = itemView.findViewById(R.id.checkoutlinearitem);
//            book_id_txt = itemView.findViewById(R.id.tv);
            ivproductimage = itemView.findViewById(R.id.ivprodukcart);
            produk_name = itemView.findViewById(R.id.tvnamaproduk);
            quantity_produk = itemView.findViewById(R.id.tvquantityproduk);
            produk_price = itemView.findViewById(R.id.tvhargaproduk);



//            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }


    }
}
