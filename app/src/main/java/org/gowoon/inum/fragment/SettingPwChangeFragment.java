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
import android.widget.Toast;

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

    TextView noInput, diff, ok , same, less8;
    EditText etCurrentPw, etNewPw, etNewPwAgain;
    Boolean length,sameBefore, sameCurrent;
    String id,pw,currentPw;
    SharedPreferences pref;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview =(ViewGroup)inflater.inflate(R.layout.fragment_setting_pw_change, container, false);

        length = Boolean.FALSE;
        sameBefore = Boolean.FALSE;
        sameCurrent = Boolean.FALSE;

        etCurrentPw = rootview.findViewById(R.id.et_setting_pwchange_currentpw);
        noInput =rootview.findViewById(R.id.tv_setting_pwchange_newpw_noinput);
        diff = rootview.findViewById(R.id.tv_setting_pwchange_newpw_diff);
        ok = rootview.findViewById(R.id.tv_setting_pwchange_newpw_ok);
        same = rootview.findViewById(R.id.tv_setting_pwchange_newpw_same);
        less8 = rootview.findViewById(R.id.tv_setting_pwchange_newpw_less8);

        etNewPw = rootview.findViewById(R.id.et_setting_pwchange_newpw);
        etNewPwAgain = rootview.findViewById(R.id.et_setting_pwchange_newpw_again);

        pref = getActivity().getSharedPreferences("userinfo",MODE_PRIVATE);
        id = pref.getString("userid","");
        pw = pref.getString("userpw","");

        etNewPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()<8) {
                    if (s.toString().length()==0){
                        noInput.setText("* 변경할 비밀번호를 입력해야 합니다.");
                        noInput.setVisibility(View.VISIBLE);
                    }else {
                        noInput.setVisibility(View.INVISIBLE);
                        less8.setVisibility(View.VISIBLE);
                        ok.setVisibility(View.INVISIBLE);
                        length = false;
                    }
                }
                else {
                    less8.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    length = true;
                }
            }
        });

        etNewPwAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (length){
                    if (s.toString().equals(etNewPw.getText().toString())) {
                        same.setVisibility(View.VISIBLE);
                        diff.setVisibility(View.INVISIBLE);
                        sameCurrent = true;
                    } else {
                        diff.setVisibility(View.VISIBLE);
                        same.setVisibility(View.INVISIBLE);
                        sameCurrent = false;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        rootview.findViewById(R.id.btn_setting_pwchange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPw = etCurrentPw.getText().toString().trim();
                if (pw.equals(currentPw)) {
                    if (sameCurrent && length) {
                        Adapter_dialog_twobutton dialog = new Adapter_dialog_twobutton(getActivity(), "확인을 누르시면 새로운\n 비밀번호로 변경됩니다.");
                        dialog.show();
                        dialog.setOnOkButtonClickListener(new Adapter_dialog_twobutton.OnOkButtonClickListener() {
                            @Override
                            public void onClick() {
                                String newPw = etNewPwAgain.getText().toString().trim();
                                Singleton.retrofit.changePasswd(id, pw, newPw).enqueue(new Callback<JsonObject>() {
                                    @Override
                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                        if (response.isSuccessful()) {
                                            JsonObject result = response.body();
                                            if (String.valueOf(result.get("ans")).equals("true")) {
                                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                                fragmentManager.beginTransaction().remove(SettingPwChangeFragment.this).commit();
                                                Toast.makeText(getActivity(), "비밀번호 변경 성공!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), "비밀번호 변경 실패", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<JsonObject> call, Throwable throwable) {

                                    }
                                });
                            }
                        });
                    }
                }else {
                    Adapter_dialog_onebutton dialogInCorrect = new Adapter_dialog_onebutton(getActivity(), "현재 비밀번호가\n일치하지 않습니다.");
                    dialogInCorrect.show();
                }
            }
        });
        return rootview;
    }
}
