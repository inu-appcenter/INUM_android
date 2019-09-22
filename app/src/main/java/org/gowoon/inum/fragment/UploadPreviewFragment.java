package org.gowoon.inum.fragment;


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.JsonObject;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.custom.AdapterViewPagerProduct;
import org.gowoon.inum.model.ItemImageList;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.util.Singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import id.zelory.compressor.Compressor;
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
public class UploadPreviewFragment extends Fragment implements View.OnClickListener{
    ProductOneItemResult uploadItemInfo = new ProductOneItemResult();
    TextView tvName, tvState, tvPrice, tvPlace, tvMethod, tvStar, tvExplain, tvCategory;
    String name, state, place, method, explain, category, userId, token;
    int price;

    private ArrayList<Uri> imageUriList = new ArrayList<>();
    private ArrayList<MultipartBody.Part> imageFileList = new ArrayList<>();
    private AdapterViewPagerProduct mAdapter;

    private ViewPager viewPager;
    private com.pm10.library.CircleIndicator circleIndicator;

    private static final String TAG = "Upload Preview";

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
        includeViewSet(rootView);

        try {
            uriToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setViewPager();

        tvUpload.setOnClickListener(view -> {
            Singleton.retrofit.productUpload(token,imageFileList,name,state,price,category,explain,method,place)
                    .enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()){
                                if (response.code()==200){
                                    Toast.makeText(getActivity(),"등록 완료",Toast.LENGTH_LONG).show();
                                    Log.d(TAG,"업로드 성공");
                                    Objects.requireNonNull(getActivity()).finish();
                                }
                            }
                            else {
                                Toast.makeText(getActivity(), "등록 실패", Toast.LENGTH_LONG).show();
                                Log.d(TAG,"response err");
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.d(TAG,"retrofit on failure");
                            t.getMessage();
                            Log.d(TAG+"error",t.getMessage());
                        }
                    });
        });

        return rootView;
    }

    private void setViewPager(){
        mAdapter = new AdapterViewPagerProduct(getContext(),imageUriList);
        viewPager.setAdapter(mAdapter);

        circleIndicator.setupWithViewPager(viewPager);
        circleIndicator.bringToFront();
    }

    private void includeViewSet(View root){
        ImageButton declareBtn = root.findViewById(R.id.btn_product_detail_declare);
        LinearLayout sellersBtn = root.findViewById(R.id.layout_detail_other_product);

        declareBtn.setVisibility(View.GONE);
        sellersBtn.setVisibility(View.GONE
        );
    }

    private void uriToFile() throws IOException {
        imageUriList = ItemImageList.getInstance().getImageUri();
        FileOutputStream outputStream = null;

        for (int i = 0 ; i < imageUriList.size();i++) {
            File file = new File(imageUriList.get(i).getPath());

            File compressedImageFile = new Compressor(getContext()).compressToFile(file);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), compressedImageFile);
            MultipartBody.Part multiFile = MultipartBody.Part.createFormData("userFile", file.getName(), requestFile);
            imageFileList.add(i,multiFile);
        }
    }

//    private Bitmap resizeImage(Context context, Uri uri){
//        Bitmap resizeBitmap = null;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        int RESIZE= 1024;
//
//        try {
//            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
//
//            int width = options.outWidth;
//            int height = options.outHeight;
//            int sampleSize = 1;
//
//            while (true){
//                if (width/2 < RESIZE|| height/2 < RESIZE){
//                    break;
//                }
//                width /= 2;
//                height /= 2;
//                sampleSize *= 2;
//            }
//
//            options.inSampleSize = sampleSize;
//            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
//            resizeBitmap = bitmap;
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        finally {
//
//        }
//        return resizeBitmap;
//    }

    private void initViewSet(View root){
        circleIndicator = root.findViewById(R.id.indicator_product_circle);
        viewPager = root.findViewById(R.id.viewpager_product_detail_image);
        tvCategory = root.findViewById(R.id.tv_product_detail_category);
        tvName = root.findViewById(R.id.tv_product_detail_name);
        tvState = root.findViewById(R.id.tv_product_detail_status);
        tvPlace = root.findViewById(R.id.tv_product_detail_dealplace);
        tvPrice = root.findViewById(R.id.tv_product_detail_price);
        tvMethod = root.findViewById(R.id.tv_product_detail_method);
        tvStar = root.findViewById(R.id.tv_product_detail_current);
        tvExplain = root.findViewById(R.id.tv_product_detail_info);

        ImageButton btnRight = root.findViewById(R.id.btn_product_detail_right);
        ImageButton btnLeft = root.findViewById(R.id.btn_product_detail_left);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int curr = viewPager.getCurrentItem();
        int last = mAdapter.getCount()-1;
        int next = curr+1;
        int prev = curr-1;
        switch (v.getId()) {
            case R.id.btn_product_detail_right:
            {
                Log.w("product view pager", String.valueOf(curr));
                if (next>last){
                    viewPager.setCurrentItem(0,true);
                }
                else
                    viewPager.setCurrentItem(next);
                break;
            }
            case R.id.btn_product_detail_left:{
                if(prev<0){
                    viewPager.setCurrentItem(last,true);
                }
                else
                    viewPager.setCurrentItem(prev);
                break;
            }
        }
    }
}
