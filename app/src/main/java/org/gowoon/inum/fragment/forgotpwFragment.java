package org.gowoon.inum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.Adapter_dialog_onebutton;
import org.w3c.dom.Text;

public class forgotpwFragment extends Fragment {

    private EditText etv_name, etv_stdid;
    Button btn_find;
    TextView tv_err;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_forgotpw, container, false);

        etv_name = rootview.findViewById(R.id.etv_forgotpw_name);
        etv_stdid = rootview.findViewById(R.id.etv_forgotpw_id);
        btn_find = rootview.findViewById(R.id.btn_forgotpw);
        tv_err = rootview.findViewById(R.id.tv_forgotpw_noinput);

        btn_find.bringToFront();

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter_dialog_onebutton dialog = new Adapter_dialog_onebutton(rootview.getContext());
                dialog.show();
            }
        });

        return rootview;
    }
}
