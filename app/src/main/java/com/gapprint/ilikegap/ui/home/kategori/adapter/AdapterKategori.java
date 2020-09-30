package com.gapprint.ilikegap.ui.home.kategori.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.ui.home.kategori.model.Parentmodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by  techsolpoint.com on 3/18/2018.
 */


public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {

    Context context;
    ArrayList<Parentmodel> rvdatacommentArrayList;


    //


    public AdapterKategori(Context context, ArrayList<Parentmodel> rvdatacommentArrayList) {
        this.context = context;
        this.rvdatacommentArrayList = rvdatacommentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kategori, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Parentmodel rvdatanewproduk = rvdatacommentArrayList.get(position);

        String Nama = rvdatanewproduk.getNama();
        final String link = rvdatanewproduk.getLink();
        String gambar = rvdatanewproduk.getGambar();

//        holder.itemlink.setText(link);
        holder.itemName.setText(Nama);
//        String imageUrl = String.valueOf(semuapostitem.getThumbnail());


        Picasso.get().load(gambar).into(holder.itemgambar);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"On comming",Toast.LENGTH_SHORT).show();
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
//        TextView itemlink;
        ImageView itemgambar;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            itemName = itemView.findViewById(R.id.tvNamakategori);

            itemgambar = itemView.findViewById(R.id.ivTextDrawablekategori);

        }
    }
}




