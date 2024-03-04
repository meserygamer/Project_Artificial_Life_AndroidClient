package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_artificial_life_androidclient.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.databinding.ChatgptAssistantMessageLayoutBinding;
import com.example.project_artificial_life_androidclient.databinding.ChatgptUserMessageLayoutBinding;

import java.util.List;

public class ChatGPTMessageHistoryAdapter extends RecyclerView.Adapter<ChatGPTMessageHistoryAdapterUniversalViewHolder> {

    public ChatGPTMessageHistoryAdapter(List<ChatGPT_Message> chatHistory) {
        this.chatHistory = chatHistory;
    }


    public static final int USER_MESSAGE = 0;
    public static final int ASSISTANT_MESSAGE = 1;


    @NonNull
    @Override
    public ChatGPTMessageHistoryAdapterUniversalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case USER_MESSAGE : return new ChatGPTMessageHistoryAdapterUniversalViewHolder(ChatgptUserMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
            case ASSISTANT_MESSAGE : return new ChatGPTMessageHistoryAdapterUniversalViewHolder(ChatgptAssistantMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext())));
            default: throw new IllegalArgumentException("Неизвестный тип view, при формировании истории сообщений с chatGPT");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatGPTMessageHistoryAdapterUniversalViewHolder holder, int position) {
        if(holder.getBinding() instanceof ChatgptAssistantMessageLayoutBinding)
        {
            ((ChatgptAssistantMessageLayoutBinding)holder.getBinding()).chatGPTAssistantMessageLayoutMessageText.setText(chatHistory.get(position).getContent());
            return;
        }
        if(holder.getBinding() instanceof ChatgptUserMessageLayoutBinding)
        {
            ((ChatgptUserMessageLayoutBinding)holder.getBinding()).chatGPTUserMessageLayoutMessageText.setText(chatHistory.get(position).getContent());
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (chatHistory.get(position).getRole())
        {
            case "user" : return USER_MESSAGE;
            case "assistant" : return ASSISTANT_MESSAGE;
            default: throw new IllegalArgumentException("Постороннее сообщение в истории сообщений с chatGPT!");
        }
    }

    @Override
    public int getItemCount() {
        return chatHistory.size();
    }


    @NonNull
    private List<ChatGPT_Message> chatHistory;
}

