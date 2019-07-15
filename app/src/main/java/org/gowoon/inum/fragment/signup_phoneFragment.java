package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignupActivity;
import org.gowoon.inum.model.UserData;

public class signup_phoneFragment extends Fragment {

    private EditText phonenum;
    private TextView errormsg;
    private Button nextstep;
    String phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_phone, container, false);

        phonenum = rootview.findViewById(R.id.etv_signup_phonenum);
        errormsg = rootview.findViewById(R.id.txt_phone_error);

        nextstep = rootview.findViewById(R.id.btn_signup_phone_nextstep);
        nextstep.setOnClickListener(new View.OnClickListener() {

            // 조건문 수정 01000000000 니까 11자리일때만 넘어가게
            @Override
            public void onClick(View v) {
                phone = phonenum.getText().toString();
                if(phone.length() == 0){
                    errormsg.setVisibility(View.VISIBLE);
                }
                else {
                    UserData.getInstance().setPhone(phone);
                    //TODO
                    //프래그먼트에서 뒤로가기 누르면 전단계로 돌아가기
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                            .replace(R.id.container_signup, new signup_reviewFragment())
                            .commit();
                }
            }
        });
        return rootview;
    }
}