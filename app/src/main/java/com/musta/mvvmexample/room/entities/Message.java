package com.musta.mvvmexample.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "message_table")
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String senderTitle;

    private String timeDate;

    private String messageBody;

    public Message(String senderTitle, String timeDate, String messageBody) {
        this.senderTitle = senderTitle;
        this.timeDate = timeDate;
        this.messageBody = messageBody;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSenderTitle() {
        return senderTitle;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
