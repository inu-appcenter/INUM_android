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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter_ProductMain extends RecyclerView.Adapter<Adapter_ProductMain.ViewHolder>{

    public ArrayList<MainProductResult> mDataset = new ArrayList<>();

  //  public ArrayList<MainProductResult> Dataset_room, Dataset_ticket, Dataset_book = new ArrayList<>();

    public ItemClick itemClick;

    public Adapter_ProductMain() {

    }
    public interface ItemClick{
        public void onClick(View view, int position);
    }

    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public Adapter_ProductMain(ArrayList<MainProductResult> myData) {
        this.mDataset.addAll(myData);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productimg;
        public TextView name,price,subcategory;

        public ViewHolder(View itemView){
            super(itemView);
            productimg = itemView.findViewById(R.id.iv_productcard_image);
            name = itemView.findViewById(R.id.tv_productcard_name);
            price =itemView.findViewById(R.id.tv_productcard_price);
            subcategory = itemView.findViewById(R.id.tv_productcard_childcategory);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ProductMain.ViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getProductName());
        holder.price.setText(mDataset.get(position).getProductPrice() + "");
        String category = mDataset.get(position).getCategory();
        if (category.contains("책")){
            String[] subcategory = category.split("책");
            holder.subcategory.setText(category.split("책")[1]);
        }
        else {
            holder.subcategory.setText("");
        }
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

    // Total number of cells
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
