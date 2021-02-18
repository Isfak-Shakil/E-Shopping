package com.example.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.holder>{
        private Context context;
        private ArrayList<ProductModel> productModelArrayList=new ArrayList<>();

    public ProductAdapter(Context context, ArrayList<ProductModel> productModelArrayList) {
        this.context = context;
        this.productModelArrayList = productModelArrayList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new ProductAdapter.holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        Glide.with(context).load(productModelArrayList.get(position).getP_image()).into(holder.pImage);
        ProductModel productModel=productModelArrayList.get(position);

        holder.pTitle.setText(productModel.getP_name());
        holder.pRating.setRating(Float.parseFloat(productModel.getP_rating()));
        holder.pPrice.setText(productModel.getP_price()+"tk");
    }

    @Override
    public int getItemCount() {
        return productModelArrayList.size();
    }

    public  class holder extends RecyclerView.ViewHolder {
        ImageView pImage;
        TextView pTitle,pPriceName,pPrice;
        RatingBar pRating;

        public holder(@NonNull View itemView) {
            super(itemView);
            pImage=itemView.findViewById(R.id.pImageId);
            pTitle=itemView.findViewById(R.id.pTitleId);
            pPriceName=itemView.findViewById(R.id.pPriceNameId);
            pPrice=itemView.findViewById(R.id.pPriceId);
            pRating=itemView.findViewById(R.id.pRatingId);
        }
    }
}
