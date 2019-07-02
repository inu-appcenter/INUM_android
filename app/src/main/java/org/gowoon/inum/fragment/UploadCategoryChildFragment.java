package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterListviewCategoryChild;

public class UploadCategoryChildFragment extends Fragment {
    ListView listViewChild;
    String[] childList;

    public UploadCategoryChildFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_category_child, container, false);
        listViewChild = rootView.findViewById(R.id.listview_upload_category_child);

        childList = getArguments().getStringArray("category child");
        AdapterListviewCategoryChild cAdapter = new AdapterListviewCategoryChild(childList);
        listViewChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UploadInfoFragment categoryInfo = new UploadInfoFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.constraint_upload, categoryInfo)
                        .addToBackStack(null)
                        .commit();
            }
        });

        listViewChild.setAdapter(cAdapter);
        return rootView;
    }
}
