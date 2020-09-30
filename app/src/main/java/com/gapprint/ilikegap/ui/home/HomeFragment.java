package com.gapprint.ilikegap.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gapprint.ilikegap.R;
import com.gapprint.ilikegap.cart.Cart;
import com.gapprint.ilikegap.halamankategori.Category;

import com.gapprint.ilikegap.ui.home.banner.SliderAdapterExample;
import com.gapprint.ilikegap.ui.home.banner.model.SliderItem;

import com.gapprint.ilikegap.ui.home.kategori.adapter.AdapterKategori;
import com.gapprint.ilikegap.ui.home.kategori.model.Parentmodel;
import com.gapprint.ilikegap.ui.home.newproduk.adapter.AdapterNewProduct;
import com.gapprint.ilikegap.ui.home.newproduk.model.ModelNewProduk;
import com.gapprint.ilikegap.ui.home.produk.adapter.AdapterHomeProduct;
import com.gapprint.ilikegap.ui.home.produk.model.ModelHomeProduk;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView allcategory;


    //banner
    SliderView sliderView;
    private SliderAdapterExample adapter;
    //
    //header
    RelativeLayout headerrelative;
    //
    //scrollview
    ImageView imagetranparnt,shooping_cart;
    int scrollX;
    NestedScrollView homefragmentMainScrollview;
    //
    private HomeViewModel homeViewModel;
    //new produk
    ArrayList<ModelNewProduk> modelNewProduks;
    RecyclerView rvnewproduk;
    AdapterNewProduct adapterNewProduct;
    RequestQueue requestQueue;
    //
    //Home produk
    ArrayList<ModelHomeProduk> modelHomeProduks;
    RecyclerView rvhomeproduk;
    AdapterHomeProduct adapterHomeProduct;
    //
    //context
//    private Context mContext;

    //kategori
    ArrayList<Parentmodel> modelKategoris;
    RecyclerView list_kategori;
    AdapterKategori adapterKategori;
    //
    //searchproduk
    CardView searchproduk;
    //
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //====================Volley=========================//
        requestQueue = Volley.newRequestQueue(getActivity());
        //====================Volley=========================//

        allcategory = (TextView)root.findViewById(R.id.allcategory);

        //====================Header=========================//
        headerrelative = (RelativeLayout)root.findViewById(R.id.trasparenheader);
        //====================Header=========================//
        //====================NewProduk ACTION/inisialisai========================//

        rvnewproduk = root.findViewById(R.id.rvnewproduk);
        modelNewProduks = new ArrayList<>();
        rvnewproduk.setHasFixedSize(true);
        LinearLayoutManager layoutManagerblocknote = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvnewproduk.setLayoutManager(layoutManagerblocknote);

        //=======================================================================//
        //====================Homeproduk ACTION/inisialisai========================//

        rvhomeproduk = root.findViewById(R.id.rvhomeproduk);
        modelHomeProduks = new ArrayList<>();
        rvhomeproduk.setHasFixedSize(true);
        GridLayoutManager layoutManagerhomeproduk =
                new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rvhomeproduk.setLayoutManager(layoutManagerhomeproduk);

        //=======================================================================//
        //====================Kategori ACTION/inisialisai========================//

        list_kategori = root.findViewById(R.id.list_kategori);
        modelKategoris = new ArrayList<>();
        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        list_kategori.setLayoutManager(layoutManager);
        //=======================================================================//
        //Scrollview
        homefragmentMainScrollview = (NestedScrollView) root.findViewById(R.id.scrolviewhomefragment);

        imagetranparnt = (ImageView)root.findViewById(R.id.background_transparent);
        shooping_cart = (ImageView)root.findViewById(R.id.shoping_cart);
        //

        //shopingcart
        shooping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(getActivity(), Cart.class);
                startActivity(a);
                Toast.makeText(getActivity(),"Dalam pengerjaan" ,Toast.LENGTH_SHORT).show();
            }
        });

        //

        //searchproduk
        searchproduk = (CardView)root.findViewById(R.id.searchproduk);
        searchproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"On Comming",Toast.LENGTH_LONG).show();
            }
        });
        //

        imagetranparnt.setAlpha(0.0f);
        //banner
        sliderView = root.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getActivity());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        //

        //imageslider

        //
        allcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), Category.class);
                startActivity(a);
            }
        });


        //
        //Scrollview
        homefragmentMainScrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                scrollX = homefragmentMainScrollview.getScrollY();
