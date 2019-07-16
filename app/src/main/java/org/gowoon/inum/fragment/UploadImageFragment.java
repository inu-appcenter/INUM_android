package org.gowoon.inum.fragment;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemImageList;
import org.gowoon.inum.recycler.AdapterRecyclerUploadImage;

public class UploadImageFragment extends Fragment {

    AdapterRecyclerUploadImage rAdapter = new AdapterRecyclerUploadImage();
    ItemImageList list;
    RecyclerView recyclerViewImage;
    LinearLayout layoutSelect;

    public UploadImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_image, container, false);

        layoutSelect = rootView.findViewById(R.id.linearLayout_upload_image_select);
        recyclerViewImage = rootView.findViewById(R.id.recyclerview_upload_image);
        list = new ItemImageList(Uri.parse(""),true);

        rAdapter.addItem(list);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerViewImage.setLayoutManager(mLayoutManager);
        recyclerViewImage.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImage.setAdapter(rAdapter);
        rAdapter.setItemClick(new AdapterRecyclerUploadImage.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                layoutSelect.setVisibility(View.VISIBLE);
                view.setAlpha(0.8F);
            }
        });

        return rootView;
    }

}
