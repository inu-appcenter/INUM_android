package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class UploadCategoryFragment extends Fragment {
    public AdapterListviewCategory lAdapter = new AdapterListviewCategory();
    public ListView listViewCategory;
    public static Bundle bundleUpload = new Bundle();
    public UploadCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_upload_category, container, false);

        listViewCategory = rootview.findViewById(R.id.listview_upload_category);

        menu();

        listViewCategory.setAdapter(lAdapter);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundleUpload.putString("category", lAdapter.getName(position));
                if (position<4){
                    UploadCategoryChildFragment categoryChild = new UploadCategoryChildFragment();



                    categoryChild.setArguments(bundleUpload);
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
        PopupMenu p  = new PopupMenu(this.getActivity(), null);
        Menu menu = p.getMenu();
        MenuInflater inflaterMenu = getActivity().getMenuInflater();
        inflaterMenu.inflate(R.menu.category_menu, menu);

        for (int i = 0; i<6 ; i++){
            lAdapter.addView(menu.getItem(i).getIcon(),menu.getItem(i).getTitle().toString());
        }
    }
}
