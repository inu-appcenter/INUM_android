package org.gowoon.inum.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.gowoon.inum.R;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.util.Config;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterMyProduct extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<SearchIdResult> mData = new ArrayList<>();
    private LayoutInflater inflater;
    private String TAG = "Adapter User Product";
    public String productId;

    @BindView(R.id.iv_item_myproduct_option)
    ImageView ivOption;
    private onOptionClickListener onOptionClickListener;

    @OnClick(R.id.iv_item_myproduct_option)
    void onClickOption(){
        if (onOptionClickListener!=null){
            onOptionClickListener.onClick();
        }
    }

    public interface onOptionClickListener{void onClick();}

    public void setOnOptionClickListener(AdapterMyProduct.onOptionClickListener listener){
        onOptionClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recyclerview_myproduct,parent,false);
        setPercentage(view, parent);
        ButterKnife.bind(view);


        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    private void setPercentage(View view, ViewGroup parent){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.081);
        view.setLayoutParams(layoutParams);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SearchIdResult itemData = mData.get(position);
        final ListViewHolder listViewHolder = (ListViewHolder) holder;
        float mScale = listViewHolder.ivProduct.getResources().getDisplayMetrics().density;
        listViewHolder.tvName.setText(itemData.getProductName());

        Glide.with(listViewHolder.ivProduct).load(Config.serverUrl + "imgload/"
                +itemData.getSellerId()
                +itemData.getFileFolder()+"/"
                +itemData.getProductImg().get(0))
                .apply(new RequestOptions().bitmapTransform(new RoundedCorners((int) (mScale*8))))
                .into(listViewHolder.ivProduct);



//        listViewHolder.ivOption.setOnClickListener(v -> {
//            final Adapter_dialog_product_menu dialogProductMenu = new Adapter_dialog_product_menu(inflater.getContext(),itemData.getProductId());
////                dialogProductMenu.setProductName(listViewHolder.product_name.getText().toString());
//            dialogProductMenu.show();
//        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, String.valueOf(mData.size()));
        return mData.size();
    }

    private static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivProduct;
//        ivOption;
        public String productId;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_myproduct_name);
            ivProduct = itemView.findViewById(R.id.iv_item_myproduct_image);
//            ivOption = itemView.findViewById(R.id.iv_item_myproduct_option);
        }
    }

    public void addItem(SearchIdResult Data) {
        mData.add(Data);
        notifyItemInserted(mData.size()-1);
    }
}
