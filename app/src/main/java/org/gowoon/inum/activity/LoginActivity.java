package org.gowoon.inum.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.retrofit_login;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login;
    public EditText etv_stdid, etv_pw;
    private TextView tv_notcorrect, tv_join, tv_findpw;
    public String stdid, pw;
    private CheckBox checkBox_login;

    public SharedPreferences pref_info;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etv_pw = findViewById(R.id.etv_login_pw);
        etv_stdid = findViewById(R.id.etv_login_stdid);

        tv_notcorrect = findViewById(R.id.tv_login_notcorrect);
        tv_join = findViewById(R.id.tv_login_join);
        tv_join.setOnClickListener(this);

        tv_findpw = findViewById(R.id.tv_login_findpw);
        tv_findpw.setOnClickListener(this);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        stdid = etv_stdid.getText().toString();
        pw = etv_pw.getText().toString();

        checkBox_login = findViewById(R.id.checkbox_login);

        pref_info = getSharedPreferences("userinfo",MODE_PRIVATE);
        editor = pref_info.edit();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
            {
                Singleton.retrofit.login(stdid,pw,"notwork").enqueue(new Callback<retrofit_login>() {
                    @Override
                    public void onResponse(Call<retrofit_login> call, Response<retrofit_login> response) {
                        if (response.isSuccessful()){
                            retrofit_login result = response.body();
                            if (result != null){
                             if ((result.getToken().length()!=0)&&(result.getMessage().equals("logged in success"))) {
                              if (!pref_info.getString("token","").equals(result.getToken())){
                                  editor.putString("token",result.getToken());
                                  editor.commit();
                              }
                             }
                                Log.d("login_result_msg",""+result.message + result.token);
                                tv_notcorrect.setVisibility(View.INVISIBLE);

                                Intent intent_login = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent_login);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<retrofit_login> call, Throwable t) {

                    }
                });
            }
            case R.id.tv_login_join:
            {

            }
            case R.id.tv_login_findpw:
            {

            }
        }
    }
}
