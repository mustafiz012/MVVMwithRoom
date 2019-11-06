package com.musta.mvvmexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musta.mvvmexample.room.entities.Message;
import com.musta.mvvmexample.viewmodels.MessageViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMessageList = findViewById(R.id.rv_message_list);
        rvMessageList.setLayoutManager(new LinearLayoutManager(this));
        rvMessageList.setHasFixedSize(true);

        MessageAdapter messageAdapter = new MessageAdapter();
        rvMessageList.setAdapter(messageAdapter);

        MessageViewModel messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messageAdapter.setMessageList(messages);
            }
        });
    }
}
