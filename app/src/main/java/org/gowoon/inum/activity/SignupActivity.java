package org.gowoon.inum.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.forgotpwFragment;
import org.gowoon.inum.fragment.signup_argeementFragment;
import org.gowoon.inum.fragment.signup_completeFragment;
import org.gowoon.inum.fragment.signup_phoneFragment;
import org.gowoon.inum.fragment.signup_pwFragment;
import org.gowoon.inum.fragment.signup_reviewFragment;
import org.gowoon.inum.fragment.signup_stdinfoFragment;

public class SignupActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singnup);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_signup, new signup_argeementFragment())
                .commit();
    }

    //TODO
    // 회원가입 관련 edittext 전무 enter키 막기 - 줄수 최대 한 줄
    public void changetFragment(signup_pwFragment signup_pwFragment) {
    }

    public void changetFragment(signup_phoneFragment signup_phoneFragment) {
    }

    public void changetFragment(signup_stdinfoFragment signup_stdinfoFragment) {
    }

    public void changetFragment(signup_reviewFragment signup_reviewFragment) {
    }

    public void changetFragment(signup_completeFragment signup_completeFragment) {
    }
}
