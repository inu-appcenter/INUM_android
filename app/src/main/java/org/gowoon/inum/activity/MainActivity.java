package org.gowoon.inum.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.BackPressCloseHandler;
import org.gowoon.inum.fragment.MainFragment;
import org.gowoon.inum.fragment.SearchProductMainFragment;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences pref;
//    List<String> product_image;
//    private String name, productid;
    EditText etSearch;

    private Fragment category,mypage;
    DrawerLayout mDrawer, cDrawer;
    FrameLayout search,fragments;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        setSearch();

        MainFragment fragmentMainProduct = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_main_main,fragmentMainProduct)
                .commit();

        final ImageView ivCancel = findViewById(R.id.iv_main_searcherase);
        final TextView tvSearchOk = findViewById(R.id.tv_main_searchok);
        ivCancel.setOnClickListener(this);
        tvSearchOk.setOnClickListener(this);

        fragments = findViewById(R.id.framelayout_fragment_main_main);

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    float mScale = getResources().getDisplayMetrics().density;
                    LinearLayout.LayoutParams layoutParams
                            = new LinearLayout.LayoutParams(0, WRAP_CONTENT,5f);
                    layoutParams.setMargins((int)mScale*13, 0, (int) mScale*3,0);
                    etSearch.setHint("");
                    fragments.setVisibility(View.INVISIBLE);
                    tvSearchOk.setVisibility(View.VISIBLE);
                    tvSearchOk.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,1f));
                    search.setLayoutParams(layoutParams);
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
                    setLayoutParams();
                    Bundle bundle = new Bundle();
                    String searchText = etSearch.getText().toString();
                    bundle.putString("search", searchText);

                    SearchProductMainFragment searchproduct = new SearchProductMainFragment();
                    searchproduct.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.framelayout_fragment_main_main, searchproduct)
                            .addToBackStack(null)
                            .commit();

                    cleanEditSearch(etSearch);

                    return true;
                }
                return false;
            }
        });
    }

    public void backPressHolder(){
        new BackPressCloseHandler(this).onBackPressed();
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        if (drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_mypage))||drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_category))) {
            drawerLayout.closeDrawers();
        }
        else if (search.hasFocus()){
            setLayoutParams();
            search.clearFocus();
        }
//        else if (findViewById(R.id.frame).getVisibility() == View.VISIBLE){
//            long tempTime = System.currentTimeMillis();
//            long intervalTime = tempTime - backPressedTime;
//            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
        else {
            super.onBackPressed();
        }
//            } else {
//                backPressedTime = tempTime;
//                Toast.makeText(getApplicationContext(), "버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    public void initView(){
        mDrawer = findViewById(R.id.nv_mypage);
        cDrawer = findViewById(R.id.nv_category);
        mypage = getSupportFragmentManager().findFragmentById(R.id.drawer_main_mypage);
        category = getSupportFragmentManager().findFragmentById(R.id.drawer_main_category);

        ImageView ivCategory = findViewById(R.id.iv_main_category);
        ivCategory.setOnClickListener(this);
        ImageView iv_mypage = findViewById(R.id.iv_main_mypage);
        iv_mypage.setOnClickListener(this);

        ImageView ivClosecategory = findViewById(R.id.iv_drawer_category_close);
        ImageView ivClosemypage = findViewById(R.id.iv_drawer_mypage_close);
        ivClosecategory.setOnClickListener(this);
        ivClosemypage.setOnClickListener(this);
    }
    
    private void setSearch(){
        etSearch = findViewById(R.id.etv_main_search);
        search = findViewById(R.id.framelayout_main_searchbar);
    }

    private void cleanEditSearch(EditText etSearch){
        etSearch.clearFocus();
        etSearch.setText("");
        etSearch.setHint("찾고있는 상품을 입력하세요");

        findViewById(R.id.iv_main_searcherase).setVisibility(View.INVISIBLE);
        findViewById(R.id.tv_main_searchok).setVisibility(View.GONE);
        fragments.setVisibility(View.VISIBLE);

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(etSearch.getWindowToken(),0);
    }

    private void setLayoutParams(){
        LinearLayout.LayoutParams param
                = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT);

        float mScale = getResources().getDisplayMetrics().density;
        param.setMargins((int) mScale*13,0,(int)mScale*13,0);

        search.setLayoutParams(param);
    }

    @Override
    public void onClick(View view) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        setSearch();

        switch(view.getId()){
            case R.id.tv_main_searchok: {
                setLayoutParams();

                Bundle bundle = new Bundle();
                String searchText = etSearch.getText().toString();
                bundle.putString("search", searchText);

                SearchProductMainFragment searchProduct = new SearchProductMainFragment();
                searchProduct.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_fragment_main_main, searchProduct)
                        .addToBackStack(null)
                        .commit();

                cleanEditSearch(etSearch);

                break;
            }

            case R.id.iv_main_searcherase:{
                setLayoutParams();
                cleanEditSearch(etSearch);
                break;
            }

            case R.id.iv_drawer_category_close:{
                drawer.closeDrawer(Gravity.START);
                break;
            }
            case R.id.iv_drawer_mypage_close:{
                drawer.closeDrawer(Gravity.END);
                break;
            }
            case R.id.iv_main_category:
            {
                drawer.openDrawer(Gravity.START);
                break;
            }
            case R.id.iv_main_mypage:{
                drawer.openDrawer(Gravity.END);
                break;
            }
        }

    }
}
