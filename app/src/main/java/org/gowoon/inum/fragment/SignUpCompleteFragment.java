package org.gowoon.inum.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.LoginActivity;
import org.gowoon.inum.custom.Adapter_dialog_onebutton;

import java.util.Objects;

public class SignUpCompleteFragment extends Fragment {
    Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_signup_complete,container, false);
        viewInit(rootView);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        return rootView;
    }
    private void viewInit(View view){
        btnSubmit = view.findViewById(R.id.btn_sign_up_submit);
    }
}

