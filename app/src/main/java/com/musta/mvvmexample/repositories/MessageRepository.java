package com.musta.mvvmexample.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.musta.mvvmexample.enums.Operation;
import com.musta.mvvmexample.room.daos.MessageDao;
import com.musta.mvvmexample.room.databases.MessageDatabase;
import com.musta.mvvmexample.room.entities.Message;

import java.util.List;

public class MessageRepository {
    private MessageDao messageDao;
    private LiveData<List<Message>> allMessages;

    public MessageRepository(Application application) {
        MessageDatabase database = MessageDatabase.getInstance(application);
        messageDao = database.messageDao();
        allMessages = messageDao.getAllMessages();
    }

    public void insert(Message message) {
        new AsyncTaskOperation(messageDao, Operation.INSERT).execute(message);
    }

    public void update(Message message) {
        new AsyncTaskOperation(messageDao, Operation.UPDATE).execute(message);
    }

    public void delete(Message message) {
        new AsyncTaskOperation(messageDao, Operation.DELETE).execute(message);
    }

    public void deleteAllMessages() {
        new AsyncTaskOperation(messageDao, Operation.DELETE_ALL).execute();
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    private static class AsyncTaskOperation extends AsyncTask<Message, Void, Void> {

        private MessageDao messageDao;
        private Operation operation;

        private AsyncTaskOperation(MessageDao messageDao, Operation operation) {
            this.messageDao = messageDao;
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Message... messages) {
            switch (operation) {
                case INSERT:
                    assert messages != null;
                    messageDao.insert(messages[0]);
                    break;
                case UPDATE:
                    assert messages != null;
                    messageDao.update(messages[0]);
                    break;
                case DELETE:
                    assert messages != null;
                    messageDao.delete(messages[0]);
                    break;
                case DELETE_ALL:
                    messageDao.deleteAllMessages();
                    break;
                default:
            }
            return null;
        }
    }
}