//                Log.d(Log, "scrollX: " + scrollX);
                String a = String.valueOf(scrollX);
//                Toast.makeText(getActivity(),"Scrollx "+a,Toast.LENGTH_SHORT).show();


                if(scrollX < 200 && scrollX > 0){
                    imagetranparnt.setAlpha(0.0f);
                    shooping_cart.setColorFilter(getActivity().getResources().getColor(R.color.colorAccent));
                    getActivity().getWindow().getDecorView().setSystemUiVisibility(0);
                }
                if(scrollX > 0 && scrollX > 200 ){

                    imagetranparnt.setAlpha(0.1f);
                    shooping_cart.setColorFilter(getActivity().getResources().getColor(R.color.colorAccent));
                }
                if(scrollX > 0 && scrollX > 220 ){
                    imagetranparnt.setAlpha(0.2f);
                    shooping_cart.setColorFilter(getActivity().getResources().getColor(R.color.colorAccent));
                }
                if(scrollX > 0 && scrollX > 230 ){
                    imagetranparnt.setAlpha(0.3f);

                }
                if(scrollX > 0 && scrollX > 240 ){
                    imagetranparnt.setAlpha(0.4f);
//
                }
                if(scrollX > 0 && scrollX > 250 ){
                    imagetranparnt.setAlpha(0.5f);
                    getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    shooping_cart.setColorFilter(getActivity().getResources().getColor(R.color.colorPrimary));
                }
                if(scrollX > 0 && scrollX > 260){
                    imagetranparnt.setAlpha(0.6f);
                }
                if(scrollX > 0 && scrollX > 270){
                    imagetranparnt.setAlpha(0.7f);
                }
                if(scrollX > 0 && scrollX > 280 ){
                    imagetranparnt.setAlpha(0.8f);

                }
                if(scrollX > 0 && scrollX > 290 ){
                    imagetranparnt.setAlpha(0.9f);

                }
                if(scrollX > 0 && scrollX > 300 ){
                    imagetranparnt.setAlpha(1.0f);

                }
                if (String.valueOf(scrollX).equals(null) ){
                    scrollX = 0;
                }
            }
        });

        //


        rene();
        kategori();
        parsenewproduk();
        parsehomeproduk();
        return root;
    }

    @Override
    public void onResume() {
        imagetranparnt.setAlpha(0.0f);
        homefragmentMainScrollview.smoothScrollTo(0, 0);
        super.onResume();

    }
    @Override
    public void onDestroy() {
        homefragmentMainScrollview.smoothScrollTo(0, 0);
        scrollX = 0;
        super.onDestroy();
    }

    @Override
    public void onStop() {
        homefragmentMainScrollview.smoothScrollTo(0, 0);
        scrollX = 0;
        super.onStop();
    }
    @Override
    public void onPause() {
        homefragmentMainScrollview.smoothScrollTo(0, 0);
        scrollX = 0;
        super.onPause();
    }

    @Override
    public void onStart() {
        homefragmentMainScrollview.smoothScrollTo(0, 0);
        scrollX = 0;

        super.onStart();
    }
    private void parsenewproduk() {
        String url = "https://suma.geloraaksara.co.id/search/produk?keyword=buku";
        System.out.print(url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("PaRSE JSON", response + "");
                        try {



                            JSONArray jsonArray = response.getJSONArray("produk");

                            for (int i = 0; i <= 5; i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                String id = data.getString("id");
                                String nama = data.getString("nama_produk");
                                String gambar = data.getString("gambar");
                                String link = data.getString("link");
                                modelNewProduks.add(new ModelNewProduk(id,nama, gambar,link));
                                adapterNewProduct = new AdapterNewProduct(getActivity(), modelNewProduks);
                                rvnewproduk.setAdapter(adapterNewProduct);

//                                autoscrollcuy();
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



//    private void autoscrollcuy() {
//        //autoscrollnew produk
//        final int speedScroll = 4000;
//        final Handler handler = new Handler();
//
//
//        final Runnable runnable = new Runnable() {
//            int count = 0;
//            @Override
//            public void run() {
//                if(count < modelNewProduks.size()){
//                    rvnewproduk.smoothScrollToPosition(count++);
//                    handler.postDelayed(this,speedScroll);
//                }
//                else {
//                    rvnewproduk.smoothScrollToPosition(0);
//                    handler.postDelayed(this,speedScroll);
//                    count = 0;
//                }
//
//
//            }
//        };
//
//        handler.postDelayed(runnable,speedScroll);
//    }

    private void parsehomeproduk() {
        String url = "https://suma.geloraaksara.co.id/search/produk?keyword=";
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
                                modelHomeProduks.add(new ModelHomeProduk(id,nama, gambar,link));
                                adapterHomeProduct = new AdapterHomeProduct(getActivity(), modelHomeProduks);
                                rvhomeproduk.setAdapter(adapterHomeProduct);
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
    private void kategori() {
//        String url = "https://suma.geloraaksara.co.id/search/produk?keyword=";
//        System.out.print(url);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Log.e("PaRSE JSON", response + "");
//                        try {
//
//
//
//                            JSONArray jsonArray = response.getJSONArray("produk");
//
//                            for (int i = 0; i <= jsonArray.length(); i++) {
//                                JSONObject data = jsonArray.getJSONObject(i);
//                                String nama = data.getString("nama_produk");
//                                String gambar = data.getString("gambar");
//                                String link = data.getString("link");
//                                modelKategoris.add(new ModelKategori(nama, gambar,link));
//                                adapterKategori = new AdapterKategori(getActivity(), modelKategoris);
//                                list_kategori.setAdapter(adapterKategori);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        requestQueue.add(request);


        modelKategoris.add(new Parentmodel("Custom Kaleder", "https://brandtalk.id/wp-content/uploads/2019/10/kalender-duduk-1-933x675.png","TEST"));
        modelKategoris.add(new Parentmodel("Kulit", "https://www.blibli.com/friends/assets/2017/11/produk-berbahan-kulit.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Mug", "https://store.primagraphia.co.id/wp-content/uploads/2017/10/Mug-polos-.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Produk Paket", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Stationary", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg","test"));
        modelKategoris.add(new Parentmodel("Personal Product", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Jurnal", "https://ilikegap.com/wp-content/uploads/2017/12/Jurnal-Eksklusif-copy-600x600-300x300.jpg","TEST"));
        modelKategoris.add(new Parentmodel("T-Shirt", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg","TEST"));
        modelKategoris.add(new Parentmodel("APD", "https://brandtalk.id/wp-content/uploads/2019/10/kalender-duduk-1-933x675.png","TEST"));
        modelKategoris.add(new Parentmodel("Promotion Tools", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Photobook", "https://store.primagraphia.co.id/wp-content/uploads/2017/10/Mug-polos-.jpg","TEST"));
        modelKategoris.add(new Parentmodel("3D Print", "https://mmc.tirto.id/image/otf/700x0/2018/12/27/ilustrasi-buku--istockphoto_ratio-16x9.jpg","TEST"));
        modelKategoris.add(new Parentmodel("Season Product", "https://ilikegap.com/wp-content/uploads/2017/12/IMG_20160502_102854-600x600.jpg","TEST"));
                                adapterKategori = new AdapterKategori(getActivity(), modelKategoris);
                                list_kategori.setAdapter(adapterKategori);


    }

    private void rene() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 2; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://suma.geloraaksara.co.id/uploads/banner/14.png?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            } else {
                sliderItem.setImageUrl("https://suma.geloraaksara.co.id/uploads/banner/15.png?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }



}