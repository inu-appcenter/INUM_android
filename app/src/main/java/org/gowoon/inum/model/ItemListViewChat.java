package org.gowoon.inum.model;

import android.view.View;

public class ItemListViewChat {
    private String name, chattext, yymmdd;
    public View.OnClickListener onClickListener;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChattext() {
        return chattext;
    }

    public void setChatText(String chattext) {
        this.chattext = chattext;
    }

    public String getYymmdd() {
        return yymmdd;
    }

    public void setYymmdd(String yymmdd) {
        this.yymmdd = yymmdd;
    }
}
