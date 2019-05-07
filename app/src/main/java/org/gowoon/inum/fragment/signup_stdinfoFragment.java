package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignupActivity;
import org.gowoon.inum.model.UserData;


public class signup_stdinfoFragment extends Fragment {

    private EditText etv_stdname, etv_stdnum;
    private TextView stdnum_message;
    String stdname, stdnum;
    private Button nextstep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootview = inflater.inflate(R.layout.fragment_signup_stdinfo,container,false);

        stdnum_message = rootview.findViewById(R.id.txt_stdnum_error);
        etv_stdname = rootview.findViewById(R.id.etv_signup_stdname);
        etv_stdnum = rootview.findViewById(R.id.etv_signup_stdnum);
        nextstep = rootview.findViewById(R.id.btn_signup_stdinfo_nextstep);

        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etv_stdnum.getText().toString().trim().length() != 9){
                    stdnum_message.setVisibility(View.VISIBLE);
                }
                else{
                    stdnum_message.setVisibility(View.INVISIBLE);

                    stdname = etv_stdname.getText().toString();
                    stdnum = etv_stdnum.getText().toString();

                    UserData.getInstance().setName(stdname);
                    UserData.getInstance().setSchoolID(stdnum);
//TODO
                    //프래그먼트에서 뒤로가기 누르면 전단계로 돌아가기
                    getFragmentManager().beginTransaction().replace(R.id.container_signup, new signup_pwFragment()).commit();
//                    ((SignupActivity)getActivity()).changetFragment(new signup_pwFragment());
                }

            }
        });

        return rootview;
    }
}