package org.gowoon.inum.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Iterator;
import java.util.List;

import static org.gowoon.inum.R.*;
import static org.gowoon.inum.R.layout.*;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    public AdapterViewPagerSignUp vAdapter;
    public Button btnNext;
    public NonSwipeViewPager viewPager;
    public int curr;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        mView = View.inflate(container.getContext(), R.layout.activity_signup, null);
//        showSetpView();
//        return mView;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_signup);

        vAdapter = new AdapterViewPagerSignUp(getSupportFragmentManager());
        viewPager = findViewById(id.viewpager_sign_up);
        setViewPager(viewPager);
        TextView tvTitle = findViewById(id.tv_sign_up_title);
        tvTitle.setOnClickListener(this);
    }

    public void stepView(int step1, int step2, int step3, int step4, int step5){

        curr = viewPager.getCurrentItem();

        com.baoyachi.stepview.HorizontalStepView stepView = findViewById(id.step_view_sign_up);
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
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(SignUpActivity.this, color.cloudy_blue))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(SignUpActivity.this, color.cloudy_blue))
                .setStepViewComplectedTextColor(ContextCompat.getColor(SignUpActivity.this, color.gunmetal))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(SignUpActivity.this, color.gunmetal))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(SignUpActivity.this, drawable.complted))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(SignUpActivity.this, drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(SignUpActivity.this, drawable.attention));
    }

    public void initViewSignUp(String title){
            TextView tvTitle = findViewById(id.tv_sign_up_title);
            tvTitle.setText(title);
    }

    @Override
    public void onBackPressed() {
        curr = viewPager.getCurrentItem();
        int prev = curr-1;
        if (curr == 1 ){
            super.onBackPressed();
        }
        else if (curr>0){
            viewPager.setCurrentItem(prev,true); }
    }

    public void setViewPager(ViewPager viewPager){
//        fragmentArrayList.add(0, new SignUpAgreementFragment());
//        fragmentArrayList.add(1, new SignUpInfoFragment());
//        fragmentArrayList.add(2, new SignUpPwFragment());
//        fragmentArrayList.add(3, new SignUpPhoneFragment());
//        fragmentArrayList.add(4, new SignUpReviewFragment());
//        fragmentArrayList.add(5, new SignUpCompleteFragment());

        vAdapter.addFragment(new SignUpAgreementFragment(),1);
        vAdapter.addFragment(new SignUpInfoFragment(),2);
        vAdapter.addFragment(new SignUpPwFragment(),3);
        vAdapter.addFragment(new SignUpPhoneFragment(),4);
        vAdapter.addFragment(new SignUpReviewFragment(),5);
        vAdapter.addFragment(new SignUpCompleteFragment(),6);

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
    }
}
