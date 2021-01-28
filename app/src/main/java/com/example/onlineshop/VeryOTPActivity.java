package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.onlineshop.databinding.ActivityVeryOTPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VeryOTPActivity extends AppCompatActivity {
    ActivityVeryOTPBinding binding;

    ProgressBar progressBar;
    String codeBySystem;

    FirebaseAuth mAuth;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    String number,name,address,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityVeryOTPBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);


        mAuth = FirebaseAuth.getInstance();
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");

        // get data from intent
        number = getIntent().getStringExtra("originalNumber");
        name = getIntent().getStringExtra("name");
        address = getIntent().getStringExtra("address");
        password = getIntent().getStringExtra("passWord");



        // phone verification
        String phoneNo = getIntent().getStringExtra("phoneNo");
        sendVerificationCodeToUser(phoneNo);


        binding.closeIconId.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VeryOTPActivity.this);
                // Setting Alert Dialog Title
                builder.setTitle("Confirm Exit..!!!");
                // Icon Of Alert Dialog
                builder.setIcon(R.drawable.ques);
                builder.setMessage("Are you sure,You want to exit");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(), OTPDesignActivity.class));
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();

            }
        });


        sendVerificationCodeToUser(phoneNo);
        binding.verifyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = binding.pinFromUserId.getText().toString();
                if (!code.isEmpty()) {
                    verifyCode(code);

                }


            }
        });
    }


    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)   // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        //     binding.pinFromUserId.setText(code);
                        verifyCode(code);
                    }

                    //  Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                    signInUsingCredential(phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(VeryOTPActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInUsingCredential(credential);
    }

    private void signInUsingCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.VISIBLE);
                          //  registerUser();

                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("name",name);
                            hashMap.put("phone",number);
                            hashMap.put("address",address);
                            hashMap.put("password",password);

                            reference.child(number).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            });


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(VeryOTPActivity.this, "Error ! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
//    private void registerUser(){
//        HashMap<String,Object> hashMap=new HashMap<>();
//        hashMap.put("name",name);
//        hashMap.put("phone",number);
//        hashMap.put("address",address);
//        hashMap.put("Password",password);
//
//        reference.child(number).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                progressBar.setVisibility(View.GONE);
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });
//    }
}