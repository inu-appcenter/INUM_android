package org.gowoon.inum.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListViewSetting;
import java.util.ArrayList;
import java.util.List;

public class Adapter_listview_setting extends BaseAdapter {
    private ArrayList<ItemListViewSetting> itemlistview = new ArrayList<ItemListViewSetting>();

    @Override
    public int getCount() {
        return itemlistview.size();
    }

    @Override
    public Object getItem(int i) {
        return itemlistview.get(i);
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
            view = inflater.inflate(R.layout.item_listview_setting, viewGroup, false);
        }

        TextView settingtext = (TextView) view.findViewById(R.id.tv_listview_setting);

        ItemListViewSetting itemlists = itemlistview.get(position);

        settingtext.setText(itemlists.getSettingitem());

        return view;
    }

    public void addItem(String text){
        ItemListViewSetting item = new ItemListViewSetting();
        item.setSettingitem(text);

        itemlistview.add(item);

    }
}
