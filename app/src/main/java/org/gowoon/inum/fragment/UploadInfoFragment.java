package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gowoon.inum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadInfoFragment extends Fragment {


    public UploadInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_upload_info, container, false);
        TextView tvNext = getActivity().findViewById(R.id.tv_upload_next);
        tvNext.setVisibility(View.VISIBLE);

        return rootView;
    }

}
