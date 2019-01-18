package org.gowoon.inum.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.WindowManager;

import org.gowoon.inum.R;

public class SplashActivity extends AppCompatActivity {

    private Layout layout;
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_splash = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent_splash);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
