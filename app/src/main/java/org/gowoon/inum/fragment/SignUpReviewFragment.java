package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.SignUpActivity;
import org.gowoon.inum.model.UserData;
import org.gowoon.inum.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpReviewFragment extends Fragment {

    private TextView tvName, tvId, tvPhone, tvMajor;
    String name, id, phone, major;

    Button next;

    public SignUpReviewFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_signup_review, container, false);

        tvId = rootview.findViewById(R.id.tv_sign_up_review_id);
        tvMajor = rootview.findViewById(R.id.tv_sign_up_review_major);
        tvName = rootview.findViewById(R.id.tv_sign_up_review_name);
        tvPhone = rootview.findViewById(R.id.tv_sign_up_review_phone_num);

        next = rootview.findViewById(R.id.btn_sign_up_next);
        next.setText("완료");

//        ((SignUpActivity) getActivity()).initViewSignUp("회원 가입 완료하기");
        ((SignUpActivity)getActivity()).stepView(1,1,1,1,0);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.retrofit.signUp(id,UserData.getInstance().getPasswd(),phone,major,name)
                        .enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if (response.isSuccessful()){
                                    JsonObject result = response.body();
                                    if (result.get("ans").equals("success")){
                                        Log.d("sign up result", "성공");
//                                        getFragmentManager().beginTransaction().replace(R.id.container_signup, new SignUpCompleteFragment());
                                        ((SignUpActivity)getActivity()).setViewPagerNext();
                                    }
                                    else {
                                        Log.v("id: ",id+" pw: "+UserData.getInstance().getPasswd()+" name: "+name+"phone: "+phone+" major: "+major);

                                        Toast.makeText(getActivity(),"회원가입을 실패하였습니다\n 잠시 후에 다시 시도해주세요",Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(getActivity(),"서버 연결상태를 확인해주세요",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });

//                //TODO
//                //HashMap 코드수정
//                Map<String, Object> info = new HashMap<>();
//                info.put("id", UserData.getInstance().getSchoolID());
//                info.put("passwd", UserData.getInstance().getPw());
//                info.put("name", UserData.getInstance().getName());
//                info.put("tel", UserData.getInstance().getPhone());
//                Log.e("벨류값", "" + info.values());

        return rootview;
    }

    public void setUserVisibleHint(boolean isVisibleToUser){
        if(isVisibleToUser){
            id = UserData.getInstance().getId();
            name = UserData.getInstance().getName();
            phone = UserData.getInstance().getPhone();
            major = UserData.getInstance().getMajor();

            tvName.setText(name);
            tvId.setText(id);
            tvPhone.setText(phone);
            tvMajor.setText(major);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    

//    private void viewSet(View root){
//        tvId = root.findViewById(R.id.tv_sign_up_review_id);
//        tvMajor = root.findViewById(R.id.tv_sign_up_review_major);
//        tvName = root.findViewById(R.id.tv_sign_up_review_name);
//        tvPhone = root.findViewById(R.id.tv_sign_up_review_phone_num);
//
//        next = root.findViewById(R.id.btn_sign_up_next);
//        next.setText("완료");
//
//        dataSet();
//        tvName.setText(name);
//        tvId.setText(id);
//        tvPhone.setText(phone);
//        tvMajor.setText(major);
//    }
//    private void dataSet(){
//        UserData uData = UserData.getInstance();
//        name = uData.getName();
//        id = uData.getId();
//        phone = uData.getPhone();
//        major = uData.getMajor();
//    }
}