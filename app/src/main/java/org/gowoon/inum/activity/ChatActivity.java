package org.gowoon.inum.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
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
