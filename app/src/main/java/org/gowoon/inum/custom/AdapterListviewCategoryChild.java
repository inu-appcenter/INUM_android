package org.gowoon.inum.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.gowoon.inum.R;

public class AdapterListviewCategoryChild extends BaseAdapter {
    String[] mdata;

    public AdapterListviewCategoryChild(String[] data){
        mdata = data;
    }

    @Override
    public int getCount() {
        return mdata.length;
    }

    @Override
    public Object getItem(int i) {
        return mdata[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_upload_category_child, viewGroup, false);
        }

        TextView tvChild = (TextView) view.findViewById(R.id.tv_item_list_upload_category_child);
        tvChild.setText(mdata[position]);

        return view;
    }

    public String getName(int position){
        return mdata[position];
    }
}
