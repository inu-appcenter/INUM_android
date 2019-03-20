package org.gowoon.inum.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListViewChat;

import java.util.ArrayList;

public class Adapter_recycler_Chat extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater = null;
    private ArrayList<ItemListViewChat> data = new ArrayList<>();

    private static class ListViewHolder extends RecyclerView.ViewHolder{
        public TextView text, name, date;
        public ImageView more_btn;
        //        public ViewGroup Rclayout;
        public ListViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_itemchatlist_text);
            name = itemView.findViewById(R.id.tv_itemchatlist_name);
            date = itemView.findViewById(R.id.tv_itemchatlist_date);
            more_btn = itemView.findViewById(R.id.iv_itemchatlist_out);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = null;

        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_recyclerview_chatlist,parent,false);
        ListViewHolder holder = new ListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemListViewChat chatdata = data.get(position);
        ListViewHolder listholder = (ListViewHolder) holder;
        listholder.name.setText(chatdata.getName());
        listholder.text.setText(chatdata.getChattext());
        listholder.date.setText(chatdata.getYymmdd());

        listholder.more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("채팅 리스트", String.valueOf(data.size()));
        return data.size();
    }

    public void addItem(ItemListViewChat list){
        data.add(list);
    }
}
