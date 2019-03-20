package org.gowoon.inum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_listview_setting;
import org.gowoon.inum.fragment.SettingPhoneChangeFragment;
import org.gowoon.inum.fragment.SettingPushAlarmFragment;
import org.gowoon.inum.fragment.SettingPwChangeFragment;
import org.gowoon.inum.fragment.SettingQuestFragment;
import org.gowoon.inum.fragment.SettingSecessionFragment;

import java.util.List;

public class MypageActivity extends AppCompatActivity {

    ListView listview;
    Adapter_listview_setting adapter;
    String[] item;
    MainActivity mainactivity;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        adapter = new Adapter_listview_setting();

        listview = findViewById(R.id.listview_mypage);
        listview.setAdapter(adapter);

        item = new String[]{"전화번호 변경", "푸시 알림 설정", "문의하기", "비밀번호 변경", "로그아웃", "회원 탈퇴"};
        for (int i = 0 ; i<item.length ;i++)
        {
            adapter.addItem(item[i]);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    //번호변경
                    case 0:{
                        SettingPhoneChangeFragment phone_change = new SettingPhoneChangeFragment();
                        getFragmentManager().beginTransaction()
                                //.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
                                .replace(R.id.activiy_setting, phone_change)
                                .addToBackStack(null)
                                .commit();
                        break;
                    }
                    //푸시알림
                    case 1:{
                        SettingPushAlarmFragment push_alarm = new SettingPushAlarmFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.activiy_setting, push_alarm)
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
                        SettingPwChangeFragment pwchange = new SettingPwChangeFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.activiy_setting, pwchange)
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

                        mainactivity = (MainActivity) MainActivity.Main;
                        mainactivity.finish();

                        Intent intent_login = new Intent(getApplicationContext(), LoginActivity.class);
                        intent_login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent_login);
                        finish();

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
            }
        });
    }
}
