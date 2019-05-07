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


public class upload_etcFragment extends Fragment {

    private Button btn_life, btn_bag, btn_etc;
    private String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_upload_etc, container, false);

        btn_life = rootview.findViewById(R.id.btn_upload_etc_life);
        btn_bag = rootview.findViewById(R.id.btn_upload_etc_bag);
        btn_etc = rootview.findViewById(R.id.btn_upload_etc_etc);

        btn_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "잡화생활사무";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "잡화가방신발";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "잡화기타";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });

        return rootview;
    }


}
