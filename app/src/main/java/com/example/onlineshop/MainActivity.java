package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static final float END_SCALE = 0.7f;
    private ImageView menuBtn;

    RecyclerView catRecycler,trendingRecycler,specialRecycler;
    //Adapter;
    TrendingAdapter trendingAdapter;
    SpecialSellAdapter specialSellAdapter;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout contentView;

  SliderLayout sliderLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        menuBtn=findViewById(R.id.menuBtn);
        catRecycler=findViewById(R.id.categoriesRecyclerId);
       trendingRecycler=findViewById(R.id.trendingRecyclerId);
       specialRecycler=findViewById(R.id.specialSellRecyclerId);


        catRecycler();
        trendingAdapter();
        specialRecycler();


        drawerLayout = findViewById(R.id.drawerLayout_Id);
        navigationView = findViewById(R.id.navigationView_Id);
        contentView= findViewById(R.id.content_Id);


        // image slider
        sliderLayout=findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderView();

      navigationDrawer();


    }

    private void specialRecycler() {
        specialRecycler.setHasFixedSize(true);
        specialRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<SpecialSellHelperClass> specialSellHelperClasses = new ArrayList<>();
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sports,"10% OFF","Sports","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.rainy,"10% OFF","Rainy","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.watch,"10% OFF","Watch","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sp5,"10% OFF","Sports","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sp1,"10% OFF","Sports","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sp3,"10% OFF","Sports","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sp2,"10% OFF","Sports","Original Price:200","Discounted Price:190"));
        specialSellHelperClasses.add(new SpecialSellHelperClass(R.drawable.sp4,"10% OFF","Sports","Original Price:200","Discounted Price:190"));


        specialSellAdapter = new SpecialSellAdapter(this,specialSellHelperClasses);
        specialRecycler.setAdapter(specialSellAdapter);
    }

    private void  trendingAdapter(){
        trendingRecycler.setHasFixedSize(true);
        trendingRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TrendingHelperClass> trendingClasses = new ArrayList<>();

       trendingClasses.add(new TrendingHelperClass(R.drawable.bags, "Bags", "Stylish different kind Bags"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.sunglass, "Sunglasses", "Branded Sunglasses"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.umbrella, "Umbrella", "Different type of exported Umbrella"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.sp4, "Women", "Winter Collection for women"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.jeans, "Jeans", "Jeans Collection For Gents"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.shirt, "Shirt", "Shirt collection for male.cotton only"));
       trendingClasses.add(new TrendingHelperClass(R.drawable.belt, "Belt", "Belt for formal and Informal"));

        trendingAdapter = new TrendingAdapter(this,trendingClasses);
        trendingRecycler.setAdapter(trendingAdapter);

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void catRecycler() {
        catRecycler.setHasFixedSize(true);
        catRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<Integer> catList=new ArrayList<>();

        catList.add(R.drawable.sp1);
        catList.add(R.drawable.sp2);
        catList.add(R.drawable.sp3);
        catList.add(R.drawable.sp4);
        catList.add(R.drawable.sp5);
        catList.add(R.drawable.babycollection);
        catList.add(R.drawable.bags);
        catList.add(R.drawable.belt);
        catList.add(R.drawable.jeans);
        catList.add(R.drawable.kids);
        catList.add(R.drawable.rainy);
        catList.add(R.drawable.shirt);
        catList.add(R.drawable.sunglass);
        catList.add(R.drawable.umbrella);
        catList.add(R.drawable.watch);
        CatAdapter catAdapter =new CatAdapter(this,catList);
        catRecycler.setAdapter(catAdapter);

    }


    private void setSliderView() {
        for (int i=0;i<=4;i++){
            DefaultSliderView defaultSliderView=new DefaultSliderView(this);
            switch (i){
                case 0:
                    defaultSliderView.setImageDrawable(R.drawable.sp1);
                    defaultSliderView.setDescription("Bags and Cloths");
                    break;
                    case 1:
                    defaultSliderView.setImageDrawable(R.drawable.sp2);
                        defaultSliderView.setDescription("Happy Shopping");
                    break;
                    case 2:
                    defaultSliderView.setImageDrawable(R.drawable.sp3);
                        defaultSliderView.setDescription("Men Category");
                    break;
                    case 3:
                    defaultSliderView.setImageDrawable(R.drawable.sp4);
                        defaultSliderView.setDescription("All Products");
                    break;
                    case 4:
                    defaultSliderView.setImageDrawable(R.drawable.sp5);
                        defaultSliderView.setDescription("Women Category");
                    break;
            }

            defaultSliderView.setDescriptionTextSize(16);
            defaultSliderView.setDescriptionTextColor(R.color.white);
            defaultSliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
           // defaultSliderView.setDescription("This is "+ (i+1));
            final  int finalI=i;
            defaultSliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                   // Toast.makeText(MainActivity.this,"This is slider"+(finalI+1),Toast.LENGTH_SHORT).show();
                }
            });
            // add slider to layout
            sliderLayout.addSliderView(defaultSliderView);
        }
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navCategories);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }

    private void animateNavigationDrawer(){
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorYellow));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navCategories:
                startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
                break;

        }
        return true;
    }
}