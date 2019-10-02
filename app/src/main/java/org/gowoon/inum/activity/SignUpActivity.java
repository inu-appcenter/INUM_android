package org.gowoon.inum.activity;

import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.bean.StepBean;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterViewPagerSignUp;
import org.gowoon.inum.custom.NonSwipeViewPager;
import org.gowoon.inum.fragment.SignUpAgreementFragment;
import org.gowoon.inum.fragment.SignUpCompleteFragment;
import org.gowoon.inum.fragment.SignUpInfoFragment;
import org.gowoon.inum.fragment.SignUpPhoneFragment;
import org.gowoon.inum.fragment.SignUpPwFragment;
import org.gowoon.inum.fragment.SignUpReviewFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    public AdapterViewPagerSignUp vAdapter;
    public Button btnNext;
    public NonSwipeViewPager viewPager;
    public int curr;
    public boolean isBack;
    public int position;
    private String title[] = new String[]{"회원 가입하기","학생정보 입력하기","비밀번호 설정하기","휴대폰 인증","회원 가입 완료하기"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        vAdapter = new AdapterViewPagerSignUp(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager_sign_up);
        setViewPager(viewPager);

        TextView tvTitle = findViewById(R.id.tv_sign_up_title);
        tvTitle.setOnClickListener(this);
    }


    public void stepView(int step1, int step2, int step3, int step4, int step5){

        com.baoyachi.stepview.HorizontalStepView stepView = findViewById(R.id.step_view_sign_up);
        List<StepBean> stepsBeanList = new ArrayList<>();
        com.baoyachi.stepview.bean.StepBean stepBean0 = new StepBean("약관 동의",step1);
        com.baoyachi.stepview.bean.StepBean stepBean1 = new StepBean("학생 정보",step2);
        com.baoyachi.stepview.bean.StepBean stepBean2 = new StepBean("비밀 번호",step3);
        com.baoyachi.stepview.bean.StepBean stepBean3 = new StepBean("휴대폰 번호",step4);
        com.baoyachi.stepview.bean.StepBean stepBean4 = new StepBean("정보 리뷰",step5);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);
        stepView.setStepViewTexts(stepsBeanList)
                .setTextSize(10)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(SignUpActivity.this, R.color.cloudy_blue))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(SignUpActivity.this, R.color.cloudy_blue))
                .setStepViewComplectedTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.gunmetal))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(SignUpActivity.this, R.color.gunmetal))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.attention));
    }

    @Override
    public void onBackPressed() {
//        if(pageStack.empty()){
//            super.onBackPressed();
//        }
//        else{
//            isBack=true;
//            int index = pageStack.pop().intValue();
//            viewPager.setCurrentItem(index);
//            isBack = false;
//        }
        curr = viewPager.getCurrentItem();
        int prev = curr-1;
        if (curr == 0 ){
            super.onBackPressed();
        }
        else if (curr>0){
            viewPager.setCurrentItem(prev,true); }
    }
//
    public void initViewSignUp(int position){ }
    public void setViewPager(ViewPager viewPager){
////        fragmentArrayList.add(0, new SignUpAgreementFragment());
////        fragmentArrayList.add(1, new SignUpInfoFragment());
////        fragmentArrayList.add(2, new SignUpPwFragment());
////        fragmentArrayList.add(3, new SignUpPhoneFragment());
////        fragmentArrayList.add(4, new SignUpReviewFragment());
////        fragmentArrayList.add(5, new SignUpCompleteFragment());

        vAdapter.addFragment(new SignUpAgreementFragment(),0);
        vAdapter.addFragment(new SignUpInfoFragment(),1);
        vAdapter.addFragment(new SignUpPwFragment(),2);
        vAdapter.addFragment(new SignUpPhoneFragment(),3);
        vAdapter.addFragment(new SignUpReviewFragment(),4);
        vAdapter.addFragment(new SignUpCompleteFragment(),5);

        curr = viewPager.getCurrentItem();
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

//        switch (curr){
//            case 0:{
//                initViewSignUp("회원가입 하기");
//                break;
//            }
//            case 1:{
//                initViewSignUp("학생정보 입력하기");
//                break;
//            }
//            case 2:{
//                initViewSignUp("비밀번호 설정하기");
//                break;
//            }
//            case 3:{
//                initViewSignUp("휴대폰 인증");
//                break;
//            }
//            case 4:{
//                initViewSignUp("회원 가입 완료하기");
//                break;
//            }
//        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "click",Toast.LENGTH_SHORT).show();
        setViewPagerNext();

        TextView tvTitle = findViewById(R.id.tv_sign_up_title);
        position = viewPager.getCurrentItem();
        switch(position){
            case 0:
                tvTitle.setText(title[position]);
                stepView(0,-1,-1,-1,-1);
            case 1:
                tvTitle.setText(title[position]);
                stepView(1,0,-1,-1,-1);
            case 2:
                tvTitle.setText(title[position]);
                stepView(1,1,0,-1,-1);
            case 3:
                tvTitle.setText(title[position]);
                stepView(1,1,1,0,-1);
            case 4:
                tvTitle.setText(title[position]);
                stepView(1,1,1,1,0);
        }
    }
}
