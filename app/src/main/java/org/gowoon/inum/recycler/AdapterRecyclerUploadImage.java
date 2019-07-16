package org.gowoon.inum.recycler;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemImageList;

import java.util.ArrayList;

public class AdapterRecyclerUploadImage extends RecyclerView.Adapter<AdapterRecyclerUploadImage.ListViewHolder>{
    private ArrayList<ItemImageList> data = new ArrayList<>(8);

    public AdapterRecyclerUploadImage(){}

    public AdapterRecyclerUploadImage(ArrayList<ItemImageList> myData) {
        this.data = myData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_upload_recyclerview_image,parent,false);
//        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.item_upload_recyclerview_image,parent,false);
//        ListViewHolder imageHolder = new ListViewHolder(view);
        return new AdapterRecyclerUploadImage.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerUploadImage.ListViewHolder holder, final int position) {
        Log.d("viewHolder", String.valueOf(position));

        final ItemImageList iL = data.get(position);
//        final ListViewHolder listholder = (ListViewHolder) holder;
        holder.uploadImage.setImageURI(iL.getImageUri());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (itemClick != null){
                    itemClick.onClick(v,position);
                }
            }
        });
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        public ImageView uploadImage;

        public ListViewHolder(View itemView){
            super(itemView);
            uploadImage = (ImageView) itemView.findViewById(R.id.iv_item_upload_image);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clearItem() {
        for(int i=0; i<data.size();i++){
            notifyItemRemoved(i);
        }
        data = new ArrayList<>();
    }

    public ItemClick itemClick;

    public interface ItemClick{
        void onClick(View view, int position);
    }

    public void setItemClick(AdapterRecyclerUploadImage.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public void addItem(ItemImageList Data){
        data.add(Data);
        notifyDataSetChanged();
    }
}
