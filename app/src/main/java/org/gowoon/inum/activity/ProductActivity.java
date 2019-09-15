package org.gowoon.inum.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterViewPagerProduct;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName, tvPlace, tvPrice, tvInfo, tvMethod, tvCategory, tvState, tvStar;
    LinearLayout btnSeller;

    ArrayList<String> arrayImage;

    AdapterViewPagerProduct vAdapter;

    String declare_kind, declare_senderId, declare_productId;
    String productId, sellerId, fileFolder;

    ViewPager viewPager;
    CircleIndicator indicator;
    com.pm10.library.CircleIndicator circleIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageButton btn_left = findViewById(R.id.btn_product_detail_left);
        ImageButton btn_right = findViewById(R.id.btn_product_detail_right);

        ImageButton btn_declare = findViewById(R.id.btn_product_detail_declare);

        final RadioGroup declareRadioGroup = findViewById(R.id.radio_group_declare);

        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

//        btn_declare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Adapter_dialog_declare dialog_declare = new Adapter_dialog_declare(ProductActivity.this);
//                dialog_declare.show();
//                dialog_declare.setOnDeclareButtonClickListener(new Adapter_dialog_declare.OnDeclareButtonClickListener() {
//                    @Override
//                    public void onClick() {
//
//                        Singleton.retrofit.report(declare_kind, declare_senderId, declare_productId)
//                                .enqueue(new Callback<JsonObject>() {
//                                    @Override
//                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                                        if(response.isSuccessful()){
//
//                                            declareRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                                @Override
//                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                                    switch (checkedId){
//                                                        case R.id.radio_btn_declare_obsence:
//                                                            Singleton.retrofit.report("음란성",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                            break;
//                                                        case R.id.radio_btn_declare_advertisement:
//                                                            Singleton.retrofit.report("광고",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                            break;
//                                                        case R.id.radio_btn_declare_overlap:
//                                                            Singleton.retrofit.report("도배",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                            break;
//                                                        case R.id.radio_btn_declare_fake:
//                                                            Singleton.retrofit.report("허위상품",UserData.getInstance().getSchoolID(),ProductOneItemResult.getInstance().getProductId());
//                                                            break;
//                                                    }
//                                                }
//                                            });
//
//                                            Log.v("kind", declare_kind+" senderId: "+ declare_senderId+ " productID: " + declare_productId);
//                                            JsonObject result = response.body();
//                                            if (result.get("ans").equals("true")) {
//                                                Log.v("declare", "성공");
//                                                Adapter_dialog_onebutton dialog_onebutton = new Adapter_dialog_onebutton(ProductActivity.this, "상품 신고가 완료되었습니다.");
//                                                dialog_onebutton.show();
//                                                dialog_onebutton.setOnOkButtonClickListener(new Adapter_dialog_onebutton.OnOkButtonClickListener() {
//                                                    @Override
//                                                    public void onClick() {
//                                                        Intent intent_declare = new Intent(String.valueOf(ProductActivity.class));
//                                                        startActivity(intent_declare);
//                                                    }
//                                                });
//                                            }
//                                            else {
//                                                Log.v("declare", "실패");
//                                                Toast.makeText(ProductActivity.this,"신고에 실패하였습니다. 다시 시도해주세요",Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    }
//                                    @Override
//                                    public void onFailure(Call<JsonObject> call, Throwable t) {
//                                        Toast.makeText(ProductActivity.this,"서버 연결상태를 확인해주세요",Toast.LENGTH_SHORT)
//                                                .show();
//                                    }
//                                });
//                    }
//                });
//                dialog_declare.setOnDeclareCancelButtonClickListener(new Adapter_dialog_declare.OnCancelButtonClickListener() {
//                    @Override
//                    public void onClick() {
//                        Toast.makeText(ProductActivity.this, "신고를 취소하였습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//

        arrayImage = new ArrayList<>();

        btnSeller = findViewById(R.id.layout_detail_other_product);
        btnSeller.setOnClickListener(this);

        tvName = findViewById(R.id.tv_product_detail_name);
        tvPlace = findViewById(R.id.tv_product_detail_dealplace);
        tvPrice = findViewById(R.id.tv_product_detail_price);
        tvInfo = findViewById(R.id.tv_product_detail_info);
        tvMethod = findViewById(R.id.tv_product_detail_method);
        tvCategory = findViewById(R.id.tv_product_detail_category);
        tvState = findViewById(R.id.tv_product_detail_status);
        tvStar = findViewById(R.id.tv_product_detail_current);

        viewPager = findViewById(R.id.viewpager_product_detail_image);
//        indicator = findViewById(R.id.indicator_product_image);
        circleIndicator = findViewById(R.id.indicator_product_circle);

        Intent intent = getIntent();
        productId = intent.getStringExtra("id");
        Log.d("product id", productId);

        SharedPreferences pref = getSharedPreferences("userinfo",MODE_PRIVATE);
        String token = pref.getString("token","");

        if (productId != null){
            Singleton.retrofit.productOneItem(token,productId).enqueue(new Callback<ProductOneItemResult>() {
                @Override
                public void onResponse(Call<ProductOneItemResult> call, Response<ProductOneItemResult> response) {
                    if (response.isSuccessful()) {
                        ProductOneItemResult result = response.body();

                        Log.d("product","product info load");

                        arrayImage.addAll(Objects.requireNonNull(result).getProductImg());

                        vAdapter = new AdapterViewPagerProduct(getApplicationContext(), arrayImage, result.getFileFolder(), result.getSellerId());
                        viewPager.setAdapter(vAdapter);

                        circleIndicator.setupWithViewPager(viewPager);
                        circleIndicator.bringToFront();

//                        Intent intentSeller = new Intent(getApplicationContext(),SellerProduct.class);
//                        intentSeller.putExtra("sellerId",sellerId);

                        sellerId = result.getSellerId();

                        tvName.setText(result.getProductName());
                        tvPrice.setText(result.getProductPrice() + " 원");
                        tvState.setText("- 거래 상태: " + result.getProductState());
                        tvMethod.setText("- 거래 방식: " +result.getMethod());
                        tvPlace.setText("- 거래 장소: " +result.getPlace());
                        tvCategory.setText("- 카테고리: " +result.getCategory());
                        tvInfo.setText(result.getProductInfo());
                        tvStar.setText("현재 " + result.getProductStar()+"명의 학생들이 문의중입니다!");
                    }
                }

                @Override
                public void onFailure(Call<ProductOneItemResult> call, Throwable t) {
                    Log.d("retrofit fail","연결상태확인");
//                    Toast.makeText(getApplicationContext(),"네트워크에러 서버나 통신상태 확인",Toast.LENGTH_LONG).show();
                }
            });
        }

        ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int curr = viewPager.getCurrentItem();
        int last = vAdapter.getCount()-1;
        int next = curr+1;
        int prev = curr-1;
        switch (v.getId()) {
//            case R.id.btn_productdetail_otherproduct:{
//                startActivity(intent_seller);
//                break;
//            }
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