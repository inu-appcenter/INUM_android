package org.gowoon.inum.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.LoginActivity;
import org.gowoon.inum.custom.Adapter_dialog_onebutton;

public class signup_completeFragment extends Fragment {


    Button btnsubmit;
    Adapter_dialog_onebutton dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_complete,container, false);

        dialog = new Adapter_dialog_onebutton(getActivity(),"회원가입이 완료되었습니다.\n이메일 인증 완료 후 로그인해주세요.");
        dialog.show();

        //TODO
        //다이얼로그 버튼 리스너로 바꾸기, Intent 말고 Fragment 종료
        btnsubmit = rootview.findViewById(R.id.btn_di_submit);
        btnsubmit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Intent intent_signup = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent_signup);

                return false;
            }
        });
        return rootview;
    }
}

