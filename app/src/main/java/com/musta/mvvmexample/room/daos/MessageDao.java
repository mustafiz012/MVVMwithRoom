package com.musta.mvvmexample.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.musta.mvvmexample.room.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    void insert(Message message);

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);

    @Query("DELETE FROM message_table")
    void deleteAllMessages();

    @Query("SELECT * FROM message_table")
    LiveData<List<Message>> getAllMessages();
}
