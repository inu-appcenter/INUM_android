package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gowoon.inum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerMypageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_drawer_mypage, container, false);
    }

}
