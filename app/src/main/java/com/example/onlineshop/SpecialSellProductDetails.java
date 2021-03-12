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

public class SpecialSellProductDetails extends AppCompatActivity {
    String sId;
    ImageView pImage;
    ImageButton decrementBtn, incrementBtn, backBtn;
    TextView productTitle, productPrice, productPrice2, productSize, productDescription, quantityNumTv, originalPriceTv, afterDiscountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_sell_product_details);

        pImage = findViewById(R.id.productImageId);
        backBtn = findViewById(R.id.backBtn);
        productTitle = findViewById(R.id.productTitleId);
        productPrice = findViewById(R.id.productPriceId);
        productPrice2 = findViewById(R.id.productPrice2Id);
        productSize = findViewById(R.id.productSizeId);

        originalPriceTv = findViewById(R.id.originalPriceTv_Id);
        afterDiscountTv = findViewById(R.id.discountedPriceTv_Id);

        productDescription = findViewById(R.id.productDescriptionId);
        incrementBtn = findViewById(R.id.incrementBtn);
        decrementBtn = findViewById(R.id.decrementBtn);
        quantityNumTv = findViewById(R.id.quantityNumTv);

        sId = getIntent().getStringExtra("sId");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        showSelectedItem();
    }

    private void showSelectedItem() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("special");
        reference.child(sId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String image = "" + snapshot.child("image").getValue();
                String nameDb = "" + snapshot.child("name").getValue();
                String originalPrice = "" + snapshot.child("originalPrice").getValue();
                String afterDiscountPrice = "" + snapshot.child("afterDiscount").getValue();
                String pSize = "" + snapshot.child("size").getValue();
                String pDesc = "" + snapshot.child("description").getValue();

                Picasso.get().load(image).placeholder(R.drawable.shirt).into(pImage);
                productTitle.setText(nameDb);

                originalPriceTv.setText("OriginalPrice:" + originalPrice + "Tk");
                afterDiscountTv.setText("AfterDiscountPrice" + afterDiscountPrice + "Tk");
                productPrice2.setText(afterDiscountPrice + "Tk");
                productSize.setText(pSize);
                productDescription.setText(pDesc);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
