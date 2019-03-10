package org.gowoon.inum.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.gowoon.inum.R;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.util.Config;

import java.util.ArrayList;

public class Adapter_recycler_ProductSearch extends RecyclerView.Adapter<Adapter_recycler_ProductSearch.ViewHolder>{

    public ArrayList<MainProductResult> mDataset = new ArrayList<>();
    public ItemClick itemClick;
    public interface ItemClick{
        public void onClick(View view, int position);
    }

    public void setItemClick(Adapter_recycler_ProductSearch.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public Adapter_recycler_ProductSearch(){

    }
    public Adapter_recycler_ProductSearch(ArrayList<MainProductResult> mDataset) {
        this.mDataset.addAll(mDataset);
    }


    @NonNull
    @Override
    public Adapter_recycler_ProductSearch.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_recycler_ProductSearch.ViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getProductName());
        holder.price.setText(mDataset.get(position).getProductPrice() + "");
        Glide.with(holder.productimg).load(Config.serverUrl + "imgload/" + mDataset.get(position).getProductImg().get(0)).into(holder.productimg);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (itemClick != null){
                    itemClick.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView productimg;
        public TextView name, price;
        public String productid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productimg = itemView.findViewById(R.id.iv_productcard2_image);
            name = itemView.findViewById(R.id.tv_productcard2_name);
            price = itemView.findViewById(R.id.tv_productcard2_price);
        }
    }

}
