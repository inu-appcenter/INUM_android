package org.gowoon.inum.model;

import java.util.ArrayList;

public class ItemListviewMainProduct {
    private ArrayList<MainProductResult> allItemsInCategory;
    private String txt_category ;

    public ItemListviewMainProduct(String txt_category, ArrayList<MainProductResult> allItemsInCategory ){
        this.txt_category = txt_category;
        this.allItemsInCategory = allItemsInCategory;
    }

    public ArrayList<MainProductResult> getAllItemsInCategory() {
        return allItemsInCategory;
    }

    public void setAllItemsInCategory(ArrayList<MainProductResult> allItemsInCategory){
        this.allItemsInCategory = allItemsInCategory;
    }

    public String getTxt_category() {
        return this.txt_category;
    }

    public void setTxt_category(String txt_category) {
        this.txt_category = txt_category;
    }
}
