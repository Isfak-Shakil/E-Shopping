package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlineshop.databinding.ActivityLoginBinding;
import com.example.onlineshop.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    String mNumber, password;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mNumber = binding.phoneId.getEditText().getText().toString().trim();
        password = binding.passwordId.getEditText().getText().toString().trim();
//check wheather phone and password already saved or not
        SessionManager sessionManager=new SessionManager(LoginActivity.this,SessionManager.SESSION_REMEMBERME);
        if (sessionManager.checkRememberMe()){
            HashMap<String,String> rememberMeDetails=sessionManager.getRememberMeDetailsFromSession();
            binding.phoneEt.setText(rememberMeDetails.get(SessionManager.KEW_SESSIONPHONENUMBER));
            binding.passWordEt.setText(rememberMeDetails.get(SessionManager.KEW_SESSIONPASSWORD));
        }

        binding.loginBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (   !validatePhoneNumber() | !validatePassword()) {
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser();
                }
            }
        });


        //   binding.lottie.animate().translationY(-300).setDuration(2000).setStartDelay(4000);
        binding.singUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent= new Intent (LoginActivity.this,SignUpActivity.class);
                Intent intent = new Intent(LoginActivity.this, OTPDesignActivity.class);
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



    private void loginUser() {
        if (!isConnected(this)){
            showCustomDialog();
        }
        if (binding.rememberMe.isChecked()){
            SessionManager sessionManager=new SessionManager(LoginActivity.this,SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession(mNumber,password);
        }


        Query checkUser = FirebaseDatabase.getInstance().getReference("users").orderByChild("phone").equalTo(mNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    binding.phoneId.setError(null);
                    binding.phoneId.setErrorEnabled(false);

                    String passwordDb = snapshot.child(mNumber).child("password").getValue(String.class);
                    if (passwordDb.equals(password)) {
                        binding.passwordId.setError(null);
                        binding.passwordId.setErrorEnabled(false);
                        // getting all data
                        String nameDb=snapshot.child(mNumber).child("name").getValue(String.class);
                        String addressDb=snapshot.child(mNumber).child("address").getValue(String.class);
                        String phone=snapshot.child(mNumber).child("phone").getValue(String.class);
                        SessionManager sessionManager=new SessionManager(LoginActivity.this,SessionManager.SESSION_USERSESSION);
                        sessionManager.createUserLoginSession(mNumber,nameDb,addressDb,phone);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();


                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No User exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isConnected(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager= (ConnectivityManager) loginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi !=null && wifi.isConnected()|| mobile!=null&&mobile.isConnected()){
            return true;
        }
        else {
            return false;
        }
    }
    private void showCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        // Setting Alert Dialog Title
        builder.setMessage("Please connect to the wifi or mobile data to proceed further.");
        builder.setCancelable(false);
        builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    private boolean validatePhoneNumber() {
        String val = binding.phoneId.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            binding.phoneId.setError("Enter valid phone number");
            return false;
        }

//        else if (!val.matches(checkspaces)) {
//            binding.phoneId.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            binding.phoneId.setError(null);
            binding.phoneId.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = binding.passwordId.getEditText().getText().toString().trim();
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
}