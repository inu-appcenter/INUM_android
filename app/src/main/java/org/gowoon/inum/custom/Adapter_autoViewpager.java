package org.gowoon.inum.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.gowoon.inum.R;

import java.util.ArrayList;

public class Adapter_autoViewpager extends PagerAdapter {
    Context context;
    ArrayList<String> data;

    public Adapter_autoViewpager(Context context, ArrayList<String> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_autoviewpager, null);
        ImageView iv_item = (ImageView) v.findViewById(R.id.iv_item_autoviewpager);
        Glide.with(context).load(data.get(position)).into((iv_item));
        container.addView(v);

        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
