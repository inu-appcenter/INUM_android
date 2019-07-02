package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import org.gowoon.inum.R;

public class UploadCategoryChildFragment extends Fragment {
    ListView listViewChild;
    String[] childList;

    public UploadCategoryChildFragment() {
        // Required empty public constructoreo
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_category_child, container, false);
        listViewChild = rootView.findViewById(R.id.listview_upload_category_child);

        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflaterMenu = getActivity().getMenuInflater();
        inflaterMenu.inflate(R.menu.category_menu, menu);

        int categoryNum = getArguments().getInt("category");
        for (int j = 1; categoryNum < 4; j++) {
            childList = new String[]{menu.getItem(categoryNum).getSubMenu().getItem(j).getTitle().toString()};
        }

        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, childList) ;
        listViewChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listViewChild.setAdapter(Adapter);
        return rootView;
    }
//    public void CategoryMenu(){
//        PopupMenu p  = new PopupMenu(this.getActivity(), null);
//        Menu menu = p.getMenu();
//        MenuInflater inflaterMenu = getActivity().getMenuInflater();
//        inflaterMenu.inflate(R.menu.category_menu, menu);
//
//        int categoryNum = getArguments().getInt("category");
//        for (int j = 1; categoryNum < 4; j++) {
//            childList = new String[]{menu.getItem(categoryNum).getSubMenu().getItem(j).getTitle().toString()};
//        }
//    }
}
