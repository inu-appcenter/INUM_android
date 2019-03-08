package org.gowoon.inum.fragment;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.gowoon.inum.R;

public class SettingPushAlarmFragment extends Fragment {
    TextView tv_gosetting;
    Button btn_done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_setting_push_alarm,container,false);

        btn_done = rootview.findViewById(R.id.btn_setting_pushalarm);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                fragmentManager.beginTransaction()
                        .remove(SettingPushAlarmFragment.this).commit();
            }
        });

        tv_gosetting = rootview.findViewById(R.id.tv_setting_pushalarm_gosetting);
        tv_gosetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_setting = new Intent(Settings.ACTION_SETTINGS);
                startActivityForResult(intent_setting, 0);
            }
        });

        return rootview;
    }

}
