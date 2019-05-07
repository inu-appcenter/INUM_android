package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_gridview_categorychild;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.recycler.Adapter_recycler_ProductSearch;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SearchProductCategoryFragment extends Fragment {
    RecyclerView recyclerView;
    private Adapter_recycler_ProductSearch Adapter;
    EditText et_search;

    FrameLayout search;
    GridView gridView;
    String[] categoryChild;
    ArrayList<String> griditems;
    TextView tv_category, tv_searchok, tvNone;
    ImageView iv_icon, iv_cancle;
    String parent, child;
    Integer caticon;
    int groupposition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_search_product_category, container, false);

        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflatermenu = getActivity().getMenuInflater();
        inflatermenu.inflate(R.menu.category_menu, menu);

        groupposition = getArguments().getInt("groupposition", 0);
        parent = getArguments().getString("parent","");
        child = getArguments().getString("child","");
        caticon = getArguments().getInt("categoryimage");

        search = rootview.findViewById(R.id.framelayout_search_category_searchbar);
        et_search = rootview.findViewById(R.id.etv_search_category_search);
        tv_searchok = rootview.findViewById(R.id.tv_search_category_ok);
        iv_cancle = rootview.findViewById(R.id.iv_search_category_erase);
        tvNone = rootview.findViewById(R.id.tv_search_category_none);

        //  검색창
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    float mScale = getResources().getDisplayMetrics().density;
                    ConstraintLayout.LayoutParams layoutParams
                            = new ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT);
                    layoutParams.setMargins((int)mScale*13, 0, (int) mScale*68,0);
                    et_search.setHint("");
                    search.setLayoutParams(layoutParams);
                    tv_searchok.setVisibility(View.VISIBLE);
                    iv_cancle.setVisibility(View.VISIBLE);
                }
                else{
                    tv_searchok.setVisibility(View.INVISIBLE);
                    iv_cancle.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_search.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    float mScale = getResources().getDisplayMetrics().density;
                    //Enter키눌렀을떄 처리
                    ConstraintLayout.LayoutParams param
                            = new ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT);
                    param.setMargins((int) mScale*13,0,(int)mScale*13,0);
                    search.setLayoutParams(param);
                    et_search.clearFocus();

                    InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });

        griditems = new ArrayList<>();
        if(groupposition <4){
            for (int j = 0 ; j <4; j++){
                categoryChild = new String[]{menu.getItem(groupposition).getSubMenu().getItem(j).getTitle().toString()};
                Collections.addAll(griditems, categoryChild);
            }
        }

        Adapter_gridview_categorychild gAdapter = new Adapter_gridview_categorychild(getActivity(),griditems);
        gridView = rootview.findViewById(R.id.gridview_search_category);
        gridView.setAdapter(gAdapter);

        tv_category = rootview.findViewById(R.id.tv_search_category_catname);
        iv_icon = rootview.findViewById(R.id.iv_search_category_icon);

        tv_category.setText(parent + " " + child);
        iv_icon.setImageDrawable(menu.getItem(groupposition).getIcon());

        String fullcategory;
        if (child.equals("전체")) {
            fullcategory = parent;
        }
        else {
            fullcategory = parent+child;
        }

        Singleton.retrofit.category(fullcategory).enqueue(new Callback<ArrayList<SearchIdResult>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchIdResult>> call, Response<ArrayList<SearchIdResult>> response) {
                if (response.isSuccessful()){
                    ArrayList<SearchIdResult> results = response.body();
                    if (results.size()!=0){
                        Adapter.mDataset.addAll(results);
                        Adapter.notifyDataSetChanged();
                        tvNone.setVisibility(View.INVISIBLE);
                    }else{
                        tvNone.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }
//                    Log.d("category_load","카테고리별 상품 로딩" +results.get(0).getProductName());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<SearchIdResult>> call, Throwable t) {

            }
        });
        Adapter = new Adapter_recycler_ProductSearch();
//        Adapter.setItemClick(new Adapter_recycler_ProductSearch.ItemClick() {
//            @Override
//            public void onClick(View view, int position) {
//                String productid = Adapter.mDataset.get(position).getProductId();
//
//            }
//        });

        recyclerView = rootview.findViewById(R.id.recyclerview_search_category);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();

        return rootview;
    }

}
