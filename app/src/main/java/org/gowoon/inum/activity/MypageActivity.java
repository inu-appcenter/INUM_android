package org.gowoon.inum.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_dialog_twobutton;
import org.gowoon.inum.custom.Adapter_listview_setting;
import org.gowoon.inum.fragment.SettingPhoneChangeFragment;
import org.gowoon.inum.fragment.SettingPushAlarmFragment;
import org.gowoon.inum.fragment.SettingPwChangeFragment;
import org.gowoon.inum.fragment.SettingQuestFragment;
import org.gowoon.inum.fragment.SettingSecessionFragment;
import org.gowoon.inum.model.UserInfoVO;

public class MypageActivity extends AppCompatActivity {

    ListView listview;
    Adapter_listview_setting adapter;
    String[] item;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        adapter = new Adapter_listview_setting();

        listview = findViewById(R.id.listview_mypage);
        listview.setAdapter(adapter);
        getUserInfo();
        final Adapter_dialog_twobutton dialogLogout =
                new Adapter_dialog_twobutton(this,"확인을 누르시면\n로그아웃 후 로그인 화면으로 이동합니다");

        item = new String[]{"전화번호 변경", "푸시 알림 설정", "문의하기", "비밀번호 변경", "로그아웃", "회원 탈퇴"};
        for (String s : item) {
            adapter.addItem(s);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    //번호변경
                    case 0:{
                        SettingPhoneChangeFragment phoneChange = new SettingPhoneChangeFragment();
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
                        dialogLogout.setOnOkButtonClickListener(new Adapter_dialog_twobutton.OnOkButtonClickListener() {
                            @Override
                            public void onClick() {
                                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentLogin);
                                finishAffinity();
                            }
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
            }
        });
    }
    public void getUserInfo(){
        UserInfoVO infoVO = (UserInfoVO)getIntent().getSerializableExtra("infoVO");
        Log.d("load data", infoVO.getId());
    }
}
