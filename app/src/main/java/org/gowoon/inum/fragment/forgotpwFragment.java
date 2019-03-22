package org.gowoon.inum.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
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
import org.gowoon.inum.custom.Adapter_dialog_onebutton;
import org.gowoon.inum.util.Singleton;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class forgotpwFragment extends Fragment {

    private EditText etv_name, etv_stdid;
    Button btn_find;
    TextView tv_err;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_forgotpw, container, false);

        etv_name = rootview.findViewById(R.id.etv_forgotpw_name);
        etv_stdid = rootview.findViewById(R.id.etv_forgotpw_id);
        btn_find = rootview.findViewById(R.id.btn_forgotpw);
        tv_err = rootview.findViewById(R.id.tv_forgotpw_noinput);

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etv_name.getText().toString();
                String stdid = etv_stdid.getText().toString();
                if ((name.length() > 2) && (stdid.length() == 9)) {
                    Singleton.retrofit.forgotPw(stdid,name).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()){
                                assert response.body() != null;
                                JsonObject result = response.body();
                                Log.d("비밀번호 찾기",""+result);
                                if(String.valueOf(result.get("ans")).equals("true")){
                                    tv_err.setVisibility(View.INVISIBLE);
                                    Adapter_dialog_onebutton dialog = new Adapter_dialog_onebutton(getActivity(),"인천대 포탈 웹메일로\n임시 비밀번호가 발송되었습니다!");
                                    dialog.show();

                                    dialog.setOnOkButtonClickListener(new Adapter_dialog_onebutton.OnOkButtonClickListener() {
                                        @Override
                                        public void onClick() {
                                            FragmentManager fragmentManager = getActivity().getFragmentManager();
                                            fragmentManager.beginTransaction()
                                                    .remove(forgotpwFragment.this).commit();
                                        }
                                    });
                                }
                                else {
                                    tv_err.setText("*입력하신 정보가 일치하지 않습니다");
                                    tv_err.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(getActivity(),"서버연결을 확인해 주세요",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else
                    tv_err.setText("*이름과 학번을 입력해주세요");
                tv_err.setVisibility(View.VISIBLE);
            }
        });

        return rootview;
    }
}
