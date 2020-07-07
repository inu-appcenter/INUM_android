package org.gowoon.inum.fragment;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterDialogTwoButton;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingSecessionFragment extends Fragment {
    EditText id, pw;
    Button btnSecession;
    TextView noinput, incorrect;
    Boolean i ,j;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_setting_secession, container, false);

        final SharedPreferences pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        final String userId = pref.getString("userid","");
        final String userPw = pref.getString("userpw","");

        id = rootview.findViewById(R.id.et_setting_secession_studentid);
        pw = rootview.findViewById(R.id.et_setting_secession_pw);

        noinput = rootview.findViewById(R.id.tv_setting_secession_noinput);
        incorrect = rootview.findViewById(R.id.tv_setting_secession_incorrect);

        final AdapterDialogTwoButton dialog_out
                = new AdapterDialogTwoButton(getActivity(),"확인을 누르시면 회원 탈퇴됩니다.");

        btnSecession = rootview.findViewById(R.id.btn_setting_secession);
        btnSecession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((id.getText().toString().length() ==0)||(pw.getText().toString().length()==0)){
                    noinput.setVisibility(View.VISIBLE);
                    incorrect.setVisibility(View.INVISIBLE);
                }
                else {
                    if ((id.getText().toString().trim().equals(userId))&&(pw.getText().toString().trim().equals(userPw))){
                        dialog_out.show();
                    }
                    else{
                        incorrect.setVisibility(View.VISIBLE);
                        noinput.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        dialog_out.setOnOkButtonClickListener(new AdapterDialogTwoButton.OnOkButtonClickListener() {
            @Override
            public void onClick() {
                Singleton.retrofit.secession(userId, userPw).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        assert response.body() != null;
                        JsonObject result = response.body();
                        if(String.valueOf(result.get("ans")).equals("true")){
                            editor = pref.edit();
                            editor.clear();
                            editor.apply();
                            FragmentManager fragmentManager = getActivity().getFragmentManager();
                            fragmentManager.beginTransaction()
                                    .remove(SettingSecessionFragment.this).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
        return rootview;
    }

}
