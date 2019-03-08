package org.gowoon.inum.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.Adapter_recycler_ProductSearch;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductMainFragment extends Fragment {

    TextView resultnum, tv_search, none;
    RecyclerView recyclersearch;
    Adapter_recycler_ProductSearch mAdapter;
    ArrayList<MainProductResult> list = new ArrayList<>();
    String search;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_search_product_main, container, false);
        tv_search = rootview.findViewById(R.id.tv_search_product_main_searchtxt);
        pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        search = getArguments().getString("search","");
        recyclersearch = rootview.findViewById(R.id.recyclerView_search_product_main);
        resultnum = rootview.findViewById(R.id.tv_search_product_main_resultnum);
        none = rootview.findViewById(R.id.tv_search_product_main_none);

        tv_search.setText("'"+search+"'");
        if (!search.equals("")) {
            Singleton.retrofit.searchproduct(search).enqueue(new Callback<ArrayList<MainProductResult>>() {
                @Override
                public void onResponse(Call<ArrayList<MainProductResult>> call, Response<ArrayList<MainProductResult>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<MainProductResult> results = response.body();
                        if (results != null) {
                            if (results.size() == 0) {
                                resultnum.setText("검색결과 00");
                                recyclersearch.setVisibility(View.INVISIBLE);
                                none.setVisibility(View.VISIBLE);

                            } else {
                                resultnum.setText("검색결과 " + results.size());
                                recyclersearch.setVisibility(View.VISIBLE);
                                none.setVisibility(View.INVISIBLE);
                                mAdapter.mDataset.addAll(results);
                                mAdapter.notifyDataSetChanged();
                                Log.d("searchtest", "검색결과부르기" + mAdapter.mDataset.get(0));
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<MainProductResult>> call, Throwable t) {

                }
            });
        }
        else
        {
            none.setText("검색어를 입력해주세요");
        }
        recyclersearch.setHasFixedSize(true);
        mAdapter = new Adapter_recycler_ProductSearch();
//        mAdapter.setItemClick(new Adapter_recycler_ProductSearch.ItemClick() {
//            @Override
//            public void onClick(View view, int position) {
//                Intent intent_detail = new Intent(getActivity(), ProductDetail.class);
//                startActivity(intent_detail);
//            }
//        });
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclersearch.setLayoutManager(mLayoutManager);
        recyclersearch.setItemAnimator(new DefaultItemAnimator());
        recyclersearch.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return rootview;
    }
}
