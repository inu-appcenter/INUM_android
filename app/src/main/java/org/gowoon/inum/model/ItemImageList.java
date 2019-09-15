package org.gowoon.inum.model;

import android.net.Uri;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ItemImageList {
    private ArrayList<Uri> imageUri;

    private static ItemImageList productImageData = new ItemImageList();

    public static ItemImageList getInstance() {
        return productImageData;
    }
}
