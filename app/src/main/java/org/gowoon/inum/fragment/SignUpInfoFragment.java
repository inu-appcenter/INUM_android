package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;
import org.gowoon.inum.model.UserData;

import java.util.ArrayList;


public class SignUpInfoFragment extends Fragment {

    Spinner spinner;

    ArrayList majorList = new ArrayList();
    private EditText etName, etId;
    private TextView tvErrMsg, tvErrNoInput;
    String name, id, major;

    Boolean inputId = false, inputName = false, inputMajor= true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootview = inflater.inflate(R.layout.fragment_signup_info,container,false);

        init(rootview);
        setSpinner(spinner,majorList);
        ((SignUpActivity)getActivity()).initViewSignUp("학생정보 입력하기");

        rootview.findViewById(R.id.btn_sign_up_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = etId.getText().toString().trim();
                name = etName.getText().toString();
                if(id.length() == 9){
                    tvErrMsg.setVisibility(View.INVISIBLE);
                    inputId = true;
                }
                if (name != null){
                    inputName = true;
                }
//                if(){
//                    inputMajor = true;
//                }

                if (inputId&&inputName&&inputMajor){
                    tvErrNoInput.setVisibility(View.INVISIBLE);
                    UserData.getInstance().setName(name);
                    UserData.getInstance().setSchoolID(id);
                    ((SignUpActivity)getActivity()).setViewPagerNext();
                }
                else{
                    tvErrNoInput.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootview;
    }

    public void init(View root){
        tvErrMsg = root.findViewById(R.id.tv_sign_up_info_error_msg);
        tvErrNoInput = root.findViewById(R.id.tv_sign_up_info_no_input);
        etName = root.findViewById(R.id.et_sign_up_name);
        etId = root.findViewById(R.id.et_sign_up_id);

        spinner = root.findViewById(R.id.spinner_sign_up_major);
    }

    public void setSpinner(Spinner spinner, ArrayList list){
        final ArrayList<String> dummyList = new ArrayList();
        dummyList.add("정보통신공학과");
        dummyList.add("디자인학부");
        dummyList.add("임베디드시스템공학과");
        dummyList.add("컴퓨터공학부");

//        ArrayAdapter sAdapter = new ArrayAdapter(getActivity(), R.layout.item_spinner_signup, list);
        ArrayAdapter sAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item,dummyList);
//        sAdapter.setDropDownViewResource(R.layout.item_spinner_signup,R.id.tv_item_spinner_signup);
        spinner.setAdapter(sAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                major = dummyList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}