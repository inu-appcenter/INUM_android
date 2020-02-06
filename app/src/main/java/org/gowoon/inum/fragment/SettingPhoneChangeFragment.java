package org.gowoon.inum.fragment;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterDialogTwoButton;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPhoneChangeFragment extends Fragment {
    Button btnChangeTel;
    TextView tvCurrentTel;
    EditText editNewTel;
    AdapterDialogTwoButton dialog;
    String password,userTel,newTel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_setting_phone_change, container, false);

        final SharedPreferences pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        password = pref.getString("userpw","");
        userTel = getArguments().getString("tel");

        tvCurrentTel = rootview.findViewById(R.id.tv_setting_phonechange_current_num);
        tvCurrentTel.setText(userTel);
        editNewTel = rootview.findViewById(R.id.etv_setting_phonechange_newnum);
        dialog = new AdapterDialogTwoButton(getActivity(),"확인을 누르시면 새로운\n전화번호로 변경됩니다.");

        btnChangeTel = rootview.findViewById(R.id.btn_setting_phone_change);
        btnChangeTel.setOnClickListener(view -> {
            newTel = editNewTel.getText().toString();
            if (newTel.length() == 11){
                rootview.findViewById(R.id.tv_setting_phonechange_noinput).setVisibility(View.INVISIBLE);
                dialog.show();

                dialog.setOnOkButtonClickListener(() -> {
                    Log.d("newTel", newTel);
                    Singleton.retrofit.changeTel(pref.getString("token",""), "0" ,newTel).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.code()==200){
                                userTel = newTel;
                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                fragmentManager.beginTransaction()
                                        .remove(SettingPhoneChangeFragment.this).commit();
                                Toast.makeText(getActivity(),"전화번호 변경 성공!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getActivity(),"전화번호 변경 실패",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(getActivity(),"서버 연결을 확인해주세요",Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
            else {
                rootview.findViewById(R.id.tv_setting_phonechange_noinput).setVisibility(View.VISIBLE);
            }
        });
        
        return rootview;
    }

}
