package org.gowoon.inum.model;

import android.graphics.drawable.Drawable;

import org.gowoon.inum.fragment.DrawerMypageFragment;

import java.util.Vector;

public class DataCategoryParent {

    private Drawable category_image;
    private String category_name;

    public Vector<String> child;

    public DataCategoryParent(Drawable category_image, String category_name){
        this.category_image = category_image;
        this.category_name = category_name;

        child = new Vector<>();
    }

    public Drawable getCategory_image() {
        return category_image;
    }

    public void setCategory_image(Drawable category_image) {
        this.category_image = category_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
