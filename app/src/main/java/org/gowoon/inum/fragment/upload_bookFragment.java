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


public class upload_bookFragment extends Fragment {

    private Button btn_culture, btn_major, btn_etc;
    private String category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_upload_book, container, false);

        btn_culture = rootview.findViewById(R.id.btn_upload_book_culture);
        btn_major = rootview.findViewById(R.id.btn_upload_book_major);
        btn_etc = rootview.findViewById(R.id.btn_upload_book_etc);

        btn_culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "책교양";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "책전공";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category ="책기타";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        return rootview;
    }


}
