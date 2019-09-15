package org.gowoon.inum.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.UploadCategoryFragment;

public class UploadActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    Fragment fragmentCategory = new UploadCategoryFragment();

    public static Bundle bundleUpload = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.constraint_upload, fragmentCategory)
                .commit();
    }
    public void initView(String title, String next, Boolean visible){
        TextView tvPreview = findViewById(R.id.tv_upload);
        tvPreview.setText(title);

        TextView tvUpload = findViewById(R.id.tv_upload_next);
        tvUpload.setText(next);

        if (visible){
        tvUpload.setVisibility(View.VISIBLE);}
        else
            tvUpload.setVisibility(View.INVISIBLE);
    }
}
