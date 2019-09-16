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
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener;
import gun0912.tedimagepicker.builder.type.MediaType;

public class UploadImageFragment extends Fragment {

    private AdapterRecyclerUploadImage rAdapter = new AdapterRecyclerUploadImage();
    private RecyclerView recyclerViewImage ;
    private LinearLayout layoutSelect;
    TextView tvAddImage, tvDelete;
    private ArrayList<Uri> mListImage = new ArrayList<>();
    List<String> imageList = new ArrayList<>();

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

        getActivity().findViewById(R.id.tv_upload_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadPreviewFragment uploadPreview = new UploadPreviewFragment();

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .replace(R.id.constraint_upload, uploadPreview)
                        .addToBackStack(null)
                        .commit();
            }
        });

        layoutSelect = rootView.findViewById(R.id.linearLayout_upload_image_select);
        recyclerViewImage = rootView.findViewById(R.id.recyclerview_upload_image);

        mListImage.add(Uri.parse(""));
        rAdapter.addItem(mListImage);

        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerViewImage.setLayoutManager(mLayoutManager);
        recyclerViewImage.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImage.addItemDecoration(new UploadItemDecoration(getContext()));
        recyclerViewImage.setAdapter(rAdapter);
        rAdapter.setItemClick(new AdapterRecyclerUploadImage.ItemClick() {
            @Override
            public void onClick(View view, final int position) {
                layoutSelect.setVisibility(View.VISIBLE);
                tvAddImage.setVisibility(View.VISIBLE);
                if (rAdapter.getItemStyle(position)){
                    tvAddImage.setText("취소");
                    tvDelete.setVisibility(View.VISIBLE);
                    tvDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteImage(position);
                        }
                    });
                }else {
//                    layoutSelect.setVisibility(View.VISIBLE);
                    tvAddImage.setText("여러장 선택");
                    tvDelete.setVisibility(View.INVISIBLE);
                    tvAddImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getFromAlbum();
                        }
                    });
                }
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
                .startMultiImage(new OnMultiSelectedListener() {
                    @Override
                    public void onSelected(List<? extends Uri> list) {
                        mListImage.addAll(list);
                        rAdapter.addItem(mListImage);
                        if (rAdapter.getItemCount()==9){
                            rAdapter.notifyItemRemoved(8);
                            rAdapter.notifyItemRangeChanged(8, list.size());
                            Log.d("image list count", String.valueOf(rAdapter.getItemCount()));
                        }
                        recyclerViewImage.setAdapter(rAdapter);
                        layoutSelect.setVisibility(View.INVISIBLE);

                        setImageData(mListImage);
                    }
                });
    }

    private void setImageData(ArrayList list){
        ProductOneItemResult.getInstance().setProductImg(list);
        ItemImageList.getInstance().setImageUri(list);
    }

    private void deleteImage(int position){
        rAdapter.mData.remove(position);
        rAdapter.notifyItemRemoved(position);
        rAdapter.notifyItemRangeRemoved(position,rAdapter.mData.size());
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
}
