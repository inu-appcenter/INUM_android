package org.gowoon.inum.recycler;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_dialog_product_menu;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.util.Config;

import java.util.ArrayList;

public class Adapter_recycler_MyProduct extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<SearchIdResult> data = new ArrayList<>();
    LayoutInflater inflater;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_recyclerview_myproduct,parent,false);
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    public void onBindViewHolder( RecyclerView.ViewHolder holder, final int position) {
        final SearchIdResult sData = data.get(position);
        final ListViewHolder listholder = (ListViewHolder) holder;
        listholder.product_name.setText(sData.getProductName());

        Glide.with(listholder.product_img).load(Config.serverUrl + "imgload/"
                +sData.getSellerId()
                +sData.getFileFolder()+"/"
                +sData.getProductImg().get(0))
                .into(listholder.product_img);

//        listholder.Rclayout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//            //    Intent intent = new Intent(v.getContext(), ProductDetail.class);
//            //    v.getContext().startActivity(intent);
//
//            }
//        });
        listholder.more_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Adapter_dialog_product_menu dialogProductMenu = new Adapter_dialog_product_menu(inflater.getContext(),sData.getProductId());
//                dialogProductMenu.setProductName(listholder.product_name.getText().toString());
                dialogProductMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("리스트부유으ㅡ유", String.valueOf(data.size()));
        return data.size();
    }

    private static class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView product_name;
        public ImageView product_img;
//        public ViewGroup Rclayout;
        public ImageView more_btn;
        public String productId;

        public ListViewHolder(View itemView) {
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.tv_item_myproduct_name);
            product_img = (ImageView) itemView.findViewById(R.id.iv_item_myproduct_image);
         //   Rclayout = (ViewGroup) itemView.findViewById(R.id.my_product_rv_item);
            more_btn = itemView.findViewById(R.id.iv_item_myproduct_option);
        }
    }

    public void addItem(SearchIdResult Data) {
        data.add(Data);
        notifyItemInserted(data.size()-1);
    }

}
