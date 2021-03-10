package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TrendingProductDetails extends AppCompatActivity {
        String tId;
    ImageView pImage;
    ImageButton decrementBtn,incrementBtn,backBtn;
    TextView productTitle,productPrice,productPrice2,productSize,productDescription,quantityNumTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_product_details);

        pImage=findViewById(R.id.productImageId);
        backBtn=findViewById(R.id.backBtn);
        productTitle=findViewById(R.id.productTitleId);
        productPrice=findViewById(R.id.productPriceId);
        productPrice2=findViewById(R.id.productPrice2Id);
        productSize=findViewById(R.id.productSizeId);
        productDescription=findViewById(R.id.productDescriptionId);
        incrementBtn=findViewById(R.id.incrementBtn);
        decrementBtn=findViewById(R.id.decrementBtn);
        quantityNumTv=findViewById(R.id.quantityNumTv);

        tId= getIntent().getStringExtra("tId");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        showTrendingSelectedItem();
    }
    private void showTrendingSelectedItem() {


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("trending");
        reference.child(tId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String image= ""+snapshot.child("p_image").getValue();
                String nameDb =""+ snapshot.child("p_title").getValue();
                String price =""+ snapshot.child("p_price").getValue();
                //     String rating =""+ snapshot.child("p_rating").getValue();
                String pSize =""+ snapshot.child("p_size").getValue();
                String pDesc =""+ snapshot.child("p_description").getValue();

                Picasso.get().load(image).placeholder(R.drawable.shirt).into(pImage);
                productTitle.setText(nameDb);
                productPrice.setText("$"+price);
                productPrice2.setText("$"+price);
                productSize.setText(pSize);
                productDescription.setText(pDesc);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}