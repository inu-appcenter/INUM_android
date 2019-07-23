package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.custom.AdapterListviewCategoryChild;
import org.gowoon.inum.model.ProductOneItemResult;

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

        ((UploadActivity)getActivity()).initView("상품 등록하기","",false);

        childList = getArguments().getStringArray("category child");
        final AdapterListviewCategoryChild cAdapter = new AdapterListviewCategoryChild(childList);
        listViewChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductOneItemResult.getInstance().setCategory(getArguments().getString("category parent")+cAdapter.getName(position));

                UploadInfoFragment categoryInfo = new UploadInfoFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .replace(R.id.constraint_upload, categoryInfo)
                        .addToBackStack(null)
                        .commit();
            }
        });

        listViewChild.setAdapter(cAdapter);
        return rootView;
    }
}
