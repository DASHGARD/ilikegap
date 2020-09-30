package com.gapprint.ilikegap.halamankategori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.halamankategori.parentkategori.ParentAdapter;

import com.gapprint.ilikegap.halamankategori.childkategori.adapter.AdapterChildCategory;
import com.gapprint.ilikegap.halamankategori.childkategori.model.Buku;

import com.gapprint.ilikegap.halamankategori.parentkategori.Parentmodel;
import com.gapprint.ilikegap.helper.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {
//    //kategori
//    ArrayList<ModelParentKategori> modelParentKategoris;
//    RecyclerView parent_kategori;
//    AdapterParentKategori adapterParentKategori;
//    //
    int mSelectedItem;
    RelativeLayout rlJudul;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView Childlist ;
    ListView Parentlist;
    ParentAdapter childAdapter;
    SharedPrefManager sharedPrefManager;
    ListView listView;
    //a List of type hero for holding list items
    List<Parentmodel> parentmodels;
    String produk = "Custom Kalender",thumbnail =  "https://brandtalk.id/wp-content/uploads/2019/10/kalender-duduk-1-933x675.png";
    //the listview
    ListView listView2;
    List<Buku> buku = new ArrayList<>();
    AdapterChildCategory adapterChildCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Childlist = findViewById(R.id.grid);

        sharedPrefManager = new SharedPrefManager(this);

        rlJudul = (RelativeLayout)findViewById(R.id.judul);
        rlJudul.setBackground(ContextCompat.getDrawable(Category.this,R.drawable.orangebackground));
        listView = (ListView) findViewById(R.id.parentkategori);
        listView.setItemChecked(0,true);
        listView.setSelection(0);





//initializing objects

                parentmodels = new ArrayList<>();

        //adding some values to our list
        parentmodels.add(new Parentmodel("Custom Kaleder", "https://brandtalk.id/wp-content/uploads/2019/10/kalender-duduk-1-933x675.png"));
        parentmodels.add(new Parentmodel("Kulit", "https://www.blibli.com/friends/assets/2017/11/produk-berbahan-kulit.jpg"));
        parentmodels.add(new Parentmodel("Mug", "https://store.primagraphia.co.id/wp-content/uploads/2017/10/Mug-polos-.jpg"));
        parentmodels.add(new Parentmodel("Produk Paket", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg"));
        parentmodels.add(new Parentmodel("Stationary", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg"));
        parentmodels.add(new Parentmodel("Personal Product", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg"));
        parentmodels.add(new Parentmodel("Jurnal", "https://ilikegap.com/wp-content/uploads/2017/12/Jurnal-Eksklusif-copy-600x600-300x300.jpg"));
        parentmodels.add(new Parentmodel("T-Shirt", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg"));
        parentmodels.add(new Parentmodel("APD", "https://brandtalk.id/wp-content/uploads/2019/10/kalender-duduk-1-933x675.png"));
        parentmodels.add(new Parentmodel("Promotion Tools", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg"));
        parentmodels.add(new Parentmodel("Photobook", "https://store.primagraphia.co.id/wp-content/uploads/2017/10/Mug-polos-.jpg"));
        parentmodels.add(new Parentmodel("3D Print", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg"));
        parentmodels.add(new Parentmodel("Season Product", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg"));

        //creating the adapter
        ParentAdapter adapter = new ParentAdapter(this, R.layout.item_parentkategori, parentmodels);

        //attaching adapter to the listview
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.gapprint.ilikegap.halamankategori.parentkategori.Parentmodel parentkategori = (com.gapprint.ilikegap.halamankategori.parentkategori.Parentmodel) parent.getItemAtPosition(position);
                Toast.makeText(Category.this,"TEST POSITION = "+position,Toast.LENGTH_SHORT).show();

                view.setSelected(true);
//                String nullstring = null;
//                buku.clear();

                buku.clear();
                produk = parentkategori.getTitle();
                thumbnail = parentkategori.getThumbnail();
                
                Toast.makeText(Category.this,produk,Toast.LENGTH_SHORT).show();
                childkategori();

//                clear();
//                produk = "buku tulis";
//                childkategori();
//                buku.clear();
//                if (position == 0){
//                    produk = "buku tulis";
//                    childkategori();
//                }
//
//                else if (position == 1){
//                    produk = "Pensil";
//                    childkategori();
//                }
//                else {
//
//                    clear();
//                }





            }
        });
        childkategori();

    }


    private void clear() {

            buku.clear(); // clear list
            adapterChildCategory.notifyDataSetChanged(); // let your adapter know about the changes and reload view.

    }

    private void childkategori() {


        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));
        buku.add(new Buku(produk, thumbnail));


        adapterChildCategory = new AdapterChildCategory(this, buku, this);
        Childlist.setAdapter(adapterChildCategory);
        Childlist.setHasFixedSize(true);
        GridLayoutManager layoutManagerhomeproduk =
                new GridLayoutManager(Category.this, 4, GridLayoutManager.VERTICAL, false);
        Childlist.setLayoutManager(layoutManagerhomeproduk);

    }
}