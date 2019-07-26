package org.gowoon.inum.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterViewPagerSignUp;
import org.gowoon.inum.fragment.SignUpAgreementFragment;
import org.gowoon.inum.fragment.SignUpCompleteFragment;
import org.gowoon.inum.fragment.SignUpInfoFragment;
import org.gowoon.inum.fragment.SignUpPhoneFragment;
import org.gowoon.inum.fragment.SignUpPwFragment;
import org.gowoon.inum.fragment.SignUpReviewFragment;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    public AdapterViewPagerSignUp vAdapter;
    public Button btnNext;
    public ViewPager viewPager;
    public int curr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        vAdapter = new AdapterViewPagerSignUp(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager_sign_up);
        setViewPager(viewPager);

        TextView tvTitle = findViewById(R.id.tv_sign_up_title);
        tvTitle.setOnClickListener(this);

//        btnNext.setOnClickListener(this);

    }

    public void initViewSignUp(String title, String next){
        TextView tvTitle = findViewById(R.id.tv_sign_up_title);

        tvTitle.setText(title);

//        btnNext = findViewById(R.id.btn_sign_up_next);
//        btnNext.setText(next);
    }

    @Override
    public void onBackPressed() {
        curr = viewPager.getCurrentItem();
        int prev = curr-1;
        if (curr == 0 ){
            super.onBackPressed();
        }
        else if (curr>0){
            viewPager.setCurrentItem(prev,true); }
    }
    //TODO
    // 회원가입 관련 edittext 전무 enter키 막기 - 줄수 최대 한 줄
//    public void changetFragment(signup_pwFragment signup_pwFragment) {
//    }
//
//    public void changetFragment(signup_phoneFragment signup_phoneFragment) {
//    }
//
//    public void changetFragment(signup_stdinfoFragment signup_stdinfoFragment) {
//    }
//
//    public void changetFragment(signup_reviewFragment signup_reviewFragment) {
//    }
//
//    public void changetFragment(signup_completeFragment signup_completeFragment) {
//    }

    public void setViewPager(ViewPager viewPager){
        vAdapter.addFragment(new SignUpAgreementFragment(),0);
        vAdapter.addFragment(new SignUpInfoFragment(),1);
        vAdapter.addFragment(new SignUpPwFragment(),2);
        vAdapter.addFragment(new SignUpPhoneFragment(),3);
        vAdapter.addFragment(new SignUpReviewFragment(),4);
        vAdapter.addFragment(new SignUpCompleteFragment(),5);

        curr = viewPager.getCurrentItem();
        switch (curr){
            case 0:{
                initViewSignUp("회원가입 하기", "다음");
                break;
            }
            case 1:{
                initViewSignUp("학생정보 입력하기", "다음");
                break;
            }
            case 2:{
                initViewSignUp("비밀번호 설정하기", "다음");
                break;
            }
            case 3:{
                initViewSignUp("휴대폰 인증", "다음");
                break;
            }
            case 4:{
                initViewSignUp("회원 가입 완료하기", "완료");
                break;
            }
        }

        viewPager.setAdapter(vAdapter);
    }

    public void setViewPagerNext(){
        curr = viewPager.getCurrentItem();
        int last = vAdapter.getCount()-1;
        int next = curr+1;
        if (curr == last){
            finish();
        }
        else {
            viewPager.setCurrentItem(next);
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "click",Toast.LENGTH_SHORT).show();
        setViewPagerNext();
    }
}
