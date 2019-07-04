package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gowoon.inum.R;

public class UploadExplainFragment extends android.support.v4.app.Fragment {

    public UploadExplainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_explain, container, false);


        return rootView;
    }

}
