package org.gowoon.inum.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.Upload;


public class upload_explainFragment extends Fragment {

    private EditText etv_explain;
    private EditText etv_place;
    private Button btn_direct, btn_delivery, nextstep;
    private TextView message;
    private LinearLayout layout_direct;

    private String info, direct, delivery, place;
    private int check; //Radiobtn 클릭 확인

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_upload_explain, container, false);

        etv_explain = rootview.findViewById(R.id.etv_upload_explain);
        etv_place = rootview.findViewById(R.id.etv_upload_explain_place);

        btn_delivery = rootview.findViewById(R.id.btn_upload_explain_delivery);
        btn_direct = rootview.findViewById(R.id.btn_upload_explain_direct);
        message = rootview.findViewById(R.id.txt_explain_msg);
        nextstep = rootview.findViewById(R.id.btn_upload_explain_nextstep);
        layout_direct = rootview.findViewById(R.id.layout_upload_direct);

//        explain = etv_explain.getText().toString();
//        direct = "직거래";
//        place = etv_place.getText().toString();

        btn_direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_direct.setVisibility(View.VISIBLE);
                direct = "직거래";
                check = 1;
                Upload.getInstance().setMethod(direct);
            }
        });
        btn_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_direct.setVisibility(View.INVISIBLE);
                delivery = "배송";
                check = 1;
                Upload.getInstance().setMethod(delivery);
            }
        });
        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etv_explain.getText().toString().length()==0 || check != 1){
                    message.setVisibility(View.VISIBLE);
                }
                else{
                    info = etv_explain.getText().toString();
                    place = etv_place.getText().toString();

                    Upload.getInstance().setProductInfo(info);
                    Upload.getInstance().setPlace(place);

                    getFragmentManager().beginTransaction().replace(R.id.container_upload, new upload_photoFragment()).commit();
                }
            }
        });
        return rootview;
    }
}
