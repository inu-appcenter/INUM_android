package org.gowoon.inum.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ChatActivity;
import org.gowoon.inum.activity.MainActivity;
import org.gowoon.inum.activity.ProductActivity;
import org.gowoon.inum.custom.AdapterAutoScrollViewpager;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.AdapterProductMain;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Objects;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment {
    SharedPreferences pref;
    ConstraintLayout btn_message;
    RecyclerView recyclerView_book, recyclerView_room, recyclerView_ticket;
    AdapterProductMain Adapter_room, Adapter_book, Adapter_ticket ;
    ArrayList<MainProductResult> list = new ArrayList<>();
    AutoScrollViewPager bannerViewPager;
    AdapterAutoScrollViewpager scrollAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        bannerViewPager = rootview.findViewById(R.id.viewpager_main_banner);
        setBanner(bannerViewPager);
        setBannerData(bannerViewPager);

        btn_message = rootview.findViewById(R.id.constraint_main_message);
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_message = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent_message);

            }
        });

        ((MainActivity) Objects.requireNonNull(getActivity())).backPressHolder();

        pref = Objects.requireNonNull(getActivity()).getSharedPreferences("userinfo",MODE_PRIVATE);
        final String token =  pref.getString("token","");
        String id = pref.getString("userid","");

        if (!Objects.requireNonNull(token).equals("")) {
            Log.d("token test", token);
            Singleton.retrofit.main(token).enqueue(new Callback<ArrayList<ArrayList<MainProductResult>>>() {
                @Override
                public void onResponse(Call<ArrayList<ArrayList<MainProductResult>>> call, Response<ArrayList<ArrayList<MainProductResult>>> response) {
                    Log.d("token response test", token);
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
                    Log.d("fail", "연결오류");
                    Toast.makeText(getActivity(), "네트워크 연결상태를 확인해주세요",Toast.LENGTH_LONG).show();
                }
            });
        }

        Adapter_ticket = new AdapterProductMain();
        Adapter_book = new AdapterProductMain();
        Adapter_room = new AdapterProductMain();

        Adapter_book.setItemClick(new AdapterProductMain.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                String productId = Adapter_book.mDataset.get(position).getProductId();
                Intent intentProductDetail = new Intent(getActivity(), ProductActivity.class);
                intentProductDetail.putExtra("id", productId);
                startActivity(intentProductDetail);
            }
        });

        Adapter_ticket.setItemClick(new AdapterProductMain.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                String productId = Adapter_ticket.mDataset.get(position).getProductId();
                Intent intentProductDetail = new Intent(getActivity(), ProductActivity.class);
                intentProductDetail.putExtra("id", productId);
                startActivity(intentProductDetail);
            }
        });
        Adapter_room.setItemClick(new AdapterProductMain.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                String productId = Adapter_room.mDataset.get(position).getProductId();
                Intent intentProductDetail = new Intent(getActivity(), ProductActivity.class);
                intentProductDetail.putExtra("id", productId);
                startActivity(intentProductDetail);
            }
        });

        recyclerView_book = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_book);
        recyclerView_book.setHasFixedSize(true);

        recyclerView_room = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_room);
        recyclerView_room.setHasFixedSize(true);

        recyclerView_ticket = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_ticket);
        recyclerView_ticket.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager_book ,mLayoutManager_room, mLayoutManager_ticket;
        mLayoutManager_book = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_book.setLayoutManager(mLayoutManager_book);
        recyclerView_book.setItemAnimator(new DefaultItemAnimator());
        recyclerView_book.setAdapter(Adapter_book);

        mLayoutManager_room = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_room.setLayoutManager(mLayoutManager_room);
        recyclerView_room.setItemAnimator(new DefaultItemAnimator());
        recyclerView_room.setAdapter(Adapter_room);

        mLayoutManager_ticket = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView_ticket.setLayoutManager(mLayoutManager_ticket);
        recyclerView_ticket.setItemAnimator(new DefaultItemAnimator());
        recyclerView_ticket.setAdapter(Adapter_ticket);

        Adapter_book.notifyDataSetChanged();
        Adapter_room.notifyDataSetChanged();
        Adapter_ticket.notifyDataSetChanged();

        return rootview;
    }

    private void setBanner(AutoScrollViewPager bannerViewPager){
        bannerViewPager.setInterval(5000);
        bannerViewPager.setScrollDurationFactor(10);
        bannerViewPager.startAutoScroll();
    }
    private void setBannerData(AutoScrollViewPager bannerViewPager){
        final ArrayList<String> data = new ArrayList<>();
        data.add("ad1.png");
        data.add("ad2.png");
        data.add("ad3.png");

        scrollAdapter = new AdapterAutoScrollViewpager(getActivity(),data);
        bannerViewPager.setAdapter(scrollAdapter);
    }
}
