package org.gowoon.inum.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.gowoon.inum.R;

import java.util.ArrayList;

public class AdapterGridCategory extends BaseAdapter {
    private Context mContext;
    private final ArrayList<String> childCategory;

    public AdapterGridCategory(Context c, ArrayList<String> childcategory){
        mContext = c;
        this.childCategory = childcategory;
    }

    @Override
    public int getCount() {
        return childCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            grid = inflater.inflate(R.layout.item_grid_searchsubcategory,null);
            TextView textView = grid.findViewById(R.id.tv_item_grid_category);

            textView.setText(childCategory.get(position));
        }
        else {
            grid = convertView;
        }
        return grid;
    }
}
