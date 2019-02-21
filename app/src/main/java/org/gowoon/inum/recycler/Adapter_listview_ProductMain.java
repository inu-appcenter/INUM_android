package org.gowoon.inum.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListviewMainProduct;

import java.util.ArrayList;

public class Adapter_listview_ProductMain extends BaseAdapter{

    private ArrayList<ItemListviewMainProduct> listviewMainProducts = new ArrayList<ItemListviewMainProduct>();

    // Adapter에 사용되는 개수 리턴
    @Override
    public int getCount() {
        return listviewMainProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return listviewMainProducts.get(position);
    }

    // 지정 위치에 있는 데이터 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    //해당 prosition에 위치한 데이터 출력할 때 사용되는 View
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos = position;
        final Context context = viewGroup.getContext();

        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_recycler_main,viewGroup,false);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_main_product);
        TextView categoryname = view.findViewById(R.id.tv_main_recycler_product_category);

        ItemListviewMainProduct itemlistview = listviewMainProducts.get(position);

//        recyclerView.setRecyclerListener();
        categoryname.setText(itemlistview.getTxt_category());

        return view;
    }
}
