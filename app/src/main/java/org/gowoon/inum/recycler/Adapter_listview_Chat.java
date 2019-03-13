package org.gowoon.inum.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListViewChat;

import java.util.ArrayList;

public class Adapter_listview_Chat extends BaseAdapter{

    private ArrayList<ItemListViewChat> chatlist = new ArrayList<>();

    @Override
    public int getCount() {
        return chatlist.size();
    }

    @Override
    public Object getItem(int position) {
        return chatlist.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_chat,null,false);

            holder =  new ChatViewHolder();
            holder.tv_date = convertView.findViewById(R.id.tv_itemchat_date);
            holder.tv_name = convertView.findViewById(R.id.tv_itemchat_name);
            holder.tv_text = convertView.findViewById(R.id.tv_itemchat_text);
            holder.iv_out = convertView.findViewById(R.id.iv_itemchat_out);
            holder.iv_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            convertView.setTag(holder);

        }
        else{
            holder = (ChatViewHolder) convertView.getTag();
        }

        ItemListViewChat itemlist = chatlist.get(position);
        holder.tv_text.setText(itemlist.getText());
        holder.tv_name.setText(itemlist.getName());
        holder.tv_date.setText(itemlist.getYymmdd());

        return convertView;
    }
    class ChatViewHolder{
        TextView tv_name, tv_text, tv_date;
        ImageView iv_out;
    }
    public void addItem(ItemListViewChat list){
        chatlist.add(list);
    }
}
