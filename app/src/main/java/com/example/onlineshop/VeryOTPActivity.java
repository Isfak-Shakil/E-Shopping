package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlineshop.databinding.ActivityVeryOTPBinding;

public class VeryOTPActivity extends AppCompatActivity {
       ActivityVeryOTPBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityVeryOTPBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.verifyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VeryOTPActivity.this,MainActivity.class));
            }
        });
    }
}