package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo,appName,splashImage;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo=findViewById(R.id.logo);
        appName=findViewById(R.id.appName);
      //  splashImage=findViewById(R.id.imageV);
        lottieAnimationView=findViewById(R.id.lottie);


    //    splashImage.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);

        appName.animate().translationY(1000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(2400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-600).setDuration(1000).setStartDelay(4000);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
    }
},5000);


    }

}