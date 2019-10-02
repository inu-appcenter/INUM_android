package org.gowoon.inum.custom;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.ProductActivity;
import org.gowoon.inum.fragment.forgotpwFragment;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.model.UserData;
import org.gowoon.inum.util.RetrofitService;
import org.gowoon.inum.util.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Adapter_dialog_declare extends Dialog{

    @BindView(R.id.btn_di_declare_declare) protected Button declareButton;
    @BindView(R.id.btn_di_declare_cancel) protected Button cancelButton;

    public interface OnDeclareButtonClickListener{
        void onClick();
    }
    public interface OnCancelButtonClickListener{
        void onClick();
    }

    private OnDeclareButtonClickListener declareButtonClickListener = null;
    private OnCancelButtonClickListener cancelButtonClickListener = null;

    public RadioGroup declareRadioGroup;
    private RadioButton obsenceRadiobtn, overlapRadiobtn,advertisementRadiobtn, fakeRadiobtn;

    public String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_custom_product_detail_declare);
        ButterKnife.bind(this);

        obsenceRadiobtn = findViewById(R.id.radio_btn_declare_obsence);
        overlapRadiobtn = findViewById(R.id.radio_btn_declare_overlap);
        advertisementRadiobtn = findViewById(R.id.radio_btn_declare_advertisement);
        fakeRadiobtn = findViewById(R.id.radio_btn_declare_fake);

        declareRadioGroup = findViewById(R.id.radio_group_declare);
        declareRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_btn_declare_obsence:
                        kind ="음란성";
                        break;
                    case R.id.radio_btn_declare_advertisement:
                        kind = "광고";
                        break;
                    case R.id.radio_btn_declare_overlap:
                        kind = "도배";
                        break;
                    case R.id.radio_btn_declare_fake:
                        kind = "허위상품";
                        break;
                }
            }
        });
//
//        declareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Singleton.retrofit.report(kind, senderId, productId)
//                        .enqueue(new Callback<JsonObject>() {
//                            @Override
//                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                                if(response.isSuccessful()){
//
//                                    declareRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                        @Override
//                                        public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                            switch (checkedId){
//                                                case R.id.radio_btn_declare_obsence:
//                                                    Singleton.retrofit.report("음란성",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                    break;
//                                                case R.id.radio_btn_declare_advertisement:
//                                                    Singleton.retrofit.report("광고",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                    break;
//                                                case R.id.radio_btn_declare_overlap:
//                                                    Singleton.retrofit.report("도배",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                    break;
//                                                case R.id.radio_btn_declare_fake:
//                                                    Singleton.retrofit.report("허위상품",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                    break;
//                                            }
//                                        }
//                                    });
//
//                                    Log.v("kind", kind+" senderId: "+ senderId + " productID: " + productId);
//                                    JsonObject result = response.body();
//                                    if (result.get("ans").equals("true")) {
//                                        Log.v("declare", "성공");
//
//                                        Adapter_dialog_onebutton dialog_onebutton = new Adapter_dialog_onebutton(getOwnerActivity(), "상품 신고가 완료되었습니다.");
//                                        dialog_onebutton.show();
//
//                                        dialog_onebutton.setOnOkButtonClickListener(new Adapter_dialog_onebutton.OnOkButtonClickListener() {
//                                            @Override
//                                            public void onClick() {
//                                                Intent intent = new Intent(String.valueOf(ProductActivity.class));
//                                            }
//                                        });
//                                    }
//                                    else {
//                                        Log.v("declare", "실패");
//                                        Toast.makeText(getContext(),"신고에 실패하였습니다. 다시 시도해주세요",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//                            @Override
//                            public void onFailure(Call<JsonObject> call, Throwable t) {
//                                Toast.makeText(getContext(),"서버 연결상태를 확인해주세요",Toast.LENGTH_SHORT)
//                                        .show();
//                            }
//                        });
//            }
//        });
    }

    public Adapter_dialog_declare(Context context){
        super(context);
    }

    public void setOnDeclareButtonClickListener(OnDeclareButtonClickListener listener){
        declareButtonClickListener = listener;
    }
    public void setOnDeclareCancelButtonClickListener(OnCancelButtonClickListener listener){
        cancelButtonClickListener = listener;
    }
    @OnClick(R.id.btn_di_declare_declare)
    public void declareButton(){
        declareButtonClickListener.onClick();

        if(declareButtonClickListener!= null){

        }
        dismiss();
    }
    @OnClick(R.id.btn_di_declare_cancel)
    public void cancelButton(){
        if(cancelButtonClickListener != null){ cancelButtonClickListener.onClick(); }
        dismiss();
    }

}

