package org.gowoon.inum.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;
import org.gowoon.inum.custom.NonSwipeViewPager;

public class SignUpAgreementFragment extends Fragment {

    private NonSwipeViewPager viewPager;
    private int curr;
    private CheckBox checkBox;
    private TextView message;
    Button btn_nextstep;


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        ((SignUpActivity) getActivity()).initViewSignUp("회원가입 하기");
        ((SignUpActivity)getActivity()).stepView(0,-1,-1,-1,-1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



//        curr = viewPager.getCurrentItem();
//        int prev = curr-1;
//
//        else if (curr>0){
//            viewPager.setCurrentItem(prev,true);

            View rootview = inflater.inflate(R.layout.fragment_signup_argeement, container, false);


        checkBox = rootview.findViewById(R.id.checkbox_signup_agree);
        message = rootview.findViewById(R.id.signup_non_agree);

        rootview.findViewById(R.id.btn_sign_up_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    message.setVisibility(View.INVISIBLE);
                    ((SignUpActivity)getActivity()).setViewPagerNext();
                }else {
                    message.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootview;
    }
}