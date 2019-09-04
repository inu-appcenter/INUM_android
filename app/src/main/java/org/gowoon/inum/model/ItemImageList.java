package org.gowoon.inum.model;

import android.net.Uri;

import lombok.Data;

@Data
public class ItemImageList {
    private Uri imageUri;
    private boolean addImg;

    public ItemImageList(Uri imageUri, boolean add) {
        this.imageUri = imageUri;
        this.addImg = add;
    }
}
