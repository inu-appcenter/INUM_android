package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterListviewCategory;
import org.gowoon.inum.model.DataCategoryParent;

import java.util.ArrayList;


public class UploadCategoryFragment extends Fragment {
    public AdapterListviewCategory lAdapter = new AdapterListviewCategory();
    public ListView listViewCategory;
    public static Bundle bundleUpload, bundle = new Bundle();
    Boolean loadData = true;

    String[] childList;
    ArrayList<String[]> childArray = new ArrayList<>();

    public UploadCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_upload_category, container, false);
        listViewCategory = rootview.findViewById(R.id.listview_upload_category);

        // 프래그먼트 실행할 때마다 새로 카테고리 값이 불러와져서 넣음
        if (loadData){
            menu();
        }

        listViewCategory.setAdapter(lAdapter);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                bundleUpload.putString("category", lAdapter.getName(position));
                if (position<4){
                    UploadCategoryChildFragment categoryChild = new UploadCategoryChildFragment();
                    bundle.putStringArray("category child",childArray.get(position));
                    categoryChild.setArguments(bundleUpload);
                    categoryChild.setArguments(bundle);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.constraint_upload, categoryChild)
                            .addToBackStack(null)
                            .commit();
                }
                else{
                    UploadInfoFragment categoryInfo = new UploadInfoFragment();
                    getFragmentManager().beginTransaction()
                        .replace(R.id.constraint_upload, categoryInfo)
                        .addToBackStack(null)
                        .commit();
                }
            }
        });
        return rootview;
    }
    private void menu(){
        loadData = false;
        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflaterMenu = getActivity().getMenuInflater();
        inflaterMenu.inflate(R.menu.category_menu, menu);

        for (int i = 0; i<6 ; i++){
            lAdapter.addView(menu.getItem(i).getIcon(),menu.getItem(i).getTitle().toString());
            Log.d("category test",menu.getItem(i).getTitle().toString());
            if (i<4) {
                childList = new String[]{menu.getItem(i).getSubMenu().getItem(1).getTitle().toString()
                            ,menu.getItem(i).getSubMenu().getItem(2).getTitle().toString()
                            ,menu.getItem(i).getSubMenu().getItem(3).getTitle().toString()};
                childArray.add(i,childList);
            }
        }
    }
}
