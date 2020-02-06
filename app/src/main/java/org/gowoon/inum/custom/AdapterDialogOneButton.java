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

public class AdapterDialogOneButton extends Dialog {

    @BindView(R.id.btn_di_submit) protected Button okButton;

    public interface OnOkButtonClickListener {
        void onClick();
    }
    private OnOkButtonClickListener okButtonClickListener = null;

    private TextView mTitleView;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_custom_onebutton);
        ButterKnife.bind(this);
        mTitleView = findViewById(R.id.txt_di_one);
        setDialogTitle(mTitle);
    }
    public AdapterDialogOneButton(Context context, String title){
        super(context);
        this.mTitle = title;
    }

    public void setDialogTitle(String text){
        mTitleView.setText(text);
    }

    public void setOnOkButtonClickListener(OnOkButtonClickListener listener){
        okButtonClickListener = listener;
    }

    @OnClick(R.id.btn_di_submit)
    public void okButton(){
        if(okButtonClickListener != null){
            okButtonClickListener.onClick();
        }
        dismiss();
    }
}
