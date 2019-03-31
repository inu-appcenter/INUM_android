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
import org.gowoon.inum.custom.Adapter_dialog_twobutton;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPhoneChangeFragment extends Fragment {
    Button btn_changephone;
    TextView tv_currentnum, tv_noinputerr;
    EditText etv_newnum;
    Adapter_dialog_twobutton dialog;
    String id,userTel,newTel;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_setting_phone_change, container, false);

        final SharedPreferences pref = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        id = pref.getString("userid","");
        userTel = pref.getString("usertel","");

        tv_currentnum = rootview.findViewById(R.id.tv_setting_phonechange_current_num);
        tv_currentnum.setText(userTel);
        etv_newnum = rootview.findViewById(R.id.etv_setting_phonechange_newnum);
        dialog = new Adapter_dialog_twobutton(getActivity(),"확인을 누르시면 새로운\n전화번호로 변경됩니다.");

        btn_changephone = rootview.findViewById(R.id.btn_setting_phone_change);
        btn_changephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTel = etv_newnum.getText().toString();
                if (newTel.length() == 11){
                    rootview.findViewById(R.id.tv_setting_phonechange_noinput).setVisibility(View.INVISIBLE);
                    dialog.show();

                    dialog.setOnOkButtonClickListener(new Adapter_dialog_twobutton.OnOkButtonClickListener() {
                        @Override
                        public void onClick() {
                            Log.d("newTel", newTel);

                            Singleton.retrofit.changeTel(id ,newTel).enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    if(response.isSuccessful()){
                                        assert response.body() != null;
                                        JsonObject result = response.body();
                                        Log.d("changeTel_test",""+result);
                                        if(String.valueOf(result.get("ans")).equals("true")){
                                            if(!userTel.equals(newTel)){
                                                editor = pref.edit();
                                                editor.putString("tel", newTel);
                                                editor.apply();
                                                tv_currentnum.setText(newTel);
                                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                                fragmentManager.beginTransaction()
                                                        .remove(SettingPhoneChangeFragment.this).commit();
                                                Toast.makeText(getActivity(),"전화번호 변경 성공!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(getActivity(),"전화번호 변경 실패",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }
                    });


                }
                else {
                    rootview.findViewById(R.id.tv_setting_phonechange_noinput).setVisibility(View.VISIBLE);
                }
            }
        });


        return rootview;
    }

}
