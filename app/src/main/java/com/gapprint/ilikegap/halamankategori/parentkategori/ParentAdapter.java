package com.gapprint.ilikegap.halamankategori.parentkategori;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gapprint.ilikegap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manish on 11/1/2016.
 */


public class ParentAdapter extends ArrayAdapter<Parentmodel>
{
    Activity activity;
    int layoutResourceId;
//    ArrayList<Parentmodel> data=new ArrayList<Parentmodel>();
List<Parentmodel> heroList;
     Parentmodel pdf;

    public ParentAdapter(Activity activity, int layoutResourceId, List<Parentmodel> data) {
        super(activity, layoutResourceId, data);

        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.heroList=data;

    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row=convertView;
        PdfHolder holder=null;

        if(row==null)
        {

            LayoutInflater inflater= LayoutInflater.from(activity);

            row=inflater.inflate(layoutResourceId,parent,false);

            holder=new PdfHolder();


            holder.TvTittle = (TextView)row.findViewById(R.id.tvNamakategori);
            holder.parentparent = (LinearLayout) row.findViewById(R.id.parentparent);
            holder.ImgMovie = (ImageView) row.findViewById(R.id.ivTextDrawablekategori);



            row.setTag(holder);

        }
        else
        {
            holder= (PdfHolder) row.getTag();
        }

        pdf = heroList.get(position);

//        holder.textViewUrl.setText(pdf.getUrl());
        holder.TvTittle.setText(pdf.getTitle());
//        holder.ImgMovie.setImageResource(pdf.getThumbnail());
        Picasso.get().load(pdf.getThumbnail()).into(holder.ImgMovie);
//        if (pdf.getStatus().equals("Belum di Review")){
//
//            row.setBackgroundColor(R.color.colorPrimaryDark);
//        }
//        if (pdf.getStatus().equals("Kreasi di terima")){
//
//            row.setBackgroundColor(R.color.colorAccent);
//        }

        return row;
    }


    class PdfHolder
    {


        LinearLayout parentparent;
        TextView TvTittle;
        ImageView ImgMovie;



    }

}

