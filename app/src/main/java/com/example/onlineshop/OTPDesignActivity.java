package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlineshop.databinding.ActivityOTPDesignBinding;

public class OTPDesignActivity extends AppCompatActivity {
    ActivityOTPDesignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityOTPDesignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhoneNumber()) {
                    return;
                }

                // get data
                String  _getUserEnteredPhoneNumber=binding.phoneNumberId.getEditText().getText().toString().trim();

               String _phoneNo="+"+binding.countryCodeId.getFullNumber()+_getUserEnteredPhoneNumber;

                Intent intent=new Intent(getApplicationContext(),VeryOTPActivity.class);
              intent.putExtra("phoneNo",_phoneNo);

                startActivity(intent);
                finish();

            }
        });
    }
    private boolean validatePhoneNumber() {
        String val =binding.phoneNumberId.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            binding.phoneNumberId .setError("Enter valid phone number");
            return false;
        }
//        else if (!val.matches(checkspaces)) {
//            binding.phoneNumberId.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            binding.phoneNumberId.setError(null);
            binding.phoneNumberId.setErrorEnabled(false);
            return true;
        }
    }
}