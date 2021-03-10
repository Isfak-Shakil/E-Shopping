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

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingHolderClass>{

    Context context;
    ArrayList<TrendingHelperClass> trendingList=new ArrayList<>();

    public TrendingAdapter(Context context, ArrayList<TrendingHelperClass> trendingList) {
        this.context = context;
        this.trendingList = trendingList;
    }

    @NonNull
    @Override
    public TrendingHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_layout,parent,false);
        return new TrendingHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingHolderClass holder, int position) {
        Glide.with(context).load(trendingList.get(position).getImage()).into(holder.image);

        TrendingHelperClass trendingHelperClass=trendingList.get(position);


        holder.title.setText(trendingHelperClass.getTitle());
        holder.description.setText(trendingHelperClass.getDescription());
        holder.trendingPrice.setText(trendingHelperClass.trendingPrice);

       final String id=trendingHelperClass.getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,TrendingProductDetails.class);
                intent.putExtra("tId",id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return trendingList.size();
    }

    public  class TrendingHolderClass extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title,description,trendingPrice;
        public TrendingHolderClass(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.trending_imageId);
            title=itemView.findViewById(R.id.trending_titleId);
            description=itemView.findViewById(R.id.trending_descriptionId);
            trendingPrice=itemView.findViewById(R.id.trendingPriceId);
        }
    }
}
