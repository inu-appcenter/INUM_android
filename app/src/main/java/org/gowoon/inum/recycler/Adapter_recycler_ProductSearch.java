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

    public ArrayList<MainProductResult> mData = new ArrayList<>();
    private ItemClick itemClick;

    public Adapter_recycler_ProductSearch(){

    }
    public Adapter_recycler_ProductSearch(ArrayList<MainProductResult> myData) {
        this.mData = myData;
    }

    class mViewHolder extends RecyclerView.ViewHolder{
        ImageView productImg;
        TextView name, price;

        mViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.iv_productcard2_image);
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

        holder.name.setText(mData.get(position).getProductName());
        holder.price.setText(mData.get(position).getProductPrice() + "원");

        if (mData.get(position).getProductImg()!=null){
            Glide.with(holder.productImg).load(Config.serverUrl + "imgload/"
                    + mData.get(position).getSellerId()
                    + mData.get(position).getFileFolder() + "/"
                    + mData.get(position).getProductImg().get(0))
                    .into(holder.productImg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (itemClick != null){
                    itemClick.onClick(v,position);
                }
            }
        });
    }

    public interface ItemClick{
        void onClick(View view, int position);
    }

    public void setItemClick(Adapter_recycler_ProductSearch.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
