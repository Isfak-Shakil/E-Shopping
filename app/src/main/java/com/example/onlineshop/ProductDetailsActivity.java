package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.onlineshop.databinding.ActivityProductDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
 String pId;
        ImageView pImage;
        ImageButton decrementBtn,incrementBtn;
        TextView productTitle,productPrice,productPrice2,productSize,productDescription,quantityNumTv;
        RatingBar ratingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        pImage=findViewById(R.id.productImageId);
        productTitle=findViewById(R.id.productTitleId);
        productPrice=findViewById(R.id.productPriceId);
        productPrice2=findViewById(R.id.productPrice2Id);
        productSize=findViewById(R.id.productSizeId);
        productDescription=findViewById(R.id.productDescriptionId);
        incrementBtn=findViewById(R.id.incrementBtn);
        decrementBtn=findViewById(R.id.decrementBtn);
        quantityNumTv=findViewById(R.id.quantityNumTv);
        ratingBar=findViewById(R.id.ratingBarId);



        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pId= getIntent().getStringExtra("id");

        showSelectedItem();




    }

    private void showSelectedItem() {

    DatabaseReference  reference= FirebaseDatabase.getInstance().getReference("categories").child(ProductActivity.catId).child("products").child(pId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String image= ""+snapshot.child("p_image").getValue();
                    String nameDb =""+ snapshot.child("p_name").getValue();
                    String price =""+ snapshot.child("p_price").getValue();
                  String rating =""+ snapshot.child("p_rating").getValue();
                    String pSize =""+ snapshot.child("p_size").getValue();
                    String pDesc =""+ snapshot.child("p_description").getValue();

                    Picasso.get().load(image).placeholder(R.drawable.shirt).into(pImage);
                    productTitle.setText(nameDb);
                    productPrice.setText("$"+price);
                    productPrice2.setText("$"+price);
                    productSize.setText(pSize);
                    productDescription.setText(pDesc);
                    ratingBar.setRating(Float.parseFloat(rating));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}