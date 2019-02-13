package org.gowoon.inum.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.ExpandableListCategory_Adapter;
import org.gowoon.inum.model.DataCategoryParent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class DrawerCategoryFragment extends Fragment {

    private ExpandableListView categoryListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_drawer_category, container, false);
        categoryListview = rootview.findViewById(R.id.expandablelist_category);
        DataCategoryParent parentList[] = new DataCategoryParent[6];

        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflatermenu = getActivity().getMenuInflater();
        inflatermenu.inflate(R.menu.category_menu, menu);

        String[] childlist;
        for (int i = 0; i<parentList.length ; i++){
            parentList[i] = new DataCategoryParent(menu.getItem(i).getIcon(),menu.getItem(i).getTitle().toString());
            if (i<4) {
                for (int j = 0; j < 4; j++) {
                    childlist = new String[]{menu.getItem(i).getSubMenu().getItem(j).getTitle().toString()};
                    Collections.addAll(parentList[i].child, childlist);
                }
            }
        }
        Vector<DataCategoryParent> data = new Vector<>(Arrays.asList(parentList).subList(0,6));
        ExpandableListCategory_Adapter adapter = new ExpandableListCategory_Adapter(this.getActivity(),data);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

//        categoryListview.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        categoryListview.setAdapter(adapter);
        return rootview;
    }
    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
