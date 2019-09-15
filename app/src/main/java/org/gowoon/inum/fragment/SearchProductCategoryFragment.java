package org.gowoon.inum.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ProductActivity;
import org.gowoon.inum.custom.AdapterGridCategory;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.Adapter_recycler_ProductSearch;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.View.GONE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SearchProductCategoryFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    private Adapter_recycler_ProductSearch Adapter = new Adapter_recycler_ProductSearch();;

    EditText etSearch;

    FrameLayout search;
    GridView gridView;
    String[] categoryChild;
    ArrayList<String> gridItems;
    TextView tvCategory, tvSearchOk, tvNone;
    ImageView ivIcon, ivCancel;
    String parent, child, token;
    Integer categoryIcon;
    int groupPosition;
    final int CATEGORY_SEARCH = 1, CATEGORY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search_product_category, container, false);

        initView(rootView);
        setSearch(rootView);
        // 맨 처음 해당되는 카테고리 상품 로드

        tvSearchOk = rootView.findViewById(R.id.tv_search_category_ok);
        ivCancel = rootView.findViewById(R.id.iv_search_category_erase);
        tvSearchOk.setOnClickListener(this);
        ivCancel.setOnClickListener(this);
        
        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflatermenu = getActivity().getMenuInflater();
        inflatermenu.inflate(R.menu.category_menu, menu);

        groupPosition = getArguments().getInt("groupposition", 0);
        parent = getArguments().getString("parent","");
        child = getArguments().getString("child","");
        categoryIcon = getArguments().getInt("categoryimage");

        getPreferences();

        searchItem(token,"",getFullCategory(),2);
        //  검색창
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    float mScale = getResources().getDisplayMetrics().density;
                    LinearLayout.LayoutParams layoutParams
                            = new LinearLayout.LayoutParams(0, WRAP_CONTENT,5f);
                    layoutParams.setMargins((int)mScale*13, 0, (int) mScale*3,0);
                    search.setLayoutParams(layoutParams);
                    etSearch.setHint("");

                    tvSearchOk.setVisibility(View.VISIBLE);
                    tvSearchOk.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,1f));

                    ivCancel.setVisibility(View.VISIBLE);
                }
                else{
                    cleanEditSearch(etSearch);
                }
            }
        });
        etSearch.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Enter키눌렀을떄 처리
                    searchItem(token, etSearch.getText().toString(),getFullCategory(),1);
                    setLayoutParams();
                    etSearch.clearFocus();
                    cleanEditSearch(etSearch);
                    return true;
                }
                return false;
            }
        });

        gridItems = new ArrayList<>();
        if(groupPosition <4){
            for (int j = 0 ; j <4; j++){
                gridView.setVisibility(View.VISIBLE);
                categoryChild = new String[]{menu.getItem(groupPosition).getSubMenu().getItem(j).getTitle().toString()};
                Collections.addAll(gridItems, categoryChild);
            }
        } else{
          gridView.setVisibility(GONE);
        }

        AdapterGridCategory gAdapter = new AdapterGridCategory(getActivity(),gridItems);
        gridView.setAdapter(gAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                child = gridItems.get(position);
                tvCategory.setText(parent + " " + child);
                searchItem(token,"",getFullCategory(),2);
            }
        });
        
        tvCategory.setText(parent + " " + child);
        ivIcon.setImageDrawable(menu.getItem(groupPosition).getIcon());

        Adapter.setItemClick(new Adapter_recycler_ProductSearch.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                String productId = Adapter.mDataset.get(position).getProductId();
                Intent intentProductDetail = new Intent(getActivity(), ProductActivity.class);
                intentProductDetail.putExtra("id", productId);
                startActivity(intentProductDetail);
            }
        });

        setRecyclerView(recyclerView,Adapter);

        return rootView;
    }

    private String getFullCategory(){
        if (child.equals("전체")) {
            return parent;
        }
        else {
            return (parent+child).trim();
        }
    }

    private void setLayoutParams(){
        LinearLayout.LayoutParams param
                = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT);

        float mScale = getResources().getDisplayMetrics().density;
        param.setMargins((int) mScale*13,0,(int)mScale*13,0);

        search.setLayoutParams(param);
    }

    private void cleanEditSearch(EditText etSearch){
        etSearch.clearFocus();
        etSearch.setText("");
        etSearch.setHint("찾고있는 상품을 입력하세요");

        tvSearchOk.setVisibility(View.INVISIBLE);
        ivCancel.setVisibility(GONE);

        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(etSearch.getWindowToken(),0);
    }
    
    private void initView(View rootView){
        gridView = rootView.findViewById(R.id.gridview_search_category);
        tvCategory = rootView.findViewById(R.id.tv_search_category_catname);
        ivIcon = rootView.findViewById(R.id.iv_search_category_icon);

        recyclerView = rootView.findViewById(R.id.recyclerview_search_category);

        tvNone = rootView.findViewById(R.id.tv_search_category_none);
    }

    private void setSearch(View rootView){
        search = rootView.findViewById(R.id.framelayout_search_category_searchbar);
        etSearch = rootView.findViewById(R.id.etv_search_category_search);
    }

    public void setRecyclerView(RecyclerView recyclerView, Adapter_recycler_ProductSearch Adapter){
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();
    }
    
    public void getPreferences(){
        final SharedPreferences pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        token = pref.getString("token","");
    }

    private void searchItem(String token, String text,String fullCategory, int NUM){
        Adapter.mDataset.clear();
        switch (NUM){
            case CATEGORY:{
                Singleton.retrofit.category(token,fullCategory).enqueue(new Callback<ArrayList<MainProductResult>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MainProductResult>> call, Response<ArrayList<MainProductResult>> response) {
                        if (response.isSuccessful()){
                            ArrayList<MainProductResult> results = response.body();
                            if (results.size()!=0){
                                Adapter.mDataset.addAll(results);
                                Adapter.notifyDataSetChanged();
                                tvNone.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }else{
                                tvNone.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                            }
                            Log.d("category_load","카테고리별 상품 로딩" +results.size());
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<MainProductResult>> call, Throwable t) {

                    }
                });
                break;
            }
            case CATEGORY_SEARCH:{
                HashMap<String, String> searchMap = new HashMap<>();
                searchMap.put("searchName",text);
                searchMap.put("category",fullCategory);
                Singleton.retrofit.searchInCategory(token,searchMap).enqueue(new Callback<ArrayList<MainProductResult>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MainProductResult>> call, Response<ArrayList<MainProductResult>> response) {
                        if (response.code() == 200){
                            ArrayList<MainProductResult> results = response.body();
                            if (results.size()!=0){
                                Adapter.mDataset.addAll(results);
                                Adapter.notifyDataSetChanged();
                                tvNone.setVisibility(View.INVISIBLE);
                            }else{
                                tvNone.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            Log.d("category_search","카테고리 내 검색" +results.size());
                            Log.w("test array",results.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MainProductResult>> call, Throwable t) {

                    }
                });
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_category_erase:{
                setLayoutParams();
                cleanEditSearch(etSearch);
            }
            case R.id.tv_search_category_ok:{
                searchItem(token,etSearch.getText().toString(),getFullCategory(),1);
                cleanEditSearch(etSearch);
                break;
            }
        }
    }
}
