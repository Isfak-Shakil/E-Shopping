package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView catLayoutRecycler;
    CategoryLayoutAdapter categoryLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        catLayoutRecycler=findViewById(R.id.catLayoutRecyclerId);
        catRecycler();
    }
    private void catRecycler() {
        catLayoutRecycler.setHasFixedSize(true);
        catLayoutRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CatLayoutHelperClass> catList=new ArrayList<>();

       catList.add(new CatLayoutHelperClass("Kids Collection","20" ));
       catList.add(new CatLayoutHelperClass("Men Collection","30" ));
       catList.add(new CatLayoutHelperClass("Women Collection","50" ));
       catList.add(new CatLayoutHelperClass("Winter Collection","20" ));
       catList.add(new CatLayoutHelperClass("Summer Collection","20" ));
       catList.add(new CatLayoutHelperClass("Rainy Collection","10" ));
       catList.add(new CatLayoutHelperClass("Belt Collection","20" ));
       catList.add(new CatLayoutHelperClass("Watch Collection","10" ));
       catList.add(new CatLayoutHelperClass("Umbrella Collection","20" ));
       catList.add(new CatLayoutHelperClass("Random Collection","20"));
       categoryLayoutAdapter=new CategoryLayoutAdapter(this,catList);
        catLayoutRecycler.setAdapter(categoryLayoutAdapter);

    }
}