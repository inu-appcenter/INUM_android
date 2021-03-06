package org.gowoon.inum.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ProductActivity;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.AdapterProductSearch;
import org.gowoon.inum.recycler.SearchItemDecoration;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductMainFragment extends Fragment {

    TextView resultNum, tv_search, none, noInput;
    RecyclerView recyclersearch;
    AdapterProductSearch mAdapter;
    String search;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = getArguments().getString("search","");

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_search_product_main, container, false);
        tv_search = rootview.findViewById(R.id.tv_search_product_main_searchtxt);
        pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        search = getArguments().getString("search","");
        recyclersearch = rootview.findViewById(R.id.recyclerView_search_product_main);
        resultNum = rootview.findViewById(R.id.tv_search_product_main_resultnum);
        none = rootview.findViewById(R.id.tv_search_product_main_none);
        noInput = rootview.findViewById(R.id.tv_search_product_main_none_text);

        tv_search.setText("'"+search+"'");
        if (!search.equals("")) {
            noInput.setVisibility(View.INVISIBLE);
            tv_search.setVisibility(View.VISIBLE);
            Singleton.retrofit.searchName(pref.getString("token",""),search).enqueue(new Callback<ArrayList<MainProductResult>>() {
                @Override
                public void onResponse(Call<ArrayList<MainProductResult>> call, Response<ArrayList<MainProductResult>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<MainProductResult> results = response.body();
                        if (results != null) {
                            if (results.size() == 0) {
                                resultNum.setText("검색결과 00");
                                recyclersearch.setVisibility(View.INVISIBLE);
                                none.setVisibility(View.VISIBLE);

                            } else {
                                resultNum.setText("검색결과 " + results.size());
                                recyclersearch.setVisibility(View.VISIBLE);
                                none.setVisibility(View.INVISIBLE);
                                mAdapter = new AdapterProductSearch(results);
                                recyclersearch.setAdapter(mAdapter);

                                mAdapter.setItemClick(new AdapterProductSearch.ItemClick() {
                                    @Override
                                    public void onClick(View view, int position) {
                                        String productId = mAdapter.mData.get(position).getProductId();
                                        Intent intentProductDetail = new Intent(getActivity(), ProductActivity.class);
                                        intentProductDetail.putExtra("id", productId);
                                        startActivity(intentProductDetail);
                                    }
                                });

                                mAdapter.notifyDataSetChanged();
                                Log.d("searchtest", "검색결과부르기" + mAdapter.mData.get(0));
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
            resultNum.setText("검색결과 00");
            recyclersearch.setVisibility(View.INVISIBLE);
            noInput.setVisibility(View.VISIBLE);
            tv_search.setVisibility(View.INVISIBLE);
        }
        recyclersearch.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclersearch.setLayoutManager(mLayoutManager);
        recyclersearch.setItemAnimator(new DefaultItemAnimator());
        recyclersearch.addItemDecoration(new SearchItemDecoration(getContext(),20f,20f,12.5f));
        return rootview;
    }
}
