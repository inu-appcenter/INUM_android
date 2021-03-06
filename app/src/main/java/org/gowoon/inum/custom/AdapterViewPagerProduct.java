package org.gowoon.inum.custom;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.gowoon.inum.R;
import org.gowoon.inum.util.Config;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterViewPagerProduct extends PagerAdapter {
    Context context;
    private ArrayList<String> mResources;
    private ArrayList<Uri> mUriList;
    private String file, sellerId;

    public AdapterViewPagerProduct(Context context, ArrayList<String> mResources, String file,String sellerId) {
        this.mResources = mResources;
        this.context = context;
        this.file = file;
        this.sellerId = sellerId;
    }

    public AdapterViewPagerProduct(Context context, ArrayList<Uri> mResources){
        this.context = context;
        this.mUriList = mResources;
    }

    @NotNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_viewpager_product_one,container,false);

        ImageView imageView = (ImageView)view.findViewById(R.id.image_viewpager_productimage);
        int mScale = (int) context.getResources().getDisplayMetrics().density;
        if (mResources != null){
            Glide.with(context).load(Config.serverUrl+"imgload/"+sellerId+file+"/"+mResources.get(position))
                    .apply(new RequestOptions().override(mScale*360,mScale*267).centerCrop()).into(imageView);
        }
        if (mUriList != null){
            Glide.with(context).load(mUriList.get(position))
                    .apply(new RequestOptions().override(mScale*360,mScale*267).centerCrop())
                    .into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (mUriList!=null){
            return mUriList.size();
        }
        if (mResources!=null){
            return mResources.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object){
        container.removeView((View) object);
    }
}
