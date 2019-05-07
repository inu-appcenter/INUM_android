package org.gowoon.inum.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.gowoon.inum.R;
import org.gowoon.inum.fragment.upload_bookFragment;
import org.gowoon.inum.fragment.upload_categoryFragment;
import org.gowoon.inum.fragment.upload_clothFragment;
import org.gowoon.inum.fragment.upload_electricFragment;
import org.gowoon.inum.fragment.upload_etcFragment;
import org.gowoon.inum.fragment.upload_explainFragment;
import org.gowoon.inum.fragment.upload_infoFragment;
import org.gowoon.inum.fragment.upload_photoFragment;

public class UploadAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

//        TODO
        // 레이아웃 전반적으로 다 수정
        android.app.FragmentManager upload_fm = getFragmentManager();
        android.app.FragmentTransaction upload_fragmentTransaction = upload_fm.beginTransaction();
        upload_fragmentTransaction.add(R.id.container_upload, new upload_categoryFragment());
        upload_fragmentTransaction.commit();
    }

    public void changetFragment(upload_bookFragment upload_bookFragment) {
    }

    public void changetFragment(upload_electricFragment upload_electricFragment) {
    }

    public void changetFragment(upload_etcFragment upload_etcFragment) {
    }

    public void changetFragment(upload_explainFragment upload_explainFragment) {
    }

    public void changetFragment(upload_infoFragment upload_infoFragment) {
    }

    public void changetFragment(upload_clothFragment upload_clothFragment) {
    }

    public void changetFragment(upload_photoFragment upload_photoFragment) {
    }
}