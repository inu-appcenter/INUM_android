package org.gowoon.inum.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.Upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class upload_photoFragment extends Fragment {

    Button photo_regist;

    Button camera, album;
    Button delete, cancel;

    Button nextstep;
    ImageView photo;

    private final int CAMERA_CODE = 1111;
    private Uri photoUri;
    private String currentPhotoPath; //실제 사진 파일 경로
    String mlmageCaptureName; //이미지 이름

    private static final int PICK_FROM_ALBUM = 1;

    private File tempFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_upload_photo, container, false);

        photo_regist = rootview.findViewById(R.id.btn_upload_photo);
        camera = rootview.findViewById(R.id.btn_upload_photo_camera);
        album = rootview.findViewById(R.id.btn_upload_photo_album);
        delete = rootview.findViewById(R.id.btn_upload_photo_delete);
        cancel = rootview.findViewById(R.id.btn_upload_photo_cancle);

        nextstep = rootview.findViewById(R.id.btn_upload_photo_nextstep);

//        photo_regist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                camera.setVisibility(View.VISIBLE);
//                album.setVisibility(View.VISIBLE);
//
//                camera.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        private void selectPhoto() {
//                            String state = Environment.getExternalStorageState();
//                            if(Environment.MEDIA_MOUNTED.equals(state)){
//                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                if(intent.resolveActivity(getPackageManager()) != null){
//                                    File photoFile = null;
//                                    try{
//                                        photoFile = createImageFile();
//                                    }catch (IOException ex){
//                                    }
//                                    if(photoFile != null){
//                                        photoUri = FileProvider.getUriForFile(this,getPackageName(),photoFile);
//                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                                        startActivityForResult(intent, CAMERA_CODE);
//                                    }
//                                }
//                            }
//                        }
//
//                        private File createImageFile() throw IOException{
//                        }
//                    }
//                });
//
//
//                album.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//
//                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), UploadingHelper.REQUEST_CODE);
//                    }
//                });
//            }
//        });
        return rootview;
    }
}