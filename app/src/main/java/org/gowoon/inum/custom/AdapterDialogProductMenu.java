package org.gowoon.inum.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.gowoon.inum.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterDialogProductMenu extends Dialog {
    public AdapterDialogProductMenu(@NonNull Context context, String productId) {
        super(context);
        this.productId = productId;
    }
    private String selectWhat, productId ;

    @BindView(R.id.btn_dialog_product_menu_ok) protected Button okButton;
    @BindView(R.id.btn_dialog_product_menu_cancle) protected Button cancelButton;

    public interface OnOkButtonClickListener{
        void onClick();
    }
    public interface OnCancelButtonClickListener{
        void onClick();
    }

    private OnOkButtonClickListener okButtonClickListener = null;
    private OnCancelButtonClickListener cancelButtonClickListener = null;

    @OnClick(R.id.tv_dialog_product_menu_sold)
    public void productSold(){
        selectWhat="sold";
    }

    @OnClick(R.id.tv_dialog_product_menu_delete)
    public void productDelete(){
        selectWhat="delete";
    }

    @OnClick(R.id.tv_dialog_product_menu_edit)
    public void productEdit(){
        selectWhat = "edit";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.adapter_dialog_product_menu);
        ButterKnife.bind(this);
    }

    public void setOnOkButtonClickListener(AdapterDialogProductMenu.OnOkButtonClickListener listener){
        okButtonClickListener = listener;
    }
    public void setOnCancelButtonClickListener(AdapterDialogProductMenu.OnCancelButtonClickListener listener){
        cancelButtonClickListener = listener;
    }

    @OnClick(R.id.btn_dialog_product_menu_ok)
    public void okButton(){
        if(okButtonClickListener != null){
            okButtonClickListener.onClick();
        }
        dismiss();
    }

    @OnClick(R.id.btn_dialog_product_menu_cancle)
    public void cancelButton(){
        if(cancelButtonClickListener != null){
            cancelButtonClickListener.onClick();
        }
        dismiss();
    }

    public String getSelectWhat() {return selectWhat;}
    public void setSelectWhat(String selectWhat) {this.selectWhat = selectWhat;}
}
