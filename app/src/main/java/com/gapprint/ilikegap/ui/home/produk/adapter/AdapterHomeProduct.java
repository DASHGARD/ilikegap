package com.gapprint.ilikegap.ui.home.produk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gapprint.ilikegap.detailproduk.DetailProduk;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.ui.home.produk.model.ModelHomeProduk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by  techsolpoint.com on 3/18/2018.
 */


public class AdapterHomeProduct extends RecyclerView.Adapter<AdapterHomeProduct.ViewHolder> {

    Context context;
    ArrayList<ModelHomeProduk> rvdatacommentArrayList;



    //


    public AdapterHomeProduct(Context context, ArrayList<ModelHomeProduk> rvdatacommentArrayList) {
        this.context = context;
        this.rvdatacommentArrayList = rvdatacommentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homeproduk, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ModelHomeProduk rvdatanewproduk = rvdatacommentArrayList.get(position);

        final String Nama = rvdatanewproduk.getNama();
        final String link = rvdatanewproduk.getLink();
        final String gambar = rvdatanewproduk.getGambar();

        holder.itemlink.setText(link);
        holder.itemName.setText(Nama);
//        String imageUrl = String.valueOf(semuapostitem.getThumbnail());
        Picasso.get().load(gambar).into(holder.itemgambar);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"On comming",Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(context, DetailProduk.class);
//                intent.putExtra("nama_produk", Nama);
//                intent.putExtra("gambar_produk", gambar);
//                context.startActivity(intent);
                String namaproduk = rvdatanewproduk.getNama();
                String gambarproduk = rvdatanewproduk.getGambar();
                String idproduk = rvdatanewproduk.getId();

                Toast.makeText(context,Nama,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailProduk.class);
                intent.putExtra("nama_produk", namaproduk);
                intent.putExtra("gambar_produk", gambarproduk);
                intent.putExtra("id_produk", idproduk);
                context.startActivity(intent);

//                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
//                httpIntent.setData(Uri.parse(link));
//                context.startActivity(httpIntent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return rvdatacommentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemlink;
        ImageView itemgambar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            itemName = itemView.findViewById(R.id.tvNamaPost);
            itemlink = itemView.findViewById(R.id.tvNamaKategoriPost);
            itemgambar = itemView.findViewById(R.id.ivTextDrawable);

        }
    }
}




