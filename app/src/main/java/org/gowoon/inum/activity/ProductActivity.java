package org.gowoon.inum.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.gowoon.inum.R;
import org.gowoon.inum.custom.AdapterViewPagerProduct;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvName, tvPlace, tvPrice, tvInfo, tvMethod, tvCategory, tvState, tvStar;
    Button btnSeller;

    ArrayList<String> arrayImage, arrayProductData;

    String productId, sellerId;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageButton btn_left = findViewById(R.id.btn_product_detail_slideleft);
        ImageButton btn_right = findViewById(R.id.btn_product_detail_slideright);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

        arrayImage = new ArrayList<>();
        arrayProductData = new ArrayList<>();

        btnSeller = findViewById(R.id.btn_product_detail_otherproduct);
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

                        arrayImage.addAll(result.getProductImg());
                        arrayProductData.add(result.getProductName());
                        arrayProductData.add(result.getSellerId());
                        arrayProductData.add(result.getCategory());

                        AdapterViewPagerProduct vAdapter = new AdapterViewPagerProduct(getApplicationContext(), arrayImage);
//                        vAdapter.registerDataSetObserver();
                        viewPager.setAdapter(vAdapter);

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
                        tvStar.setText("현재" + result.getProductStar()+"명의 학생들이 문의중입니다!");
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
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_productdetail_otherproduct:{
//                startActivity(intent_seller);
//                break;
//            }
//            case R.id.btn_productdetail_slideright:
//            {
//                if (next>last){
//                    viewPager.setCurrentItem(0,false);
//                }
//                else
//                    viewPager.setCurrentItem(next);
//                break;
//            }
//            case R.id.btn_productdetail_slideleft:{
//                if(prev<0){
//                    viewPager.setCurrentItem(last,false);
//                }
//                else
//                    viewPager.setCurrentItem(prev);
//                break;
//            }
        }
    }
}
