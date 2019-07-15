package org.gowoon.inum.recycler;

import android.content.Context;
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

public class AdapterRecyclerUploadImage extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ItemImageList> data = new ArrayList<>(8);
    LayoutInflater inflater;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_upload_recyclerview_image,parent,false);
        ListViewHolder imageholder = new ListViewHolder(view);
        return imageholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.d("viewHolder", String.valueOf(position));
        final ItemImageList iL = data.get(position);
        final ListViewHolder listholder = (ListViewHolder) holder;
        listholder.uploadImage.setImageURI(iL.getImageUri());

    }

    private class ListViewHolder extends RecyclerView.ViewHolder{
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

    public void addItem(ItemImageList Data){
        data.add(Data);
        notifyDataSetChanged();
    }
}
