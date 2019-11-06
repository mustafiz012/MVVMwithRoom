package com.musta.mvvmexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.musta.mvvmexample.room.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private List<Message> messageList = new ArrayList<>();

    public MessageAdapter() {
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_message_item, parent, false);
        return new MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = messageList.get(position);
        holder.tv_title.setText(message.getSenderTitle());
        holder.tv_message_body.setText(message.getMessageBody());
        holder.tv_date_time.setText(message.getTimeDate());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_message_body;
        private TextView tv_date_time;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_message_body = itemView.findViewById(R.id.tv_message_body);
            tv_date_time = itemView.findViewById(R.id.tv_date_time);
        }
    }
}
