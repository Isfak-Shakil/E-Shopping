package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlineshop.databinding.ActivityLoginBinding;
import com.example.onlineshop.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



     //   binding.lottie.animate().translationY(-300).setDuration(2000).setStartDelay(4000);
        binding.singUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
//                Pair[] pairs=new Pair[7];
//                pairs[0]=new Pair<View,String>(binding.lottie,"logo_image");
//                pairs[1]=new Pair<View,String>(binding.mainTextId,"main_text");
//                pairs[2]=new Pair<View,String>(binding.sloganTextId,"slogan_text");
//                pairs[3]=new Pair<View,String>(binding.phoneId,"username_text");
//                pairs[4]=new Pair<View,String>(binding.passwordId,"password_text");
//                pairs[5]=new Pair<View,String>(binding.loginBtnId,"login_text");
//                pairs[6]=new Pair<View,String>(binding.singUpId,"signUp_text");
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
//                    startActivity(intent, options.toBundle());
//                    finish();
//                }

            }
        });
    }
}