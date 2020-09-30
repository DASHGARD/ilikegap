package com.gapprint.ilikegap.halamankategori.childkategori.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.halamankategori.Category;
import com.gapprint.ilikegap.halamankategori.childkategori.model.Buku;
import com.squareup.picasso.Picasso;


import java.util.List;


public class AdapterChildCategory extends RecyclerView.Adapter<AdapterChildCategory.MyViewHolder> {

    Context context ;
    List<Buku> mData;
    int COLORPOSITION = 0;



    public AdapterChildCategory(Context context, List<Buku> mData, Category listener) {
        this.context = context;
        this.mData = mData;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_parentkategori,viewGroup,false);
        return new MyViewHolder(view);


        }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {


        myViewHolder.TvTitle.setText(mData.get(i).getTitle());
//        myViewHolder.ImgMovie.setImageResource(mData.get(i).getThumbnail());
        Picasso.get().load(mData.get(i).getThumbnail()).into(myViewHolder.ImgMovie);

        myViewHolder.parentparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = mData.get(i).getTitle();
//                myViewHolder.parentparent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                Toast.makeText(context,a,Toast.LENGTH_SHORT).show();

//                COLORPOSITION = i;

//                changecolor();
//



            }
        });


    }

    private void changecolor() {

    }

    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }

        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgMovie;
        private LinearLayout parentparent;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            parentparent = itemView.findViewById(R.id.parentparent);
            TvTitle = itemView.findViewById(R.id.tvNamakategori);
            ImgMovie = itemView.findViewById(R.id.ivTextDrawablekategori);



        }
    }
}
