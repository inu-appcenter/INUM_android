package org.gowoon.inum.recycler;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class AdapterRecyclerUploadImage extends RecyclerView.Adapter<AdapterRecyclerUploadImage.ListViewHolder>{
//    private ArrayList<ItemImageList> data = new ArrayList<>(8);
    private ArrayList<Uri> data = new ArrayList<>(8);
    private Object mContext;
    public AdapterRecyclerUploadImage(){}

    public AdapterRecyclerUploadImage( Context context) {
//        this.data = myData;
        this.mContext = context;
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

//        final ItemImageList iL = data.get(position);
//        final ListViewHolder listholder = (ListViewHolder) holder;

        float mScale = holder.uploadImage.getResources().getDisplayMetrics().density;

        Glide.with((Context) mContext).asBitmap()
                .load(data.get(position))
                .apply(new RequestOptions().override(191,191)
                        .centerCrop()
                        .bitmapTransform(new RoundedCorners((int) (mScale*6.9))))
                .into(holder.uploadImage);

//        Glide.with(holder.uploadImage).load(data.get(0))
//                .apply(new RequestOptions().override(191,191).centerCrop())
//                .apply(new RequestOptions().bitmapTransform(new RoundedCorners((int) (mScale*6.9))))
//                .into(holder.uploadImage);
//        holder.uploadImage.setImageURI(iL.getImageUri());
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
            uploadImage = itemView.findViewById(R.id.iv_item_upload_image);
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
    }

    public ItemClick itemClick;

    public interface ItemClick{
        void onClick(View view, int position);
    }

    public void setItemClick(AdapterRecyclerUploadImage.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public void addItem(Uri Data){
//        data.add(Data);
        data.add(Data);
        notifyDataSetChanged();
    }
}
