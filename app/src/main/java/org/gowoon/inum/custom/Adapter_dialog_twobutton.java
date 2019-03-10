package org.gowoon.inum.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.gowoon.inum.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Adapter_dialog_twobutton extends Dialog {

    public Adapter_dialog_twobutton(@NonNull Context context) {
        super(context);
    }

    @BindView(R.id.btn_di_twobutton_submit) protected Button okButton;
    @BindView(R.id.btn_di_twobutton_cancle) protected Button cancelButton;

    public interface OnOkButtonClickListener{
        void onClick();
    }
    public interface OnCancelButtonClickListener{
        void onClick();
    }

    private OnOkButtonClickListener okButtonClickListener = null;
    private OnCancelButtonClickListener cancelButtonClickListener = null;
    private TextView mTitleView;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_custom_twobutton);
        ButterKnife.bind(this);
        mTitleView = findViewById(R.id.tv_dialog_twobutton);
        setdialogtxt(mTitle);

    }
    public Adapter_dialog_twobutton(Context context, String title){
        super(context);
        this.mTitle = title;
    }

//
//    @OnClick(R.id.my_product_di_state)
//    public void stateChange(){
//        selectWhat="change";
//    }
//
//    @OnClick(R.id.my_product_di_delete)
//    public void productDelete(){
//        selectWhat="delete";
//    }

    public void setdialogtxt(String text){
        mTitleView.setText(text);
    }

    public void setOnOkButtonClickListener(OnOkButtonClickListener listener){
        okButtonClickListener = listener;
    }
    public void setOnCancelButtonClickListener(OnCancelButtonClickListener listener){
        cancelButtonClickListener = listener;
    }
    @OnClick(R.id.btn_di_twobutton_submit)
    public void okButton(){
        if(okButtonClickListener != null){
            okButtonClickListener.onClick();
        }
        dismiss();
    }

    @OnClick(R.id.btn_di_twobutton_cancle)
    public void cancleButton(){
        if(cancelButtonClickListener != null){
            cancelButtonClickListener.onClick();
        }
        dismiss();
    }

//    public String getSelectWhat() {return selectWhat;}
//    public void setSelectWhat(String selectWhat) {this.selectWhat = selectWhat;}
}