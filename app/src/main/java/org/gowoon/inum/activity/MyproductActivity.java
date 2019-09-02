package org.gowoon.inum.activity;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.SearchIdResult;
import org.gowoon.inum.recycler.Adapter_recycler_MyProduct;
import org.gowoon.inum.util.Singleton;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyproductActivity extends AppCompatActivity {

    RecyclerView myproduct;
    Adapter_recycler_MyProduct mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproduct);

        SharedPreferences pref = Objects.requireNonNull(this).getSharedPreferences("userinfo",MODE_PRIVATE);
        String id = pref.getString("userid","");
        myproduct = findViewById(R.id.recyclerview_myproduct);
        mAdapter = new Adapter_recycler_MyProduct();

        Singleton.retrofit.searchId(pref.getString("token",""),id).enqueue(new Callback<ArrayList<SearchIdResult>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchIdResult>> call, Response<ArrayList<SearchIdResult>> response) {
                if (response.isSuccessful()){
                    ArrayList<SearchIdResult> results = response.body();
                    assert results != null;
                    for (int i = 0 ; i < results.size();i++){
                        mAdapter.addItem(results.get(i));
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<SearchIdResult>> call, Throwable t) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myproduct.setLayoutManager(linearLayoutManager);

        myproduct.setAdapter(mAdapter);
    }
}
