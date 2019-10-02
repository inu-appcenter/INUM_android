package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;

public class SignUpAgreementFragment extends Fragment {

    private CheckBox checkBox;
    private TextView message;
    Button btn_nextstep;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((SignUpActivity) getActivity()).initViewSignUp("회원가입 하기");
        ((SignUpActivity)getActivity()).stepView(0,-1,-1,-1,-1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_argeement, container, false);

        checkBox = rootview.findViewById(R.id.checkbox_signup_agree);
        message = rootview.findViewById(R.id.signup_non_agree);

        rootview.findViewById(R.id.btn_sign_up_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    message.setVisibility(View.INVISIBLE);
//                    getFragmentManager().beginTransaction().replace(R.id.container_signup,new SignUpInfoFragment()).commit();
                    ((SignUpActivity)getActivity()).setViewPagerNext();
                }else {
                    message.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootview;
    }
}