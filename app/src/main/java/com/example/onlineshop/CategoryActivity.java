package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView catLayoutRecycler;
    CategoryLayoutAdapter categoryLayoutAdapter;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category);
        catLayoutRecycler=findViewById(R.id.catLayoutRecyclerId);
        backBtn=findViewById(R.id.backBtn);

        catRecycler();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void catRecycler() {
        catLayoutRecycler.setHasFixedSize(true);
        catLayoutRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CatLayoutHelperClass> catList=new ArrayList<>();


        final DatabaseReference reference=FirebaseDatabase.getInstance().getReference("categories");
        reference.orderByChild("cat_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            catList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    String id=""+ds.child("cat_id").getValue().toString();
                    String item=""+ds.child("items").getValue().toString();
                    String catName=""+ds.child("cat_name").getValue().toString();
                    String image=""+ds.child("cat_image").getValue().toString();

                    CatLayoutHelperClass helperClass=new CatLayoutHelperClass();
                    helperClass.setCatId(id);
                    helperClass.setTitle(catName);
                    helperClass.setItemCount(item);
                    helperClass.setImageUrl(image);

                    catList.add(helperClass);
                }
                //setup adapter
                categoryLayoutAdapter=new CategoryLayoutAdapter(CategoryActivity.this,catList);
                //set adapter
                catLayoutRecycler.setAdapter(categoryLayoutAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}