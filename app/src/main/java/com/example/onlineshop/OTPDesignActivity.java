package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.onlineshop.databinding.ActivityOTPDesignBinding;
import com.google.android.material.textfield.TextInputLayout;

public class OTPDesignActivity extends AppCompatActivity {
    ActivityOTPDesignBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityOTPDesignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTPDesignActivity.this, LoginActivity.class));
            }
        });

        binding.nextBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePhoneNumber() | !validatePassword() | !validateUsername() | !validateAddress()) {
                    return;
                }

                // get data
                String _getUserEnteredPhoneNumber = binding.phoneNumberId.getEditText().getText().toString().trim();
                String _phoneNo = "+" + binding.countryCodeId.getFullNumber() + _getUserEnteredPhoneNumber;
                String fullName=binding.fullNameId.getEditText().getText().toString().trim();
                String address=binding.addressId.getEditText().getText().toString().trim();
                String password = binding.passwordId.getEditText().getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(), VeryOTPActivity.class);
                intent.putExtra("phoneNo", _phoneNo);
                intent.putExtra("originalNumber", _getUserEnteredPhoneNumber);
                intent.putExtra("name", fullName);
                intent.putExtra("address", address);
                intent.putExtra("passWord", password);

                startActivity(intent);
                finish();

            }
        });
    }

    // field validation
    private boolean validatePhoneNumber() {
        String val = binding.phoneNumberId.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            binding.phoneNumberId.setError("Enter valid phone number");
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

    private boolean validatePassword() {
     String  password = binding.passwordId.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (password.isEmpty()) {
            binding.passwordId.setError("Field can not be empty");
            return false;
        }
//        else if (!password.matches(checkPassword)) {
//            binding.passwordId.setError("Password should contain 4 characters!");
//            return false;
//        }
        else {
            binding.passwordId.setError(null);
            binding.passwordId.setErrorEnabled(false);
            return true;
        }


    }

    private boolean validateAddress() {
        String address = binding.addressId.getEditText().getText().toString().trim();
        if (address.isEmpty()) {
            binding.addressId.setError("Address can not be empty");
            return false;
        } else {
            binding.addressId.setError(null);
            binding.addressId.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String fullName = binding.fullNameId.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";

        if (fullName.isEmpty()) {
            binding.fullNameId.setError("Field can not be empty");
            return false;
        } else if (fullName.length() > 20) {
            binding.fullNameId.setError("Username is too large!");
            return false;
        }
//        else if (!fullName.matches(checkspaces)) {
//            binding.fullNameId.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            binding.fullNameId.setError(null);
            binding.fullNameId.setErrorEnabled(false);
            return true;
        }
    }
}