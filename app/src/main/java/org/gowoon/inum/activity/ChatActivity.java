package org.gowoon.inum.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.gowoon.inum.R;
import org.gowoon.inum.recycler.Adapter_listview_Chat;

public class ChatActivity extends AppCompatActivity {
    private Adapter_listview_Chat adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        adapter = new Adapter_listview_Chat();
        listView = findViewById(R.id.listview_chat);



    }
}
