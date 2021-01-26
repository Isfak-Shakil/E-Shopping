package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo,appName,splashImage;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        appName=findViewById(R.id.appName);
        lottieAnimationView=findViewById(R.id.lottie);



        appName.animate().translationY(1000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-300).setDuration(2000).setStartDelay(4000);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {

       // startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        startActivity(new Intent(SplashActivity.this,OTPDesignActivity.class));
        finish();
    }
},5000);


    }

}