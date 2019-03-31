package org.gowoon.inum.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ChatActivity;
import org.gowoon.inum.custom.Adapter_autoViewpager;
import org.gowoon.inum.model.MainProductResult;
import org.gowoon.inum.recycler.Adapter_ProductMain;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends android.support.v4.app.Fragment {
    SharedPreferences pref;
    ConstraintLayout btn_message;
    RecyclerView recyclerView_book, recyclerView_room, recyclerView_ticket;
    Adapter_ProductMain Adapter_room, Adapter_book, Adapter_ticket ;
//    ArrayList<MainProductResult> list = new ArrayList<>();
//    AutoScrollViewPager autoviewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

//        ArrayList<String> data = new ArrayList<>();
//
//        autoviewpager = (AutoScrollViewPager) rootview.findViewById(R.id.viewpager_main_banner);
//        Adapter_autoViewpager scrollAdapter = new Adapter_autoViewpager(getActivity(),data);
//        autoviewpager.setAdapter(scrollAdapter);
//        autoviewpager.setInterval(5000);
//        autoviewpager.startAutoScroll();

        btn_message = rootview.findViewById(R.id.constraint_main_message);
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_message = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent_message);

            }
        });

        pref = getActivity().getSharedPreferences("userinfo",MODE_PRIVATE);
        String token =  pref.getString("token","");
        String id = pref.getString("userid","");

        if (!token.equals("")) {
            Log.d("token test", token);
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
                    Log.d("fail", "연결오류");
                    Toast.makeText(getActivity(), "네트워크 연결상태를 확인해주세요",Toast.LENGTH_LONG).show();
                }
            });
        }

        Adapter_ticket = new Adapter_ProductMain();
        Adapter_book = new Adapter_ProductMain();
        Adapter_room = new Adapter_ProductMain();

        recyclerView_book = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_book);
        recyclerView_book.setHasFixedSize(true);

        recyclerView_room = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_room);
        recyclerView_room.setHasFixedSize(true);

        recyclerView_ticket = (RecyclerView) rootview.findViewById(R.id.recyclerview_main_product_ticket);
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

}
