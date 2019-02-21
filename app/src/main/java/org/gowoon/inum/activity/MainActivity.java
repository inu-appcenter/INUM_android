package org.gowoon.inum.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListviewMainProduct;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.Adapter_ProductMain;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout btn_message;
    RecyclerView recyclerView_book, recyclerView_room, recyclerView_ticket;
    Adapter_ProductMain Adapter_room, Adapter_book, Adapter_ticket ;
    ArrayList<MainProductResult> list = new ArrayList<>();
    SharedPreferences pref;
//    List<String> product_image;
//    private String name, productid;

    public static Activity Main;

    private Fragment category,mypage;
    DrawerLayout mDrawer, cDrawer;


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

        btn_message = findViewById(R.id.constraint_main_message);
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pref = getSharedPreferences("userinfo",MODE_PRIVATE);
        String token =  pref.getString("token","");
        String id = pref.getString("userid","");

        if (!token.equals("")) {
            Log.d("Sharedpreferences test", "토큰 받은거 확인" + token);
            Singleton.retrofit.main(token).enqueue(new Callback<ArrayList<ArrayList<MainProductResult>>>() {
                @Override
                public void onResponse(Call<ArrayList<ArrayList<MainProductResult>>> call, Response<ArrayList<ArrayList<MainProductResult>>> response) {
                    Log.d("main recycler test", "" + response.code());
                    if (response.isSuccessful()) {
                        ArrayList<ArrayList<MainProductResult>> result = response.body();

                        assert result != null;
                        Adapter_ticket.mDataset.addAll(result.get(0));
                        Adapter_ticket.notifyDataSetChanged();

                        Adapter_book.mDataset.addAll(result.get(1));
                        Adapter_book.notifyDataSetChanged();

                        Adapter_room.mDataset.addAll(result.get(2));
                        Adapter_room.notifyDataSetChanged();

                        Log.d("maintest", "메인 상품 로딩성공" + result);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ArrayList<MainProductResult>>> call, Throwable t) {
                    Log.d("fail", "안돼");
                }
            });
        }

        Adapter_ticket = new Adapter_ProductMain();
        Adapter_book = new Adapter_ProductMain();
        Adapter_room = new Adapter_ProductMain();

        recyclerView_book = (RecyclerView) findViewById(R.id.recyclerview_main_product_book);
        recyclerView_book.setHasFixedSize(true);

        recyclerView_room = (RecyclerView) findViewById(R.id.recyclerview_main_product_room);
        recyclerView_room.setHasFixedSize(true);

        recyclerView_ticket = (RecyclerView) findViewById(R.id.recyclerview_main_product_ticket);
        recyclerView_ticket.setHasFixedSize(true);


//        Adapter_ticket = new Adapter_ProductMain();
       // if (mAdapter.)
//        mAdapter.setItemClick(new Adapter_ProductMain().ItemClick() {

//            @Override
//            public void onClick(View view, int position) {
//                String pid = mAdapter.mDataset.get(position).getProductId();
//                Intent intent_detail = new Intent(getActivity(), ProductDetail.class);
//                intent_detail.putExtra("id",pid);
//                startActivity(intent_detail);
//            }
//        });
        RecyclerView.LayoutManager mLayoutManager_book ,mLayoutManager_room, mLayoutManager_ticket;
        mLayoutManager_book = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_book.setLayoutManager(mLayoutManager_book);
        recyclerView_book.setItemAnimator(new DefaultItemAnimator());
        recyclerView_book.setAdapter(Adapter_book);

        mLayoutManager_room = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_room.setLayoutManager(mLayoutManager_room);
        recyclerView_room.setItemAnimator(new DefaultItemAnimator());
        recyclerView_room.setAdapter(Adapter_room);

        mLayoutManager_ticket = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView_ticket.setLayoutManager(mLayoutManager_ticket);
        recyclerView_ticket.setItemAnimator(new DefaultItemAnimator());
        recyclerView_ticket.setAdapter(Adapter_ticket);


        Adapter_book.notifyDataSetChanged();
        Adapter_room.notifyDataSetChanged();
        Adapter_ticket.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        if (drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_mypage))||drawerLayout.isDrawerOpen(findViewById(R.id.drawer_main_category))) {
            drawerLayout.closeDrawers();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        switch(view.getId()){

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
