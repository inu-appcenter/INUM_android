package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.model.UserData;
import org.gowoon.inum.util.Singleton;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class signup_reviewFragment extends Fragment {

    private TextView txt_name, txt_stdnum, txt_phonenum;
    Button nextstep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_review, container, false);

        txt_name = rootview.findViewById(R.id.txt_signup_stdname);
        txt_stdnum = rootview.findViewById(R.id.txt_signup_stdnum);
        txt_phonenum = rootview.findViewById(R.id.txt_signup_phone_num);

        nextstep = rootview.findViewById(R.id.btn_signup_phone_nextstep);

        txt_name.setText(UserData.getInstance().getName());
        txt_stdnum.setText(UserData.getInstance().getSchoolID());
        txt_phonenum.setText(UserData.getInstance().getPhone());

        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO
                //HashMap 코드수정
                Map<String, Object> info = new HashMap<>();
                info.put("id", UserData.getInstance().getSchoolID());
                info.put("passwd", UserData.getInstance().getPw());
                info.put("name", UserData.getInstance().getName());
                info.put("tel", UserData.getInstance().getPhone());
                Log.e("벨류값", "" + info.values());

                //변수에 값 저장 후 레트로핏으로 전송
                Singleton.retrofit.account(UserData.getInstance().getSchoolID(), UserData.getInstance().getPw()
                        , UserData.getInstance().getName(), UserData.getInstance().getPhone())
                        .enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if (response.isSuccessful()) {
                                    JsonObject result = response.body();
                                    Log.d("account", "" + result);

                                    // result "ans"가 false 일 때도 설정
                                    if (String.valueOf(result.get("ans")).equals("true")) {
                                        getFragmentManager().beginTransaction()
                                                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                                                .replace(R.id.container_signup, new signup_completeFragment())
                                                .commit();
                                    }
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