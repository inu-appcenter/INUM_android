package org.gowoon.inum.fragment;


import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.gowoon.inum.R;
import org.gowoon.inum.activity.UploadActivity;
import org.gowoon.inum.model.ItemImageList;
import org.gowoon.inum.model.ProductOneItemResult;
import org.gowoon.inum.recycler.AdapterRecyclerUploadImage;
import org.gowoon.inum.recycler.UploadItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.type.MediaType;

public class UploadImageFragment extends Fragment implements View.OnClickListener {

    private AdapterRecyclerUploadImage rAdapter = new AdapterRecyclerUploadImage();
    private RecyclerView recyclerViewImage ;
    private LinearLayout layoutSelect;
    TextView tvAddImage, tvDelete, tvImageNum , tvNext, tvEmpty;
    private ArrayList<Uri> mListImage = new ArrayList<>();
    List<String> imageList = new ArrayList<>();

    private int imageNum = 0;

    public UploadImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_upload_image, container, false);

        ((UploadActivity)getActivity()).initView("상품 등록하기","다음",true);
        makePermission();
        tvAddImage = rootView.findViewById(R.id.tv_upload_image_multi);
        tvDelete = rootView.findViewById(R.id.tv_upload_image_delete);
        tvImageNum = rootView.findViewById(R.id.tv_upload_image_num);
        tvNext = getActivity().findViewById(R.id.tv_upload_next);
        tvNext.setOnClickListener(this);
        tvEmpty = rootView.findViewById(R.id.tv_upload_image_no_input);

        layoutSelect = rootView.findViewById(R.id.linearLayout_upload_image_select);
        recyclerViewImage = rootView.findViewById(R.id.recyclerview_upload_image);

        if (mListImage.size()==0){
            mListImage.add(Uri.parse(""));
            rAdapter.addItem(mListImage);
        }else{
            tvImageNum.setText(imageNum+"/8");
        }

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerViewImage.setLayoutManager(mLayoutManager);
        recyclerViewImage.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImage.addItemDecoration(new UploadItemDecoration(getContext(),9.6f,11.5f));

        recyclerViewImage.setAdapter(rAdapter);
        rAdapter.setItemClick((view, position) -> {
            layoutSelect.setVisibility(View.VISIBLE);
            tvAddImage.setVisibility(View.VISIBLE);
            if (rAdapter.getItemStyle(position)){
                tvAddImage.setText("취소");
                tvAddImage.setOnClickListener(view12 -> layoutSelect.setVisibility(View.INVISIBLE));
                tvDelete.setVisibility(View.VISIBLE);
                tvDelete.setOnClickListener(view1 -> deleteImage(position));
            }else {
                tvAddImage.setText("여러장 선택");
                tvDelete.setVisibility(View.INVISIBLE);
                tvAddImage.setOnClickListener(view13 -> getFromAlbum());
            }
        });
        return rootView;
    }

    private void getFromAlbum(){
        mListImage.clear();
        TedImagePicker.with(Objects.requireNonNull(getContext()))
                .mediaType(MediaType.IMAGE)
                .cameraTileBackground(R.color.orangey_red)
                .max(9-rAdapter.getItemCount(),"이미지는 최대 8장까지 선택 가능합니다.")
                .startMultiImage(list -> {
                    mListImage.addAll(list);
                    rAdapter.addItem(mListImage);
                    if (rAdapter.getItemCount()==9){
                        rAdapter.notifyItemRemoved(8);
                        rAdapter.notifyItemRangeChanged(8, list.size());
                        Log.d("image list count", String.valueOf(rAdapter.getItemCount()));
                    }
                    recyclerViewImage.setAdapter(rAdapter);
                    layoutSelect.setVisibility(View.INVISIBLE);

                    imageNum = imageNum+mListImage.size();
                    tvImageNum.setText(imageNum+"/8");
                });
    }

    private void setImageData(ArrayList list){
//        ProductOneItemResult.getInstance().setProductImg(list);
        ItemImageList.getInstance().setImageUri(list);
    }

    private void deleteImage(int position){
        rAdapter.mData.remove(position);
        rAdapter.notifyItemRemoved(position);
        rAdapter.notifyItemRangeRemoved(position,rAdapter.mData.size());
        layoutSelect.setVisibility(View.INVISIBLE);

        imageNum = --imageNum;
        tvImageNum.setText(imageNum+"/8");
    }

    private void makePermission(){
        PermissionListener permissionListener = new PermissionListener(){
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getActivity(), "다음 권한이 거부되었습니다\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getContext())
                .setPermissionListener(permissionListener)
                .setRationaleTitle("권한 허용")
                .setRationaleMessage("사진 업로드를 위해 권한을 허용해주세요")
                .setDeniedMessage("나중에 [설정]>[권한]에서 허용할 수 있습니다")
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)

                .check();
    }

    @Override
    public void onClick(View view) {
        if (imageNum==0){
            tvEmpty.setVisibility(View.VISIBLE);
        }else{
            tvEmpty.setVisibility(View.INVISIBLE);
            mListImage.clear();
            mListImage.addAll(rAdapter.mData);
            mListImage.remove(mListImage.size()-1);
            setImageData(mListImage);

            recyclerViewImage.removeAllViews();
            changeFragment();
        }
    }

    private void changeFragment(){
        UploadPreviewFragment uploadPreview = new UploadPreviewFragment();

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .replace(R.id.constraint_upload, uploadPreview)
                .addToBackStack(null)
                .commit();
    }
}
