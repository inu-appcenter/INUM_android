package org.gowoon.inum.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.WindowManager;

import org.gowoon.inum.R;

public class SplashActivity extends AppCompatActivity {

    private Layout layout;
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    public SharedPreferences pref_info;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        pref_info = getSharedPreferences("userinfo",MODE_PRIVATE);
        editor = pref_info.edit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pref_info.getBoolean("checkboxlogin", Boolean.parseBoolean(""))){
                    Intent intent_splash = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent_splash);
                    SplashActivity.this.finish();
                }
                else {
                    Intent intent_splash_login = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent_splash_login);
                    SplashActivity.this.finish();
                }
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
