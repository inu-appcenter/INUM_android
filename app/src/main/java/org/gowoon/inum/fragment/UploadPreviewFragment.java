package org.gowoon.inum.fragment;


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.model.ItemImageList;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.util.Singleton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadPreviewFragment extends Fragment {
    ProductOneItemResult uploadItemInfo = new ProductOneItemResult();
    TextView tvName, tvState, tvPrice, tvPlace, tvMethod, tvStar, tvExplain, tvCategory;
    String name, state, place, method, explain, category, userId, token;
    int price;
    ArrayList<Uri> imageUriList = new ArrayList<>();
    private ArrayList<MultipartBody.Part> imageFileList = new ArrayList<>();

    public UploadPreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_upload_preview, container, false);
        ((UploadActivity)getActivity()).initView("상품 등록 미리보기","등록",true);
        TextView tvUpload = getActivity().findViewById(R.id.tv_upload_next);

        getInfo();
        initViewSet(rootView);
        uriToFile();

        tvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.retrofit.productUpload(token,imageFileList,name,state,price,category,explain,method,place)
                        .enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if (response.isSuccessful()){
                                    if (response.code()==200){
                                        try{
                                            if (response.message().equals("success")){
                                            Toast.makeText(getActivity(),"등록 완료",Toast.LENGTH_LONG).show();
                                            }
                                        }catch (JsonIOException e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                else {
                                    Toast.makeText(getActivity(), "등록 실패", Toast.LENGTH_LONG).show();
                                    Log.d("upload fail","fail..");
                                }
                            }
                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.d("upload fail onFailure","fail..");
                                t.getMessage();
                                Log.d("err msg",t.getMessage());
                            }
                        });
                Log.d("item info",name+price);
            }
        });

        return rootView;
    }

    private void uriToFile(){
        imageUriList = ItemImageList.getInstance().getImageUri();
        for (int i = 0 ; i < imageUriList.size();i++) {
            File file = new File(String.valueOf(imageUriList.get(i)));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part multiFile = MultipartBody.Part.createFormData("userfile", file.getName(), requestFile);
            imageFileList.add(i,multiFile);
        }
    }

    private void initViewSet(View root){
        tvCategory = root.findViewById(R.id.tv_product_detail_category);
        tvName = root.findViewById(R.id.tv_product_detail_name);
        tvState = root.findViewById(R.id.tv_product_detail_status);
        tvPlace = root.findViewById(R.id.tv_product_detail_dealplace);
        tvPrice = root.findViewById(R.id.tv_product_detail_price);
        tvMethod = root.findViewById(R.id.tv_product_detail_method);
        tvStar = root.findViewById(R.id.tv_product_detail_current);
        tvExplain = root.findViewById(R.id.tv_product_detail_info);

        tvName.setText(name);
        tvPrice.setText(price + "원");
        tvCategory.setText("-카테고리:" +category);
        tvState.setText("-상품 상태:" +state);
        tvExplain.setText(explain);
        tvPlace.setText("-거래 장소:" +place);
        tvMethod.setText("-거래 방식:" +method);
        tvStar.setText("자세한 사항은 판매자에게 문의하세요!");
    }

    private void getInfo(){
        SharedPreferences pref = Objects.requireNonNull(getActivity()).getSharedPreferences("userinfo",MODE_PRIVATE);
        token = pref.getString("token","");

        uploadItemInfo = ProductOneItemResult.getInstance();
        name = uploadItemInfo.getProductName();
        price = uploadItemInfo.getProductPrice();
        place = uploadItemInfo.getPlace();
        category = uploadItemInfo.getCategory();
        method = uploadItemInfo.getMethod();
        explain = uploadItemInfo.getProductInfo();
        state = uploadItemInfo.getProductState();
    }
}
