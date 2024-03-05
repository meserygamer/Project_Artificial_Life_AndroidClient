package com.example.project_artificial_life_androidclient.Presenters;

import androidx.annotation.NonNull;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.Models.Chat_With_ChatGPT_Model;

import java.util.LinkedList;
import java.util.List;

public class Chat_With_ChatGPT_Presenter implements Chat_With_ChatGPT_Contract.Presenter{

    public Chat_With_ChatGPT_Presenter(Chat_With_ChatGPT_Contract.View view){
        this.view = view;
        model = new Chat_With_ChatGPT_Model();
    }

    @Override
    public List<ChatGPT_Message> getMessagesHistory() {
        return model.getMessagesHistory();
    }


    @NonNull
    private Chat_With_ChatGPT_Contract.View view;

    @NonNull
    private Chat_With_ChatGPT_Contract.Model model;

}
