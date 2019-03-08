package org.gowoon.inum.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.MainFragment;
import org.gowoon.inum.fragment.SearchProductMainFragment;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences pref;
//    List<String> product_image;
//    private String name, productid;
    EditText et_search;

    public static Activity Main;

    private Fragment category,mypage;
    DrawerLayout mDrawer, cDrawer;
    FrameLayout search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main = MainActivity.this;

        mDrawer = findViewById(R.id.nv_mypage);
        cDrawer = findViewById(R.id.nv_category);
        mypage = getSupportFragmentManager().findFragmentById(R.id.drawer_main_mypage);
        category = getSupportFragmentManager().findFragmentById(R.id.drawer_main_category);

        ImageView iv_category = findViewById(R.id.iv_main_category);
        iv_category.setOnClickListener(this);
        ImageView iv_mypage = findViewById(R.id.iv_main_mypage);
        iv_mypage.setOnClickListener(this);

        ImageView iv_closecategory = findViewById(R.id.iv_drawer_category_close);
        ImageView iv_closemypage = findViewById(R.id.iv_drawer_mypage_close);
        iv_closecategory.setOnClickListener(this);
        iv_closemypage.setOnClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment product = new MainFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_fragment_main_main,product).commit();

        et_search = findViewById(R.id.etv_main_search);
        search = findViewById(R.id.framelayout_main_searchbar);
        final ImageView iv_cancle = findViewById(R.id.iv_main_searcherase);
        final TextView tv_searchok = findViewById(R.id.tv_main_searchok);
        iv_cancle.setOnClickListener(this);
        tv_searchok.setOnClickListener(this);

        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    float mScale = getResources().getDisplayMetrics().density;
                    ConstraintLayout.LayoutParams layoutParams
                            = new ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, WRAP_CONTENT);
                    layoutParams.height = (int) (mScale*78);
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
                    ViewGroup.LayoutParams param = search.getLayoutParams();
                    param.height = (int) (mScale * 33);
                    param.width = MATCH_PARENT;
                    search.setLayoutParams(param);
//                    et_search.setHint("찾고있는 상품을 입력하세요");
                    et_search.setText("");
                    et_search.clearFocus();

                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("search", String.valueOf(et_search.getText().toString()));

//                    SearchProductMainFragment searchproduct = new SearchProductMainFragment();
//                    searchproduct.setArguments(bundle);
//                    getFragmentManager().beginTransaction()
//                            .replace(R.id.framelayout_fragment_main_main, searchproduct)
//                            .addToBackStack(null)
//                            .commit();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        if (drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_mypage))||drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_category))) {
            drawerLayout.closeDrawers();
        }
        else if (search.hasFocus()){
            search.clearFocus();
        }
//        else if (findViewById(R.id.fragment_Main_product).getVisibility() == View.VISIBLE){
//            long tempTime = System.currentTimeMillis();
//            long intervalTime = tempTime - backPressedTime;
//            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
//            } else {
//                backPressedTime = tempTime;
//                Toast.makeText(getApplicationContext(), "버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//            }
    //    }
    }

    @Override
    public void onClick(View view) {
        ConstraintLayout.LayoutParams param
                = new ConstraintLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        et_search = findViewById(R.id.etv_main_search);
        search = findViewById(R.id.framelayout_main_searchbar);
        float mScale = getResources().getDisplayMetrics().density;
        switch(view.getId()){
            case R.id.tv_main_searchok: {
                param.setMargins((int) mScale*13,(int) mScale*22,(int)mScale*13,(int) mScale*22);
                param.height = (int) (mScale * 33);
                param.width = MATCH_PARENT;
                search.setLayoutParams(param);
                Bundle bundle = new Bundle();
                String searchtext = et_search.getText().toString();
                bundle.putString("search", searchtext);
                SearchProductMainFragment searchproduct = new SearchProductMainFragment();
                searchproduct.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_fragment_main_main, searchproduct)
                        .addToBackStack(null)
                        .commit();


                et_search.clearFocus();
                et_search.setText("");


               // break;
            }

            case R.id.iv_main_searcherase:{
//                param.width = MATCH_PARENT;
//                param.setMargins((int) mScale*13,(int) mScale*22,(int)mScale*13,(int) mScale*22);
//                param.height = (int) (mScale*33);
//                search.setLayoutParams(param);
//                et_search.clearFocus();
//                et_search.setText("");
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(et_search.getWindowToken(),0);
                findViewById(R.id.iv_main_searcherase).setVisibility(View.INVISIBLE);
                findViewById(R.id.tv_main_searchok).setVisibility(View.INVISIBLE);
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
