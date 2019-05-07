package org.gowoon.inum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadAcitivity;
import org.gowoon.inum.model.Upload;


public class upload_electricFragment extends Fragment {

    private Button btn_elec, btn_furni, btn_etc;
    private String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_upload_electric, container, false);

        btn_elec = rootview.findViewById(R.id.btn_upload_electric_elec);
        btn_furni = rootview.findViewById(R.id.btn_upload_electric_furniture);
        btn_etc = rootview.findViewById(R.id.btn_upload_electric_etc);

        btn_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "가전가구전자기기";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_furni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "가전가구가구";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });


        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "가전가구기타";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        return rootview;
    }

}
