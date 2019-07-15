package org.gowoon.inum.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignupActivity;


public class signup_argeementFragment extends Fragment {

    private CheckBox checkBox;
    private TextView message;
    private Button btn_nextstep;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_argeement, container, false);

        checkBox = rootview.findViewById(R.id.checkbox_signup_agree);
        message = rootview.findViewById(R.id.signup_non_agree);
        btn_nextstep = rootview.findViewById(R.id.btn_signup_agree_nextstep);

        btn_nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    message.setVisibility(View.INVISIBLE);
//                    FragmentManager agree_fm = getFragmentManager();
//                    FragmentTransaction tran = agree_fm.beginTransaction();
//
//                    tran.replace(R.id.container_signup,);
                    //TODO
                    //프래그먼트에서 뒤로가기 누르면 전단계로 돌아가기
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right,0,0,R.anim.exit_to_left)
                            .replace(R.id.container_signup, new signup_stdinfoFragment())
                            .addToBackStack(null)
                            .commit();

                } else {
                    message.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootview;
    }
}