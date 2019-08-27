package org.gowoon.inum.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.model.ProductOneItemResult;

public class UploadExplainFragment extends android.support.v4.app.Fragment{

    EditText evExplain, evPlace;
    RadioButton radioButtonMeet, radioButtonPost;
    RadioGroup radioGroupMethod;
    private String method, place;

    Boolean meeting;
    LinearLayout constraintLayoutMethod;

    public UploadExplainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_explain, container, false);

        TextView tvNext = getActivity().findViewById(R.id.tv_upload_next);
        ((UploadActivity)getActivity()).initView("상품 등록하기","다음",true);

        evExplain = rootView.findViewById(R.id.etv_upload_explain);
        evPlace = rootView.findViewById(R.id.etv_upload_explain_place);

        radioGroupMethod = rootView.findViewById(R.id.radio_group_upload);
        radioButtonMeet = rootView.findViewById(R.id.radio_btn_meeting);
        radioButtonPost = rootView.findViewById(R.id.radio_btn_postbox);
        constraintLayoutMethod = rootView.findViewById(R.id.constraint_upload_explain_method);

        radioGroupMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_btn_meeting:
                        constraintLayoutMethod.setVisibility(View.VISIBLE);
                        meeting = true;
                        method = "직거래";
                        break;
                    case R.id.radio_btn_postbox:
                        constraintLayoutMethod.setVisibility(View.INVISIBLE);
                        place = "postbox";
                        method = "택배";
                        meeting = false;
                        break;

                }
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(evExplain.getText().toString().equals("")||meeting==null){
                    Toast.makeText(getContext(),"상품 설명을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (meeting) {
                        place = evPlace.getText().toString();
                    }
                    ProductOneItemResult.getInstance().setMethod(method);
                    ProductOneItemResult.getInstance().setPlace(place);
                    ProductOneItemResult.getInstance().setProductInfo(evExplain.getText().toString());
                    Log.d("upload explain", method+place);

                    UploadImageFragment uploadImage = new UploadImageFragment();
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                            .replace(R.id.constraint_upload, uploadImage)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
        return rootView;
    }

}
