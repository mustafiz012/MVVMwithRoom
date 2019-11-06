package com.musta.mvvmexample.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.musta.mvvmexample.repositories.MessageRepository;
import com.musta.mvvmexample.room.entities.Message;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    private MessageRepository repository;
    private LiveData<List<Message>> allMessages;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        repository = new MessageRepository(application);
        allMessages = repository.getAllMessages();
    }

    public void insert(Message message) {
        repository.insert(message);
    }

    public void update(Message message) {
        repository.update(message);
    }

    public void delete(Message message) {
        repository.delete(message);
    }

    public void deleteAllMessages() {
        repository.deleteAllMessages();
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }
}
