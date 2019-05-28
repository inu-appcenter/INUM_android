package org.gowoon.inum.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.gowoon.inum.R;
import org.gowoon.inum.util.Config;

import java.util.ArrayList;

public class AdapterViewPagerProduct extends PagerAdapter {
    Context context;
    private ArrayList<String> mResources;

    public AdapterViewPagerProduct(Context context, ArrayList<String> mResources) {
        this.mResources = mResources;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_viewpager_product_one,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.image_viewpager_productimage);
        int mScale = (int) context.getResources().getDisplayMetrics().density;
//        Glide.with(context).load(Config.serverUrl + "imgload/" + mResources.get(position)).
        Glide.with(context).load(Config.serverUrl+"imgload/"+mResources.get(position))
                .apply(new RequestOptions().override(mScale*360,mScale*267).centerCrop()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}