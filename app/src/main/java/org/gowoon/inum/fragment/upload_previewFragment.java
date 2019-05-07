package org.gowoon.inum.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.Upload;


public class upload_previewFragment extends Fragment {


    private TextView productname, productstate, productprice, productinfo, dealmethod, dealplace, productcategory;
    private String productName, productState, productPrice, category, productInfo, method, place;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_upload_preview, container, false);

        productname = rootview.findViewById(R.id.txt_upload_productName);
        productprice = rootview.findViewById(R.id.txt_upload_productPrice);
        productinfo = rootview.findViewById(R.id.txt_upload_productInfo);
        productstate = rootview.findViewById(R.id.txt_upload_productstatus);
        dealmethod = rootview.findViewById(R.id.txt_upload_method);
        dealplace = rootview.findViewById(R.id.txt_upload_place);
        productcategory = rootview.findViewById(R.id.txt_upload_category);

        productName = productname.getText().toString();
        productState = productstate.getText().toString();
        productPrice = productprice.getText().toString();
        category = productcategory.getText().toString();
        productInfo = productinfo.getText().toString();
        method = dealmethod.getText().toString();
        place = dealplace .getText().toString();
        category = productcategory.getText().toString();

        Upload.getInstance().getProductName(productName);
        Upload.getInstance().getProductPrice(productPrice);
        Upload.getInstance().getProductInfo(productInfo);
        Upload.getInstance().getProductState(productState);
        Upload.getInstance().getMethod(method);
        Upload.getInstance().getPlace(place);
        Upload.getInstance().getProductCategory(category);

        return rootview;
    }

}
