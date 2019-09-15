package org.gowoon.inum.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class Adapter_recycler_ProductSearch extends RecyclerView.Adapter<Adapter_recycler_ProductSearch.mViewHolder>{

    public ArrayList<MainProductResult> mDataset = new ArrayList<>();

    public Adapter_recycler_ProductSearch(){

    }
    public Adapter_recycler_ProductSearch(ArrayList<MainProductResult> myData) {
        this.mDataset = myData;
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        public ImageView productimg;
        public TextView name, price;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            productimg = itemView.findViewById(R.id.iv_productcard2_image);
            name = itemView.findViewById(R.id.tv_productcard2_name);
            price = itemView.findViewById(R.id.tv_productcard2_price);
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_product,parent,false);
        return new Adapter_recycler_ProductSearch.mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_recycler_ProductSearch.mViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getProductName());
        holder.price.setText(mDataset.get(position).getProductPrice() + "원");
        Glide.with(holder.productimg).load(Config.serverUrl + "imgload/"
                + mDataset.get(position).getSellerId()
                + mDataset.get(position).getFileFolder()+"/"
                + mDataset.get(position).getProductImg().get(0)).into(holder.productimg);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (itemClick != null){
                    itemClick.onClick(v,position);
                }
            }
        });
    }
        public ItemClick itemClick;

    public interface ItemClick{
        void onClick(View view, int position);
    }

    public void setItemClick(Adapter_recycler_ProductSearch.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItemSet(ArrayList<MainProductResult> myData){
        this.mDataset = myData;
    }
}
