package com.musta.mvvmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.musta.mvvmexample.room.entities.Message;
import com.musta.mvvmexample.viewmodels.MessageViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MessageViewModel messageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        messageViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                Log.i(TAG, "onChanged: "+messages.size());
            }
        });
    }
}
