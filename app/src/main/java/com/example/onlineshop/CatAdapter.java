package com.example.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatHolderClass> {
    Context context;
    ArrayList<Integer> catList=new ArrayList<>();

    public CatAdapter(Context context, ArrayList<Integer> catList) {
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
        holder.imageView.setImageResource(catList.get(position));

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public  class  CatHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        public CatHolderClass(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.imageSp1_Id);
        }
    }
}
