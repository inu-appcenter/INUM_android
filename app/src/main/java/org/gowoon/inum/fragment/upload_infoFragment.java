package org.gowoon.inum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadAcitivity;
import org.gowoon.inum.model.Upload;


public class upload_infoFragment extends Fragment {

    private TextView message;
    private EditText etv_name, etv_state, etv_price;

    private Button nextstep;

    private String productName;
    private String productState;
    private String productPrice;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_upload_info, container, false);

        message = rootview.findViewById(R.id.txt_info_msg);

        etv_name = rootview.findViewById(R.id.etv_upload_info_name);
        etv_state = rootview.findViewById(R.id.etv_upload_info_condition);
        etv_price = rootview.findViewById(R.id.etv_upload_info_price);

        nextstep = rootview.findViewById(R.id.btn_upload_info_nextstep);

        productName = etv_name.getText().toString();
        productState = etv_state.getText().toString();
        productPrice = etv_price.getText().toString();

        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if ((etv_name.getText().toString().trim().length()==0)||(etv_state.getText().toString().trim().length()==0)||(etv_price.getText().toString().trim().length()==0))
                    message.setVisibility(View.VISIBLE);
                else{
                    message.setVisibility(View.INVISIBLE);
                    Upload.getInstance().setProductName(productName);
                    Upload.getInstance().setProductState(productState);
                    Upload.getInstance().setProductPrice(productPrice);

                    getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_explainFragment()).commit();
                }
            }
        });
        return rootview;
    }

}
