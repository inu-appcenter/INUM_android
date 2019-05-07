package org.gowoon.inum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadAcitivity;
import org.gowoon.inum.model.Upload;

import java.util.zip.Inflater;

public class upload_categoryFragment extends Fragment {

    private Button btn_book, btn_cloth, btn_electric, btn_etc, btn_room, btn_ticket, nextstep;
    private String category;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
      View rootview = inflater.inflate(R.layout.fragment_upload_category,container, false);

      btn_book = rootview.findViewById(R.id.btn_upload_category_book);
      btn_cloth = rootview.findViewById(R.id.btn_upload_category_cloth);
      btn_electric = rootview.findViewById(R.id.btn_upload_category_electric);
      btn_etc = rootview.findViewById(R.id.btn_upload_category_etc);
      btn_room = rootview.findViewById(R.id.btn_upload_category_room);
      btn_ticket = rootview.findViewById(R.id.btn_upload_category_ticket);

      btn_book.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_bookFragment()).commit();
          }
      });

        btn_cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_clothFragment()).commit();
            }
        });

        btn_electric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_electricFragment()).commit();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_etcFragment()).commit();
            }
        });
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "자취방";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });
        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "식권";
                Upload.getInstance().setProductCategory(category);
                getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_infoFragment()).commit();
            }
        });
        return rootview;
    }

}
