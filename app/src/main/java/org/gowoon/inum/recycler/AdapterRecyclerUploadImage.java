package org.gowoon.inum.recycler;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.gowoon.inum.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerUploadImage extends RecyclerView.Adapter<AdapterRecyclerUploadImage.ViewHolder>{
//    private ArrayList<ItemImageList> data = new ArrayList<>(8);
    private ArrayList<Uri> mData = new ArrayList<>();
    public AdapterRecyclerUploadImage(){}
    public ItemClick itemClick;

    public interface ItemClick{
        void onClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image, addImage;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_item_upload_image);
            addImage = itemView.findViewById(R.id.iv_item_upload_image_plus);
        }
    }

    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public AdapterRecyclerUploadImage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_upload_recyclerview_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerUploadImage.ViewHolder holder, final int position) {
        float mScale = holder.image.getResources().getDisplayMetrics().density;
        if (mData.size()==9){
//            notifyItemRemoved(0);
            mData.remove(9);
            notifyItemRemoved(9);
            notifyItemRangeChanged(9, mData.size());
        }
        else{
            if (position==mData.size()-1){
                holder.addImage.setVisibility(View.VISIBLE);
            }
            else {
                holder.addImage.setVisibility(View.INVISIBLE);
            }

        }

        Glide.with(holder.image).load(mData.get(position))
                .apply(new RequestOptions().override(191,191).centerCrop())
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners((int) (mScale*6.9))))
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClick!=null){
                    itemClick.onClick(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addItem(List<? extends Uri> item){
        mData.addAll(0,item);
        notifyDataSetChanged();
    }

    public void popAddButton(){
        mData.remove(0);
    }
}
