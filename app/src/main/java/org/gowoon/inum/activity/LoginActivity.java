package org.gowoon.inum.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.forgotpwFragment;
import org.gowoon.inum.util.Singleton;

import java.util.Objects;

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
                Log.d("login click",stdid + pw);

                Singleton.retrofit.signIn(stdid,pw,FCM).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if ((response.isSuccessful())&&(response.body() != null)){
                            switch (response.code()){
                                case 200:{
                                    String userToken = String.valueOf(response.body().get("token"));
                                    if (checkBox_login.isChecked()){
                                        editor.putString("userid",stdid);
                                        editor.putString("userpw",pw);
                                        editor.putBoolean("checkboxlogin",checkBox_login.isChecked());
                                        editor.commit();
                                    }

                                    userToken = userToken.substring(1,userToken.length()-1);
                                    if (!Objects.equals(pref_info.getString("token", ""), userToken)){
                                        editor.putString("token", userToken);
                                        editor.putString("userid",stdid);
                                        editor.putString("userpw",pw);
                                        editor.commit();
                                    }

                                    Log.d("login_result_msg",response.message() + userToken);
                                    tv_notcorrect.setVisibility(View.INVISIBLE);

                                    Intent intent_login = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent_login);
                                    finish();

                                    break;
                                }
                                case 400:{
                                    tv_notcorrect.setVisibility(View.VISIBLE);
                                    String signInAns = String.valueOf(response.errorBody());
                                    if(signInAns.equals("password")){
                                        Log.d("login_fail_wrong","아이디비밀번호오류");
                                        tv_notcorrect.setText("*학번과 일치하는 패스워드가 아닙니다.");
                                    }
                                    if(signInAns.equals("certification")){
                                        Log.d("login_result_msg_Err","인증안됨");
                                        tv_notcorrect.setText("이메일 인증 후 로그인 해주세요");
                                        Toast.makeText(LoginActivity.this, "포탈 이메일 인증 후 로그인 해주세요", Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("signInFail","onFailure"+t);
                        Toast.makeText(LoginActivity.this, "서버 연결을 확인해주세요", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            }
            case R.id.tv_login_join:
            {
                Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentSignUp);
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
