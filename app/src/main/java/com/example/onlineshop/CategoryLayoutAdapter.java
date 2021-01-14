package com.example.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        CatLayoutHelperClass catLayoutHelperClass=catLayoutList.get(position);

        holder.titleTv.setText(catLayoutHelperClass.getTitle());
        holder.itemCountTv.setText(catLayoutHelperClass.getItemCount());
        holder.rightArrow.setImageResource(R.drawable.arrow_right);

    }

    @Override
    public int getItemCount() {
        return catLayoutList.size();
    }

    public  class CategoryLayoutHolder extends RecyclerView.ViewHolder {
        TextView titleTv,itemCountTv;
        ImageView rightArrow;
        public CategoryLayoutHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.titleCatId);
            itemCountTv=itemView.findViewById(R.id.itemCatId);
            rightArrow=itemView.findViewById(R.id.right_arrowCatId);
        }
    }
}
