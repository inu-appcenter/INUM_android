package org.gowoon.inum.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ChatActivity;
import org.gowoon.inum.activity.MypageActivity;
import org.gowoon.inum.activity.MyproductActivity;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class DrawerMypageFragment extends Fragment {

    private FrameLayout Drawer,message,myproduct;
    TextView newmessage,productnum, mypagename, upload;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Drawer = (FrameLayout) inflater.inflate(R.layout.fragment_drawer_mypage, container, false);

        upload = Drawer.findViewById(R.id.tv_mypage_uploadproduct);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upload_intent = new Intent(getActivity().getApplicationContext(), UploadActivity.class);
                startActivity(upload_intent);
                DrawerLayout drawer = getActivity().findViewById(R.id.drawer_main);
                drawer.closeDrawer(Gravity.END);
            }
        });

        mypagename = Drawer.findViewById(R.id.tv_mypage_name);
        newmessage = (TextView) Drawer.findViewById(R.id.tv_mypage_newmessage);
        productnum = (TextView) Drawer.findViewById(R.id.tv_mypage_newproduct);
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("userinfo",MODE_PRIVATE);
        String id = pref.getString("userid","");
        mypagename.setText(pref.getString("name",""));

        message = Drawer.findViewById(R.id.framelayout_mypage_message);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_message = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent_message);
            }
        });

        myproduct = Drawer.findViewById(R.id.framelayout_mypage_myproduct);
        myproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_product = new Intent(getActivity(), MyproductActivity.class);
                startActivity(intent_product);
            }
        });

        Singleton.retrofit.searchId(id).enqueue(new Callback<ArrayList<SearchIdResult>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchIdResult>> call, Response<ArrayList<SearchIdResult>> response) {
                if (response.isSuccessful()){
                    ArrayList<SearchIdResult> result = response.body();
                    productnum.setText(String.valueOf(result.size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchIdResult>> call, Throwable t) {

            }
        });

        Drawer.findViewById(R.id.tv_mypage_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_setting = new Intent(getActivity(), MypageActivity.class);
                startActivity(intent_setting);
                DrawerLayout drawer = getActivity().findViewById(R.id.drawer_main);
                drawer.closeDrawer(Gravity.END);
            }
        });
        return Drawer;
    }

}