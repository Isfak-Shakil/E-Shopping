package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.onlineshop.databinding.ActivityMainBinding;
import com.example.onlineshop.databinding.ActivityProductBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    static String catId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding =  ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        String title=getIntent().getStringExtra("name");
        catId=getIntent().getStringExtra("id");


        binding.titleText.setText(title);
        showProduct();

binding.backBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});


    }

    private void showProduct() {
        binding.recyclerId.setHasFixedSize(false);
        binding.recyclerId.setLayoutManager(new GridLayoutManager(this, 2));


        ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("categories").child(catId);
        reference.child("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModelArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ProductModel productModel = new ProductModel();
                     productModel.setP_id(Objects.requireNonNull(ds.child("p_id").getValue()).toString());

                     productModel.setP_image(Objects.requireNonNull(ds.child("p_image").getValue()).toString());
                    productModel.setP_name(Objects.requireNonNull(ds.child("p_name").getValue()).toString());
                    productModel.setP_price(Objects.requireNonNull(ds.child("p_price").getValue()).toString());
                    productModel.setP_rating(Objects.requireNonNull(ds.child("p_rating").getValue()).toString());
                    productModelArrayList.add(productModel);

                }
                //setup adapter
                ProductAdapter productAdapter = new ProductAdapter(ProductActivity.this,productModelArrayList);
                //set adapter
             binding.recyclerId.setAdapter(productAdapter);
              productAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}