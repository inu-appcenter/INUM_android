package org.gowoon.inum.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_dialog_onebutton;
import org.gowoon.inum.custom.Adapter_dialog_twobutton;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SettingPwChangeFragment extends Fragment {

    TextView noinput, diff, ok , same, less8;
    EditText et_currentpw, et_newpw, et_newpwagain;
    Boolean length,same_beforepw;
    String id,pw;
    SharedPreferences pref;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup)inflater.inflate(R.layout.fragment_setting_pw_change, container, false);

        length = true;

        et_currentpw = rootview.findViewById(R.id.et_setting_pwchange_currentpw);
        noinput =rootview.findViewById(R.id.tv_setting_pwchange_newpw_noinput);
        diff = rootview.findViewById(R.id.tv_setting_pwchange_newpw_diff);
        ok = rootview.findViewById(R.id.tv_setting_pwchange_newpw_ok);
        same = rootview.findViewById(R.id.tv_setting_pwchange_newpw_same);
        less8 = rootview.findViewById(R.id.tv_setting_pwchange_newpw_less8);

        et_currentpw = rootview.findViewById(R.id.et_setting_pwchange_currentpw);
        et_newpw =rootview.findViewById(R.id.et_setting_pwchange_newpw);
        et_newpwagain = rootview.findViewById(R.id.et_setting_pwchange_newpw_again);

        pref = getActivity().getSharedPreferences("userinfo",MODE_PRIVATE);
        id = pref.getString("id","");
        pw = pref.getString("pw","");

        et_newpw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()<8) {
                    less8.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.INVISIBLE);
                    length = false;
                }
                else {
                    less8.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    length = true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_newpwagain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (length) {
                    if (s.toString().equals(et_newpw.getText().toString())) {
                        same.setVisibility(View.VISIBLE);
                        diff.setVisibility(View.INVISIBLE);
                        same_beforepw = true;
                    } else {
                        diff.setVisibility(View.VISIBLE);
                        same.setVisibility(View.INVISIBLE);
                        same_beforepw = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rootview.findViewById(R.id.btn_setting_pwchange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((et_currentpw.getText().toString().equals(pw))){
                    if((same_beforepw)&&(length)){
                        //noinput.setVisibility(View.VISIBLE);
                        Adapter_dialog_twobutton dialog = new Adapter_dialog_twobutton(getActivity(),"확인을 누르시면 새로운\n비밀번호로 변경됩니다.");
                        dialog.setOnOkButtonClickListener(new Adapter_dialog_twobutton.OnOkButtonClickListener() {
                            @Override
                            public void onClick() {
                                final String st_newpw = et_newpw.getText().toString();

                                Singleton.retrofit.changePasswd(id ,pw ,st_newpw).enqueue(new Callback<JsonObject>() {
                                    @Override
                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                        if(response.isSuccessful()){
                                            assert response.body() != null;
                                            JsonObject result = response.body();
                                            if(String.valueOf(result.get("ans")).equals("true")){
                                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                                fragmentManager.beginTransaction().remove(SettingPwChangeFragment.this).commit();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<JsonObject> call, Throwable t) {
                                    }
                                });
                            }
                        });

                        dialog.show();}
                }
                else{
                    Adapter_dialog_onebutton dialog_incorrect
                            = new Adapter_dialog_onebutton(getActivity(),"현재 비밀번호가\n일치하지 않습니다.");
                    dialog_incorrect.show();
                }
            }
        });

        return rootview;
    }
}
