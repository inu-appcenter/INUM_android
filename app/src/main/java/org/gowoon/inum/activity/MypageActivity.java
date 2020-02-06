package org.gowoon.inum.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterDialogTwoButton;
import org.gowoon.inum.custom.Adapter_listview_setting;
import org.gowoon.inum.fragment.SettingPhoneChangeFragment;
import org.gowoon.inum.fragment.SettingPushAlarmFragment;
import org.gowoon.inum.fragment.SettingPwChangeFragment;
import org.gowoon.inum.fragment.SettingQuestFragment;
import org.gowoon.inum.fragment.SettingSecessionFragment;
import org.gowoon.inum.model.UserInfoVO;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageActivity extends AppCompatActivity {

    ListView listview;
    Adapter_listview_setting adapter;
    String[] item;
    private String id, token, tel;
    SharedPreferences.Editor editor;

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        adapter = new Adapter_listview_setting();

        listview = findViewById(R.id.listview_mypage);
        listview.setAdapter(adapter);
//        getUserInfo();
        final AdapterDialogTwoButton dialogLogout =
                new AdapterDialogTwoButton(this,"확인을 누르시면\n로그아웃 후 로그인 화면으로 이동합니다");

        item = new String[]{"전화번호 변경", "푸시 알림 설정", "문의하기", "비밀번호 변경", "로그아웃", "회원 탈퇴"};
        for (String s : item) {
            adapter.addItem(s);
        }

        listview.setOnItemClickListener((adapterView, view, position, l) -> {
            switch (position){
                //번호변경
                case 0:{
                    SettingPhoneChangeFragment phoneChange = new SettingPhoneChangeFragment();
                    Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
                    bundle.putString("tel", tel); // key , value
                    phoneChange.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activiy_setting, phoneChange)
                            .addToBackStack(null)
                            .commit();
                    break;
                }
                //푸시알림
                case 1:{
                    SettingPushAlarmFragment pushAlarm = new SettingPushAlarmFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activiy_setting, pushAlarm)
                            .addToBackStack(null)
                            .commit();
                    break;
                }
                //문희
                case 2:{
                    SettingQuestFragment moonhee = new SettingQuestFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activiy_setting, moonhee)
                            .addToBackStack(null)
                            .commit();
                    break;
                }
                //비밀번호 변경
                case 3:{
                    SettingPwChangeFragment pwChange = new SettingPwChangeFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.activiy_setting, pwChange)
                            .addToBackStack(null)
                            .commit();
                    break;
                }
                //로그아웃
                case 4:{
                    SharedPreferences pref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    editor = pref.edit();
                    editor.clear();
                    editor.apply();
                    dialogLogout.show();
                    dialogLogout.setOnOkButtonClickListener(() -> {
                        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentLogin);
                        finishAffinity();
                    });
                    break;
                }
                //탈퇴
                case 5:{
                    SettingSecessionFragment secession = new SettingSecessionFragment();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.activiy_setting, secession)
                            .addToBackStack(null)
                            .commit();
                    break;
                }
            }
        });
    }
    public void getUserInfo(){
        SharedPreferences pref = this.getSharedPreferences("userinfo",MODE_PRIVATE);
        token = pref.getString("token","");

        Singleton.retrofit.userInfo(token).enqueue(new Callback<UserInfoVO>() {
            @Override
            public void onResponse(Call<UserInfoVO> call, Response<UserInfoVO> response) {
                if (response.code()==200){
                    tel = response.body().getTel();
                    Log.w("user Tel",tel);
                }
                else {
                    Log.d("user info load err","err");
                }
            }

            @Override
            public void onFailure(Call<UserInfoVO> call, Throwable t) {

            }
        });
    }
}
