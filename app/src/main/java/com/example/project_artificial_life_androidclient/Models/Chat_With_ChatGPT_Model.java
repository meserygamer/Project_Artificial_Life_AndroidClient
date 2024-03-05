package com.example.project_artificial_life_androidclient.Models;

import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;

import java.util.LinkedList;
import java.util.List;

public class Chat_With_ChatGPT_Model implements Chat_With_ChatGPT_Contract.Model {

    public Chat_With_ChatGPT_Model() {
        messagesHistory = new LinkedList<ChatGPT_Message>();
        ChatGPT_Message message = new ChatGPT_Message();
        message.setRole("user");
        message.setContent("Hello!");
        messagesHistory.add(message);
        message = new ChatGPT_Message();
        message.setRole("assistant");
        message.setContent("Hey, I'm artificial intelegance");
        messagesHistory.add(message);
    }


    @Override
    public List<ChatGPT_Message> getMessagesHistory() {
        return messagesHistory;
    }


    private List<ChatGPT_Message> messagesHistory;
}
