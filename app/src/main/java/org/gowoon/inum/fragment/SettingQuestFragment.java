package org.gowoon.inum.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterDialogTwoButton;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingQuestFragment extends Fragment {
    EditText etRequest;
    String request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_setting_quest, container, false);
        final AdapterDialogTwoButton dialog_send = new AdapterDialogTwoButton(getActivity(),"확인을 누르시면\n문의사항이 전송됩니다.");

        etRequest = rootview.findViewById(R.id.et_setting_question);

        Log.d("moonhee",request);
        rootview.findViewById(R.id.btn_question_send).setOnClickListener(v -> {
                    request = etRequest.getText().toString();
                    rootview.findViewById(R.id.tv_setting_question_noinput).setVisibility(View.INVISIBLE);
                    dialog_send.show();
                });

        dialog_send.setOnOkButtonClickListener(() -> {
            final SharedPreferences pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            String token = pref.getString("token","");
            Singleton.retrofit.report(token,"moonHee",request).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.code()==200){
                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        fragmentManager.beginTransaction()
                                .remove(SettingQuestFragment.this).commit();
                    }
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        });
        return rootview;
    }

}
