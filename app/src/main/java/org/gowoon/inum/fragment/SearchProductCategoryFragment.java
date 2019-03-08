package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.Adapter_recycler_ProductSearch;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductCategoryFragment extends Fragment {
    RecyclerView recyclerView;
    Adapter_recycler_ProductSearch Adapter;

    TextView tv_category;
    ImageView iv_icon;
    String parent, child;
    Integer caticon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_search_product_category, container, false);

        parent = getArguments().getString("parent","");
        child = getArguments().getString("child","");
        caticon = getArguments().getInt("categoryimage");

        tv_category = rootview.findViewById(R.id.tv_search_category_catname);
        iv_icon = rootview.findViewById(R.id.iv_search_category_icon);

        tv_category.setText(parent + " " + child);
        iv_icon.setImageResource(caticon);

        String fullcategory;
        if (child.equals("전체")) {
            fullcategory = parent;
        }
        else {
            fullcategory = parent+child;
        }

//        Singleton.retrofit.category(fullcategory).enqueue(new Callback<ArrayList<MainProductResult>>() {
//            @Override
//            public void onResponse(Call<ArrayList<MainProductResult>> call, Response<ArrayList<MainProductResult>> response) {
//                if (response.isSuccessful()){
//                    ArrayList<MainProductResult> results = response.body();
//                    Adapter.mDataset.addAll(results);
//                    Adapter.notifyDataSetChanged();
//                    Log.d("category_load","카테고리별 상품 로딩" +results.get(0));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<MainProductResult>> call, Throwable t) {
//
//            }
//        });
//        Adapter = new Adapter_recycler_ProductSearch();
//        Adapter.setItemClick(new Adapter_recycler_ProductSearch.ItemClick() {
//            @Override
//            public void onClick(View view, int position) {
//                String productid = Adapter.mDataset.get(position).getProductId();
//
//            }
//        });
//
//        RecyclerView.LayoutManager mLayoutManager;
//        mLayoutManager = new GridLayoutManager(getActivity(),3);
//
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(Adapter);
//        Adapter.notifyDataSetChanged();

        return rootview;
    }

}
