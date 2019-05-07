package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignupActivity;
import org.gowoon.inum.model.UserData;

public class signup_pwFragment extends Fragment {

    private EditText pw, confirmpw;
    private TextView error_txt_pw, error_txt_confirmpw, txt_pw, txt_confrimpw, pw_message;
    private Button nextstep;
    String stdpw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_pw,container,false);

        pw_message = rootview.findViewById(R.id.txt_pw_message);

        pw = rootview.findViewById(R.id.etv_signup_pw);
        confirmpw = rootview.findViewById(R.id.etv_signup_confirmpw);

        error_txt_pw = rootview.findViewById(R.id.txt_pwlength_error);
        error_txt_confirmpw = rootview.findViewById(R.id.txt_confirmpw_message_error);

        txt_pw = rootview.findViewById(R.id.txt_pwlength);
        txt_confrimpw = rootview.findViewById(R.id.txt_confirmpw_message);

        if (pw.getText().toString().trim().equals(confirmpw.getText().toString().trim())&&((pw.getText().toString().trim().length() != 0)&&(confirmpw.getText().toString().trim().length() != 0))){
            //비밀번호 일치
            txt_pw.setVisibility(View.VISIBLE);
            txt_confrimpw.setVisibility(View.VISIBLE);
        }

        nextstep = rootview.findViewById(R.id.btn_signup_pw_nextstep);
        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pw.getText().toString().trim().length()==0 || confirmpw.getText().toString().trim().length()==0){
                    //비밀번호 미입력
                    pw_message.setVisibility(View.VISIBLE);
                    error_txt_confirmpw.setVisibility(View.INVISIBLE);
                    error_txt_pw.setVisibility(View.INVISIBLE);
                }
                else if(!pw.getText().toString().trim().equals(confirmpw.getText().toString().trim())){
                    //비밀번호 불일치
                  error_txt_confirmpw.setVisibility(View.VISIBLE);
                  error_txt_pw.setVisibility(View.INVISIBLE);
                  pw_message.setVisibility(View.INVISIBLE);
                }
                else if (pw.getText().toString().length()<8){
                    //비밀번호 8글자 미만
                    error_txt_pw.setVisibility(View.VISIBLE);
                    error_txt_confirmpw.setVisibility(View.INVISIBLE);
                    pw_message.setVisibility(View.INVISIBLE);
                }
                else if(pw.getText().toString().trim().equals(confirmpw.getText().toString().trim()) && pw.getText().toString().length()>7){
                    //비밀번호 일치,8글자 이상
                    stdpw = pw.getText().toString();
                    UserData.getInstance().setPw(stdpw);
                    getFragmentManager().beginTransaction().replace(R.id.container_signup, new signup_phoneFragment()).commit();
                }
            }
        });
        return rootview;
    }
}