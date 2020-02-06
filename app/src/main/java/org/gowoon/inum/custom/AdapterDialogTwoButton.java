package org.gowoon.inum.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.gowoon.inum.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterDialogTwoButton extends Dialog {
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
    public AdapterDialogTwoButton(Context context, String title){
        super(context);
        this.mTitle = title;
    }

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
}