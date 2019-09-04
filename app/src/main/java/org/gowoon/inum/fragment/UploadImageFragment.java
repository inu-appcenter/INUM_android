package org.gowoon.inum.fragment;


import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
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
import org.gowoon.inum.recycler.AdapterRecyclerUploadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener;
import gun0912.tedimagepicker.builder.type.MediaType;

public class UploadImageFragment extends Fragment {

    private AdapterRecyclerUploadImage rAdapter = new AdapterRecyclerUploadImage();
    ArrayList<ItemImageList> rList;
    ItemImageList itemImageList;
    private RecyclerView recyclerViewImage ;
    private LinearLayout layoutSelect;
    TextView tvAddImage, tvCamera;
    private ArrayList<Uri> mListImage = new ArrayList<>();

//    private static final int SELECT_ALBUM = 1, SELECT_CAMERA = 2, IMAGE_OUTPUT_SIZE = 191;
//    private static final int CROP_FROM_CAMERA = 3, CROP_FROM_ALBUM =4, REQUEST_IMAGE_CAPTURE=5;
//    private String filePath;
//    private Uri imageCaptureUri;
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
        recyclerViewImage.setAdapter(rAdapter);
        rAdapter.setItemClick(new AdapterRecyclerUploadImage.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                layoutSelect.setVisibility(View.VISIBLE);
                tvAddImage = rootView.findViewById(R.id.tv_upload_image_multi);
                tvAddImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFromAlbum();
                    }
                });
            }
        });
        return rootView;
    }

    private void getFromAlbum(){
        TedImagePicker.with(Objects.requireNonNull(getContext()))
                .mediaType(MediaType.IMAGE)
                .cameraTileBackground(R.color.orangey_red)
                .max(9-rAdapter.getItemCount(),"이미지는 최대 8장까지 선택 가능합니다.")
                .startMultiImage(new OnMultiSelectedListener() {
                    @Override
                    public void onSelected(List<? extends Uri> list) {
                        mListImage.addAll(list);
//                        rAdapter.popAddButton();
                        mListImage.remove(0);
                        rAdapter.addItem(mListImage);
                        recyclerViewImage.setAdapter(rAdapter);
                        layoutSelect.setVisibility(View.INVISIBLE);
                    }
                });
    }
//
//    private void getFromCamera(){
//        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        startActivityForResult(intentCamera, SELECT_CAMERA);
//        File photoFile = null;
//        try {
//            photoFile = createImageFile();
//        } catch (IOException e) {
//            Toast.makeText(getContext(), "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
//        }
//        if (photoFile != null) {
//           imageCaptureUri = FileProvider.getUriForFile(getContext(),
//                    "com.example.test.provider", photoFile); //FileProvider의 경우 이전 포스트를 참고하세요.
//            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageCaptureUri); //사진을 찍어 해당 Content uri를 photoUri에 적용시키기 위함
//            startActivityForResult(intentCamera, SELECT_CAMERA);
//        }
////        String imageURL = "tmp_"+ String.valueOf(System.currentTimeMillis()) +".jpg";
////        imageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imageURL));
////
////        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,imageCaptureUri);
////        startActivityForResult(intentCamera,SELECT_CAMERA);
//    }

//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = "fuck";//new SimpleDateFormat("HHmmss").format(new Date());
//        String imageFileName = "IP" + timeStamp + "_";
//        File storageDir = new File(Environment.getExternalStorageDirectory() + "/test/"); //test라는 경로에 이미지를 저장하기 위함
//        if (!storageDir.exists()) {
//            storageDir.mkdir();
//        }
//        File image = File.createTempFile(
//                imageFileName,
//                ".jpg",
//                storageDir
//        );
//        return image;
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
//            Log.d("Matisse","ItemImageList : " + itemImageList);
//            for (int i = 0 ; i < itemImageList.size() ; i++){
//                rAdapter.addItem(itemImageList.get(i));
//            }
//        }
//    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case SELECT_ALBUM: {
//                    imageCaptureUri = data.getData();
//                    rAdapter.addItem(imageCaptureUri.toString());
//                    Log.d("album uri", imageCaptureUri.toString());
//                }
//                // 리사이즈할 이미지 크기 결정, 크롭 어플리케이션 호출
//                case SELECT_CAMERA: {
//                    Intent intentCameraSize = new Intent("com.android.camera.action.CROP");
//                    intentCameraSize.setDataAndType(imageCaptureUri, "image/*");
//
//                    //crop image size
//                    intentCameraSize.putExtra("outputX", IMAGE_OUTPUT_SIZE);
//                    intentCameraSize.putExtra("outputY", IMAGE_OUTPUT_SIZE);
//                    // crop box 비율
//                    intentCameraSize.putExtra("aspectX", 1);
//                    intentCameraSize.putExtra("outputX", 1);
//
//                    intentCameraSize.putExtra("scale", true);
//                    intentCameraSize.putExtra("return-data", true);
//
//                    startActivityForResult(intentCameraSize, CROP_FROM_CAMERA);
//
//                    break;
//                }
//                case CROP_FROM_CAMERA: {
//                    //크롭된 이미지 받기
//                    final Bundle extra = data.getExtras();
//                    filePath = Environment.getExternalStorageDirectory()
//                            .getAbsolutePath() + "/SmartWheel/" + System.currentTimeMillis() + ".jpg";
//
//                    if (extra != null) {
//                        Bitmap photo = extra.getParcelable("data");
//                        imageList.add(imageCaptureUri+filePath);
////                        imageList.add(String.valueOf(imageCaptureUri));
////                        list.setImageUri(imageList);
////                        imageList = itemImageList;
//                        Log.d("image crop", filePath);
//                        rAdapter.addItem(imageList.get(0));
//                        rAdapter.notifyDataSetChanged();
//                        break;
//                    }
//                }
//            }
//        }
//        return;
//    }
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
