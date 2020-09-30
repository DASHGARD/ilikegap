package com.gapprint.ilikegap.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.MessagePattern;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.cart.Cart;
import com.gapprint.ilikegap.database.MyDatabaseHelper;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList produk_id,produk_image,produk_price,produk_title,produk_qty,produk_selected;
    private CartAdapter adapter;
    public CartAdapter(Activity activity, Context context, ArrayList produk_id, ArrayList produk_image, ArrayList produk_price,
                       ArrayList produk_title, ArrayList produk_qty,ArrayList produk_selected){
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
        View view = inflater.inflate(R.layout.item_cart, parent, false);
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
                Toast.makeText(activity,String.valueOf(produk_id.get(position)),Toast.LENGTH_SHORT).show();
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = holder.checkBox.isChecked();
                String id = String.valueOf(produk_id.get(position));
                if (isChecked){
                    MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
                    String selected = "true";
                    myDB.updateData2(id,selected);
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
                    String selected = "false";
                    myDB.updateData2(id,selected);
                }

                send();


                // Do something here.
            }
        });
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String id = String.valueOf(produk_id.get(position));
//            if (holder.checkBox.isChecked()){
//                MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
//                String selected = "true";
//                myDB.updateData2(id,selected);
//            }
//            else{
//                MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
//                String selected = "false";
//                myDB.updateData2(id,selected);
//
//
//
//            }
//            send();
////
//            calculate();
//
//            }
//        });
        holder.checkBox.setChecked(Boolean.parseBoolean(String.valueOf(produk_selected.get(position))));
        holder.produk_name.setText(String.valueOf(produk_title.get(position)));

        holder.produkqty.setRange(0,1000);
        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = Double.parseDouble(String.valueOf(produk_price.get(position)))*Double.parseDouble(String.valueOf(produk_qty.get(position)));
        String price = formatter.format(myNumber);
        double priceproduk = Double.parseDouble(String.valueOf(produk_price.get(position)));
        String price_produk = formatter.format(priceproduk);
        holder.produk_price.setText("Harga Produk = Rp."+price_produk);
        holder.produkqty.setNumber(String.valueOf(produk_qty.get(position)));
        holder.harga_total.setText("Subtotal : Rp."+price);
        holder.produkqty.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {


                MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
                String image = "2",
//                        id=String.valueOf(produk_id.get(position)),
                        id=String.valueOf(produk_id.get(position)),
                        title= "1",
                        price = "3",
                        qty = "4";
                image = String.valueOf(produk_image.get(position));
                price = String.valueOf(produk_price.get(position));
                title = String.valueOf(produk_title.get(position));
                qty = String.valueOf(newValue);
                myDB.updateData(id,qty);
                if(newValue == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Delete " + String.valueOf(produk_title.get(position))+ " ?");
                    builder.setMessage("Are you sure you want to delete " + String.valueOf(produk_title.get(position))+ " ?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
                            myDB.deleteOneRow(  String.valueOf(produk_id.get(position)));

                            send();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(activity);
                            String image = "2",
//                        id=String.valueOf(produk_id.get(position)),
                                    id=String.valueOf(produk_id.get(position)),
                                    title= "1",
                                    price = "3",
                                    qty = "4";
                            image = String.valueOf(produk_image.get(position));
                            price = String.valueOf(produk_price.get(position));
                            title = String.valueOf(produk_title.get(position));
                            qty = String.valueOf(1);
                            myDB.updateData(id,qty);
                            send();

                        }
                    });
//                    builder.create().show();
                    AlertDialog alert = builder.create();
                    alert.show();

                    Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton.setTextColor(Color.BLACK);
                    Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    pbutton.setTextColor(Color.BLACK);
                    alert.setCancelable(false);
//                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                        @Override
//                        public void onShow(final DialogInterface dialog) {
//                            Button negativeButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
//                            Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);
//
//                            // this not working because multiplying white background (e.g. Holo Light) has no effect
//                            //negativeButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
//
//                            final int negativeButtonDrawable =activity.getResources().getColor(R.color.orange);
//                            final int positiveButtonDrawable = activity.getResources().getColor(R.color.orange);
//                            if (Build.VERSION.SDK_INT >= 16) {
//                                negativeButton.setTextColor(negativeButtonDrawable);
//                                positiveButton.setTextColor(positiveButtonDrawable);
//                            } else {
//                                negativeButton.setTextColor(negativeButtonDrawable);
//                                positiveButton.setTextColor(positiveButtonDrawable);
//                            }
//                            negativeButton.invalidate();
//                            positiveButton.invalidate();
//                        }
//                    });

                    send();
                }

//                produk_price.remove(produk_price); //Actually change your list of items here
//                adapter.notifyDataSetChanged();
                send();






            }
        });

        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(book_id.get(position)));
//                intent.putExtra("title", String.valueOf(book_title.get(position)));
//                intent.putExtra("author", String.valueOf(book_author.get(position)));
//                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });


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


        TextView  produk_name, produk_price,harga_total;
        ElegantNumberButton produkqty;
        ImageView ivproductimage;
        CheckBox checkBox;
//        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            book_id_txt = itemView.findViewById(R.id.tv);
            harga_total = itemView.findViewById(R.id.hargatotal);
            ivproductimage = itemView.findViewById(R.id.ivprodukcart);
            produk_name = itemView.findViewById(R.id.tvnamaproduk);
            produk_price = itemView.findViewById(R.id.tvhargaproduk);
            checkBox = itemView.findViewById(R.id.checkbox);
            produkqty = itemView.findViewById(R.id.qvbutton);


//            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }


    }
}
