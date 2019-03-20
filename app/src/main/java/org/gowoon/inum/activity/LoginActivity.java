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
import org.gowoon.inum.fragment.forgotpwFragment;
import org.gowoon.inum.model.LoginResult;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView btn_login;
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

        checkBox_login = findViewById(R.id.checkbox_login);

        pref_info = getSharedPreferences("userinfo",MODE_PRIVATE);
        editor = pref_info.edit();

}

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
            {
                String FCM = "notwork";
                stdid = etv_stdid.getText().toString();
                pw = etv_pw.getText().toString();

                Singleton.retrofit.login(stdid,pw,FCM).enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if ((response.isSuccessful())&&(response.body() != null)){
                            LoginResult result = response.body();
                            if (result.getMessage().equals("logged in success")) {
                                if (checkBox_login.isChecked()){
                                    editor.putString("userid",stdid);
                                    editor.putString("userpw",pw);
                                    editor.putString("usertel",result.getTel());
                                    editor.putBoolean("checkboxlogin",checkBox_login.isChecked());
                                    editor.commit();
                                }

                                if (!pref_info.getString("token","").equals(result.getToken())){
                                    editor.putString("token",result.getToken());
                                    editor.putString("usertel",result.getTel());
                                    editor.putString("userid",stdid);
                                    editor.putString("userpw",pw);
                                    editor.putString("name",result.getName());
                                    editor.commit();
                                }

                                Log.d("login_result_msg",""+result.getMessage() + result.getToken());
                                tv_notcorrect.setVisibility(View.INVISIBLE);

                                Intent intent_login = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent_login);
                                finish();
                            }
                            else {
                                if (result.getMessage().equals("certification")) {
                                    Log.d("login_result_msg_Err","인증안됨");
                                    tv_notcorrect.setText("이메일 인증 후 로그인 해주세요");
                                }
                                else if (result.getMessage().equals("fail")){
                                    Log.d("login_fail_wrong","아이디비밀번호오류");
                                    tv_notcorrect.setText("*학번과 일치하는 패스워드가 아닙니다.");
                                    tv_notcorrect.setVisibility(View.VISIBLE);
                                }
                                tv_notcorrect.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Log.d("Loginfail","onFailure"+t);
                    }
                });
                break;
            }
            case R.id.tv_login_join:
            {
                Intent intent_signup = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent_signup);
                break;
            }
            case R.id.tv_login_findpw:
            {
                forgotpwFragment fragment = new forgotpwFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.login,fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            }
        }
    }
}
