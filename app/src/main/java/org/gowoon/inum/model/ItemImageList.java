package org.gowoon.inum.model;

import android.net.Uri;

public class ItemImageList {
    private Uri imageUri;
    private boolean addimg;

    public ItemImageList(Uri imageUri, boolean addimg) {
        this.imageUri = imageUri;
        this.addimg = addimg;

    }

    public boolean getAddimg() {
        return addimg;
    }

    public void setAddimg(boolean addimg) {
        this.addimg = addimg;
    }


    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
