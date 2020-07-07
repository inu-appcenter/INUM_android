package org.gowoon.inum.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.Log;
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
import org.gowoon.inum.model.UserInfoVO;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class DrawerMypageFragment extends Fragment implements View.OnClickListener {

    private FrameLayout Drawer,message,myproduct;
    TextView tvNewMsg,tvUserProduct, tvName, tvUpload;
    UserInfoVO infoVO;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Drawer = (FrameLayout) inflater.inflate(R.layout.fragment_drawer_mypage, container, false);

        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("userinfo",MODE_PRIVATE);
        String id = pref.getString("userid","");
        String token = pref.getString("token","");

        Singleton.retrofit.userInfo(token).enqueue(new Callback<UserInfoVO>() {
            @Override
            public void onResponse(Call<UserInfoVO> call, Response<UserInfoVO> response) {
                if (response.isSuccessful()){
                    infoVO = response.body();
                    Log.d("load info",infoVO.getId());
                    tvName.setText(infoVO.getName());
                }
            }

            @Override
            public void onFailure(Call<UserInfoVO> call, Throwable t) {

            }
        });

        Singleton.retrofit.searchId(token, id).enqueue(new Callback<ArrayList<SearchIdResult>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchIdResult>> call, Response<ArrayList<SearchIdResult>> response) {
                if (response.isSuccessful()){
                    ArrayList<SearchIdResult> result = response.body();
                    tvUserProduct.setText(String.valueOf(result != null ? result.size() : 0));
                    Log.d("product num ", String.valueOf(result.size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchIdResult>> call, Throwable t) {

            }
        });

        initView(Drawer);

        tvUpload.setOnClickListener(view -> {
            Intent upload_intent = new Intent(getActivity().getApplicationContext(), UploadActivity.class);
            startActivity(upload_intent);
            close();
        });

        message = Drawer.findViewById(R.id.framelayout_mypage_message);
        message.setOnClickListener(view -> {
            Intent intent_message = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent_message);
        });

        myproduct = Drawer.findViewById(R.id.framelayout_mypage_myproduct);
        myproduct.setOnClickListener(view -> {
            Intent intent_product = new Intent(getActivity(), MyproductActivity.class);
            startActivity(intent_product);
            close();
        });

        Drawer.findViewById(R.id.tv_mypage_setting).setOnClickListener(v -> {
            Intent intentSetting = new Intent(getActivity(), MypageActivity.class);
            intentSetting.putExtra("infoVO", infoVO);
            startActivity(intentSetting);
            close();
        });
        return Drawer;
    }
    private void initView(View Drawer){
        tvUpload = Drawer.findViewById(R.id.tv_mypage_uploadproduct);
        tvName = Drawer.findViewById(R.id.tv_mypage_name);

        tvNewMsg = Drawer.findViewById(R.id.tv_mypage_newmessage);
        tvUserProduct = Drawer.findViewById(R.id.tv_mypage_newproduct);
    }
    private void close(){
        DrawerLayout drawer = getActivity().findViewById(R.id.drawer_main);
        drawer.closeDrawer(Gravity.END);
    }

    @Override
    public void onClick(View view) {
//        switch (view){
//            case R.id.
//        }
    }
}