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


public class upload_clothFragment extends Fragment {

    private Button btn_male, btn_female, btn_etc;
    private String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_upload_cloth, container, false);

        btn_male = rootview.findViewById(R.id.btn_upload_cloth_man);
        btn_female = rootview.findViewById(R.id.btn_upload_cloth_woman);
        btn_etc = rootview.findViewById(R.id.btn_upload_cloth_etc);

        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "의류남성";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "의류여성";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "의류기타";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });


        return rootview;
    }

}
