package org.gowoon.inum.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;
import org.gowoon.inum.model.UserData;

public class SignUpPhoneFragment extends Fragment {

    private EditText phonenum;
    private TextView errormsg;
    String phone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((SignUpActivity) getActivity()).initViewSignUp("휴대폰 인증");
        ((SignUpActivity)getActivity()).stepView(1,1,1,0,-1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_phone, container, false);




        phonenum = rootview.findViewById(R.id.et_sign_up_phone);
        errormsg = rootview.findViewById(R.id.tv_sign_up_phone_error);
        rootview.findViewById(R.id.btn_sign_up_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = phonenum.getText().toString();
                if(phone.length() == 11){
                   UserData.getInstance().setPhone(phone);
                   errormsg.setVisibility(View.INVISIBLE);
                    ((SignUpActivity)getActivity()).setViewPagerNext();
                }
                else {
                errormsg.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootview;
    }
}