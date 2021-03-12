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

public class SpecialSellAdapter extends RecyclerView.Adapter<SpecialSellAdapter.SpecialSellHolderClass> {

    Context context;
    ArrayList<SpecialSellHelperClass> sSellList=new ArrayList<>();

    public SpecialSellAdapter(Context context, ArrayList<SpecialSellHelperClass> sSellList) {
        this.context = context;
        this.sSellList = sSellList;
    }

    @NonNull
    @Override
    public SpecialSellHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.special_sell_layout,parent,false);
        return new SpecialSellHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialSellHolderClass holder, int position) {
        SpecialSellHelperClass specialSellHelperClass=sSellList.get(position);

        Glide.with(context).load(sSellList.get(position).getImage()).into(holder.imageView);

    holder.title.setText(specialSellHelperClass.getTitle());
    holder.discountNote.setText(specialSellHelperClass.getDiscountNote());
    holder.originalPrice.setText(specialSellHelperClass.getOriginalPrice());
    holder.discountedPrice.setText(specialSellHelperClass.getDiscountedPrice());

    final String id=specialSellHelperClass.getId();

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,SpecialSellProductDetails.class);
            intent.putExtra("sId",id);
            context.startActivity(intent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return sSellList.size();
    }

    public class SpecialSellHolderClass extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView discountNote,title,originalPrice,discountedPrice;
        public SpecialSellHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.productIconIv_Id);
            discountNote=itemView.findViewById(R.id.discountNoteTv_Id);
            title=itemView.findViewById(R.id.titleTv_Id);
            originalPrice=itemView.findViewById(R.id.originalPriceTv_Id);
           discountedPrice=itemView.findViewById(R.id.discountedPriceTv_Id);
        }
    }
}
