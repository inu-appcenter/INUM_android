package org.gowoon.inum.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemImageList;
import org.gowoon.inum.recycler.AdapterRecyclerUploadImage;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class UploadImageFragment extends Fragment {

    AdapterRecyclerUploadImage rAdapter = new AdapterRecyclerUploadImage();
    ItemImageList list;
    RecyclerView recyclerViewImage;
    LinearLayout layoutSelect;
    TextView tvAddImage, tvCamera;

    private static final int SELECT_ALBUM = 1, SELECT_CAMERA = 2, IMAGE_OUTPUT_SIZE = 300;
    private static final int CROP_FROM_CAMERA = 3, CROP_FROM_ALBUM =4;
    private String filePath;
    private Uri imageCaptureUri;

    public UploadImageFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_upload_image, container, false);

        makePermission();

        layoutSelect = rootView.findViewById(R.id.linearLayout_upload_image_select);
        recyclerViewImage = rootView.findViewById(R.id.recyclerview_upload_image);
        list = new ItemImageList(Uri.parse(""),true);

        rAdapter.addItem(list);
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerViewImage.setLayoutManager(mLayoutManager);
        recyclerViewImage.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImage.setAdapter(rAdapter);
        rAdapter.setItemClick(new AdapterRecyclerUploadImage.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                layoutSelect.setVisibility(View.VISIBLE);
//                view.findViewById(R.id.view_upload_item_opacity).setVisibility(View.VISIBLE);
                tvAddImage = rootView.findViewById(R.id.tv_upload_image_multi);
                tvAddImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFromAlbum();
                    }
                });
                tvCamera = rootView.findViewById(R.id.tv_upload_image_camera);
                tvCamera.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        getFromCamera();
                    }
                });
            }
        });


        return rootView;
    }

    public void getFromAlbum(){
        Intent intentImage = new Intent(Intent.ACTION_PICK);
        intentImage.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intentImage,SELECT_ALBUM);

    }
    public void getFromCamera(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String imageURL = "tmp_"+ String.valueOf(System.currentTimeMillis()) +".jpg";
        imageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imageURL));

        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,imageCaptureUri);
        startActivityForResult(intentCamera,SELECT_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_ALBUM: {
                    imageCaptureUri = data.getData();
                    Log.d("album uri", imageCaptureUri.getPath());
                }
                // 리사이즈할 이미지 크기 결정, 크롭 어플리케이션 호출
                case SELECT_CAMERA: {
                    Intent intentCameraSize = new Intent("com.android.camera.action.CROP");
                    intentCameraSize.setDataAndType(imageCaptureUri, "image/*");

                    //crop image size
                    intentCameraSize.putExtra("outputX", IMAGE_OUTPUT_SIZE);
                    intentCameraSize.putExtra("outputY", IMAGE_OUTPUT_SIZE);
                    // crop box 비율
                    intentCameraSize.putExtra("aspectX", 1);
                    intentCameraSize.putExtra("outputX", 1);

                    intentCameraSize.putExtra("scale", true);
                    intentCameraSize.putExtra("return-data", true);

                    startActivityForResult(intentCameraSize, CROP_FROM_CAMERA);

                    break;
                }
                case CROP_FROM_CAMERA: {
                    //크롭된 이미지 받기
                    final Bundle extra = data.getExtras();
                    filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel/" + System.currentTimeMillis() + ".jpg";

                    if (extra != null) {
                        Bitmap photo = extra.getParcelable("data");


                    }
                }
            }
        }
    }
    public void makePermission(){
        PermissionListener permissionListener = new PermissionListener(){
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
//                TedPermission.with(getContext())
//                        .setRationaleTitle("권한 허용")
//                        .setRationaleMessage("사진 업로드를 위해 권한을 허용해주세요")
//                        .setDeniedMessage("나중에 [설정]>[권한]에서 허용할 수 있습니다")
//                        .setPermissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .check();
                Toast.makeText(getActivity(), "다음 권한이 거부되었습니다\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(getContext())
                .setPermissionListener(permissionListener)
                .setRationaleTitle("권한 허용")
                .setRationaleMessage("사진 업로드를 위해 권한을 허용해주세요")
                .setDeniedMessage("나중에 [설정]>[권한]에서 허용할 수 있습니다")
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)

                .check();
    }
}
