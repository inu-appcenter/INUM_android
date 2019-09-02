package org.gowoon.inum.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import org.gowoon.inum.R;
import org.gowoon.inum.model.ItemListViewChat;
import org.gowoon.inum.recycler.Adapter_recycler_Chat;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity{
    private Adapter_recycler_Chat adapter;
    private RecyclerView recycler_chat;
    ArrayList<ItemListViewChat> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
//        adapter = new Adapter_recycler_Chat();
//        recycler_chat = findViewById(R.id.recyclerview_chat);
//
//        ItemListViewChat chatlist = new ItemListViewChat();
//
//        adapter.addItem(chatlist);
//
//        recycler_chat.setAdapter(adapter);
    }
}
