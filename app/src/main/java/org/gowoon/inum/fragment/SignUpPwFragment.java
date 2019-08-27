package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyachi.stepview.bean.StepBean;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;
import org.gowoon.inum.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class SignUpPwFragment extends Fragment {

    private EditText etPassWd, etPassWdAgain;
    private TextView tvPwNoInput, tvPwAgainErr, tvPwLength, tvPwLengthErr, tvPwAgain;
    private Button nextstep;
    String passWd, passWdAgain;

    Boolean passWordOK = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        ((SignUpActivity) getActivity()).initViewSignUp("비밀번호 설정하기");
        ((SignUpActivity)getActivity()).stepView(1,1,0,-1,-1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_pw,container,false);



        viewSet(rootview);

        rootview.findViewById(R.id.btn_sign_up_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passWd = etPassWd.getText().toString().trim();
                passWdAgain = etPassWdAgain.getText().toString().trim();
                if ((passWd.length() == 0 )||(passWdAgain.length() ==0)){
                    tvPwNoInput.setVisibility(View.VISIBLE);
                }else{
                    if (passWd.length() >=8){
                        tvPwLength.setVisibility(View.VISIBLE);
                        tvPwLengthErr.setVisibility(View.INVISIBLE);

                        if (passWd.equals(passWdAgain)){
                            UserData.getInstance().setPw(passWd);
                            tvPwAgain.setVisibility(View.VISIBLE);
                            tvPwAgainErr.setVisibility(View.INVISIBLE);

                            setInfo(passWd);
                            ((SignUpActivity)getActivity()).setViewPagerNext();
                        }
                        else {
                            tvPwAgainErr.setVisibility(View.VISIBLE);
                            tvPwAgain.setVisibility(View.INVISIBLE);
                        }
                    }
                    else{
                        tvPwLength.setVisibility(View.INVISIBLE);
                        tvPwLengthErr.setVisibility(View.VISIBLE);
                    }
                }

            }
        });


//        if (pw.getText().toString().trim().equals(confirmpw.getText().toString().trim())&&((pw.getText().toString().trim().length() != 0)&&(confirmpw.getText().toString().trim().length() != 0))){
//            //비밀번호 일치
//            txt_pw.setVisibility(View.VISIBLE);
//            txt_confrimpw.setVisibility(View.VISIBLE);
//        }

//        nextstep = rootview.findViewById(R.id.btn_signup_pw_nextstep);
//        nextstep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(pw.getText().toString().trim().length()==0 || confirmpw.getText().toString().trim().length()==0){
//                    //비밀번호 미입력
//                    pw_message.setVisibility(View.VISIBLE);
//                    error_txt_confirmpw.setVisibility(View.INVISIBLE);
//                    error_txt_pw.setVisibility(View.INVISIBLE);
//                }
//                else if(!pw.getText().toString().trim().equals(confirmpw.getText().toString().trim())){
//                    //비밀번호 불일치
//                  error_txt_confirmpw.setVisibility(View.VISIBLE);
//                  error_txt_pw.setVisibility(View.INVISIBLE);
//                  pw_message.setVisibility(View.INVISIBLE);
//                }
//                else if (pw.getText().toString().length()<8){
//                    //비밀번호 8글자 미만
//                    error_txt_pw.setVisibility(View.VISIBLE);
//                    error_txt_confirmpw.setVisibility(View.INVISIBLE);
//                    pw_message.setVisibility(View.INVISIBLE);
//                }
//                else if(pw.getText().toString().trim().equals(confirmpw.getText().toString().trim()) && pw.getText().toString().length()>7){
//                    //비밀번호 일치,8글자 이상
//                    stdpw = pw.getText().toString();
//                    UserData.getInstance().setPw(stdpw);
////                    getFragmentManager().beginTransaction()
////                            .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
////                            .replace(R.id.container_signup, new SignUpPhoneFragment())
////                            .commit();
//                }
//            }
//        });
        return rootview;
    }

    private void viewSet(View root){
        etPassWd = root.findViewById(R.id.et_sign_up_pw);
        etPassWdAgain = root.findViewById(R.id.et_sign_up_pw_again);

        tvPwLength = root.findViewById(R.id.tv_sign_up_pw_length);
        tvPwLengthErr = root.findViewById(R.id.tv_sign_up_pw_length_error);

        tvPwAgain = root.findViewById(R.id.tv_sign_up_same);
        tvPwAgainErr = root.findViewById(R.id.tv_sign_up_not_same);

        tvPwNoInput = root.findViewById(R.id.tv_sign_up_pw_no_input);
    }

    private void setInfo(String pw){
        UserData.getInstance().setPw(pw);
    }
}