package com.musta.mvvmexample.room.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.musta.mvvmexample.room.daos.MessageDao;
import com.musta.mvvmexample.room.entities.Message;

@Database(entities = Message.class, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase instance;

    public abstract MessageDao messageDao();

    public static synchronized MessageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MessageDatabase.class, "message_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DummyDBAsyncTask(instance).execute();
        }
    };

    private static class DummyDBAsyncTask extends AsyncTask<Void, Void, Void> {

        private MessageDao messageDao;

        private DummyDBAsyncTask(MessageDatabase database) {
            messageDao = database.messageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 10; i++) {
                messageDao.insert(new Message(i + ". Title", "06-10-2019", i + ". This is a message body."));
            }
            return null;
        }
    }
}
