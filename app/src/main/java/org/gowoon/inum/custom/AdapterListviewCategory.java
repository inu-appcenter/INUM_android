package org.gowoon.inum.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.DataCategoryParent;

import java.util.ArrayList;

public class AdapterListviewCategory extends BaseAdapter {
    private ArrayList<DataCategoryParent> itemlist = new ArrayList<>();

    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int i) {
        return itemlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        final Context context = viewGroup.getContext();

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_upload_category, viewGroup, false);
        }

        DataCategoryParent itemlists = itemlist.get(position);

        ImageView icon = view.findViewById(R.id.iv_item_list_upload_category);
        TextView name = view.findViewById(R.id.tv_item_list_upload_category);

        icon.setImageDrawable(itemlists.getCategory_image());
        name.setText(itemlists.getCategory_name());

        return view;
    }

    public String getName(int position){
        DataCategoryParent itemlists = itemlist.get(position);
        return itemlists.getCategory_name();
    }

    public void addView(Drawable categoryIcon, String name) {
        DataCategoryParent item = new DataCategoryParent();
        item.setCategory_image(categoryIcon);
        item.setCategory_name(name);
        
        itemlist.add(item);
    }
}
