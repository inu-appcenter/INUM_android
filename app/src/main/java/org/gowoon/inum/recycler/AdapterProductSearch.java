package org.gowoon.inum.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.gowoon.inum.R;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.util.Config;

import java.util.ArrayList;

public class AdapterProductSearch extends RecyclerView.Adapter<AdapterProductSearch.mViewHolder>{

    public ArrayList<MainProductResult> mData = new ArrayList<>();
    private ItemClick itemClick;

    public AdapterProductSearch(){

    }
    public AdapterProductSearch(ArrayList<MainProductResult> myData) {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_product,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth()*0.25);
        view.setLayoutParams(layoutParams);

        return new AdapterProductSearch.mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductSearch.mViewHolder holder, final int position) {
        float mScale = holder.productImg.getResources().getDisplayMetrics().density;
        holder.name.setText(mData.get(position).getProductName());
        holder.price.setText(mData.get(position).getProductPrice() + "원");

        if (mData.get(position).getProductImg()!=null){
            Glide.with(holder.productImg).load(Config.serverUrl + "imgload/"
                    + mData.get(position).getSellerId()
                    + mData.get(position).getFileFolder() + "/"
                    + mData.get(position).getProductImg().get(0))
                    .apply(new RequestOptions().bitmapTransform(new RoundedCorners((int) (mScale*8))))
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

    public void setItemClick(AdapterProductSearch.ItemClick itemClick){
        this.itemClick = itemClick;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
