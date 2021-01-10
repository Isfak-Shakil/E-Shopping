package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static final float END_SCALE = 0.7f;
    private ImageView menuBtn;

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

        drawerLayout = findViewById(R.id.drawerLayout_Id);
        navigationView = findViewById(R.id.navigationView_Id);
        contentView= findViewById(R.id.content_Id);

        sliderLayout=findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);
        setSliderView();

      navigationDrawer();


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
            defaultSliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            defaultSliderView.setDescriptionTextColor(R.color.colorYellow);
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
        return true;
    }
}