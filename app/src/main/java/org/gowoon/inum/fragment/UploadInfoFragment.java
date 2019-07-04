package org.gowoon.inum.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.model.ProductOneItemResult;

import static org.gowoon.inum.activity.UploadActivity.*;

public class UploadInfoFragment extends Fragment {
    EditText etvName, etvStatus, etvPrice;
    TextView tvInput;
    String[] info;
    Boolean inputItem = true;


    public UploadInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_info, container, false);
        TextView tvNext = getActivity().findViewById(R.id.tv_upload_next);
        tvNext.setVisibility(View.VISIBLE);

        etvName = rootView.findViewById(R.id.etv_upload_info_name);
        etvPrice = rootView.findViewById(R.id.etv_upload_info_price);
        etvPrice.setTextColor(Color.BLACK);
        etvStatus = rootView.findViewById(R.id.etv_upload_info_status);

        tvInput = rootView.findViewById(R.id.tv_upload_info_message);

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = new String[]{etvName.getText().toString(), etvStatus.getText().toString(), etvPrice.getText().toString()};
                for (int i = 0 ; i <3 ; i++){
                    inputItem = !(info[i].equals(""));
                }
                if (inputItem){
                    ProductOneItemResult.getInstance().setProductName(info[0]);
                    ProductOneItemResult.getInstance().setProductState(info[1]);
                    ProductOneItemResult.getInstance().setProductPrice(Integer.valueOf(info[2]));

                    UploadExplainFragment uploadExplain = new UploadExplainFragment();
                    Log.d("info",info[0]+info[1]+info[2]);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.constraint_upload, uploadExplain)
                            .addToBackStack(null)
                            .commit();
                }
                else{
                    tvInput.setVisibility(View.VISIBLE);
                }
            }
        });



        return rootView;
    }
}