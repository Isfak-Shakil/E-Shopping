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

import java.util.ArrayList;

public class CategoryLayoutAdapter extends RecyclerView.Adapter<CategoryLayoutAdapter.CategoryLayoutHolder> {

    Context context;
    ArrayList<CatLayoutHelperClass> catLayoutList=new ArrayList<>();

    public CategoryLayoutAdapter(Context context, ArrayList<CatLayoutHelperClass> catLayoutList) {
        this.context = context;
        this.catLayoutList = catLayoutList;
    }

    @NonNull
    @Override
    public CategoryLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout,parent,false);
        return new CategoryLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryLayoutHolder holder, int position) {
        Glide.with(context).load(catLayoutList.get(position).getImageUrl()).into(holder.imageView);
        CatLayoutHelperClass catLayoutHelperClass=catLayoutList.get(position);

        holder.titleCatTv.setText(catLayoutHelperClass.getTitle());
       holder.itemCountTv.setText(catLayoutHelperClass.getItemCount());

        String name=catLayoutHelperClass.getTitle();
      final   String catId=catLayoutHelperClass.getCatId();

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
        return catLayoutList.size();
    }

    public  class CategoryLayoutHolder extends RecyclerView.ViewHolder {
        TextView titleCatTv,itemCountTv;
        ImageView imageView,rightArrow;
        public CategoryLayoutHolder(@NonNull View itemView) {
            super(itemView);
            titleCatTv=itemView.findViewById(R.id.titleCatId);
            itemCountTv=itemView.findViewById(R.id.itemCatId);
            imageView=itemView.findViewById(R.id.imageSp1);
            //rightArrow=itemView.findViewById(R.id.right_arrowCatId);
        }
    }
}
