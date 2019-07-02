package org.gowoon.inum.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.UploadCategoryFragment;

public class UploadActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    Fragment fragmentCategory = new UploadCategoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.constraint_upload, fragmentCategory)
                .addToBackStack(null)
                .commit();
    }
}
