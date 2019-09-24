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

public class AdapterProductMain extends RecyclerView.Adapter<AdapterProductMain.ViewHolder>{

    public ArrayList<MainProductResult> mDataset = new ArrayList<>();

  //  public ArrayList<MainProductResult> Dataset_room, Dataset_ticket, Dataset_book = new ArrayList<>();

    public ItemClick itemClick;

    public AdapterProductMain() {

    }
    public interface ItemClick{
        void onClick(View view, int position);
    }

    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public AdapterProductMain(ArrayList<MainProductResult> myData) {
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
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth()*0.46);
        layoutParams.height = parent.getHeight();
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductMain.ViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getProductName());
        holder.price.setText(mDataset.get(position).getProductPrice() + "");
        float mScale = holder.productimg.getResources().getDisplayMetrics().density;
        String category = mDataset.get(position).getCategory();
        if (category.contains("책")){
            String[] subcategory = category.split("책");
            holder.subcategory.setText(category.split("책")[1]);
        }
        else {
            holder.subcategory.setText("");
        }
        Glide.with(holder.productimg).load(Config.serverUrl + "imgload/"
                +mDataset.get(position).getSellerId()
                +mDataset.get(position).getFileFolder()+"/"
                +mDataset.get(position).getProductImg().get(0))
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners((int) (mScale*6.9f))))
                .into(holder.productimg);

        holder.itemView.setOnClickListener(v -> {
            if (itemClick != null){
                itemClick.onClick(v,position);
            }
        });
    }

    // Total number of cells
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
