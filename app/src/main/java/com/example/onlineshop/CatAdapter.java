package com.example.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatHolderClass> {
    Context context;
    ArrayList<ImageHelperClass> catList;

    public CatAdapter(Context context, ArrayList<ImageHelperClass> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CatHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_design,parent,false);
        return new CatHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatHolderClass holder, int position) {

        Glide.with(context).load(catList.get(position).getImageUrl()).into(holder.imageView);

       ImageHelperClass helperClass=catList.get(position);
        holder.textView.setText(helperClass.getName());
        String name=helperClass.getName();
        final   String catId=helperClass.getCatId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",catId);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public  class  CatHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public CatHolderClass(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.imageSp1_Id);
        textView=itemView.findViewById(R.id.textView);


        }
    }
}
