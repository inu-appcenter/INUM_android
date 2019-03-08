package org.gowoon.inum.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.PopupMenu;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.ExpandableListCategory_Adapter;
import org.gowoon.inum.model.DataCategoryParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class DrawerCategoryFragment extends Fragment {

    private ExpandableListView categoryListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = (FrameLayout)inflater.inflate(R.layout.fragment_drawer_category, container, false);
        categoryListview = rootview.findViewById(R.id.expandablelist_category);
        final DataCategoryParent parentList[] = new DataCategoryParent[6];

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

        categoryListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {
                if ((groupPosition == 4)||(groupPosition == 5)){
                    Drawable image = parentList[groupPosition].getCategory_image();
                    String parentname = parentList[groupPosition].getCategory_name();

                    Bundle bundle = new Bundle();
                    bundle.putString("parent",parentname);
                    //   bundle.putInt("categoryimage",image);

                    SearchProductCategoryFragment category_product = new SearchProductCategoryFragment();
                    category_product.setArguments(bundle);

                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.activity_main, category_product)
                            .addToBackStack(null)
                            .commit();
                    DrawerLayout drawer = getActivity().findViewById(R.id.drawer_main);
                    drawer.closeDrawer(Gravity.START);
                }
                return false;
            }
        });
        categoryListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Drawable image = parentList[groupPosition].getCategory_image();
                String childname = parentList[groupPosition].child.get(childPosition);
                String parentname = parentList[groupPosition].getCategory_name();

                Bundle bundle = new Bundle();
                bundle.putString("child",childname);
                bundle.putString("parent",parentname);
                //   bundle.putInt("categoryimage",image);

                SearchProductCategoryFragment category_product = new SearchProductCategoryFragment();
                category_product.setArguments(bundle);

                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.activity_main, category_product)
                        .addToBackStack(null)
                        .commit();
                DrawerLayout drawer = getActivity().findViewById(R.id.drawer_main);
                drawer.closeDrawer(Gravity.START);

                categoryListview.collapseGroup(groupPosition);

                return false;
            }
        });

        return rootview;
    }
}
