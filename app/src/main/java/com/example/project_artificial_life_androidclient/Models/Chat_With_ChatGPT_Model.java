package com.example.project_artificial_life_androidclient.Models;

import android.util.Log;

import com.example.project_artificial_life_androidclient.APIes.Connections.ChatGPTRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Response;

public class Chat_With_ChatGPT_Model implements Chat_With_ChatGPT_Contract.Model {

    public Chat_With_ChatGPT_Model() {
        messagesHistory = new LinkedList<ChatGPT_Message>();
        /*ChatGPT_Message message = new ChatGPT_Message();
        message.setRole("user");
        message.setContent("Hello!");
        messagesHistory.add(message);
        message = new ChatGPT_Message();
        message.setRole("assistant");
        message.setContent("Hey, I'm artificial intelegance");
        messagesHistory.add(message);*/
    }


    @Override
    public List<ChatGPT_Message> getMessagesHistory() {
        return messagesHistory;
    }

    @Override
    public boolean sendMessageToServer(String message) {

        List<ChatGPT_Message> messagesHistory = FormMessageHistoryForRequest(new ChatGPT_Message("user", message)); //Формируем историю сообщений
        ChatGPT_SendMessage_Request request = new ChatGPT_SendMessage_Request(messagesHistory);
        try
        {
            ChatGPT_Message BotResponseMessage = (new ChatGPTRetrofitConnection()).Get_ChatGPT_API()
                                                                                  .SendMessageToChatGPTWithParameters(request)
                                                                                  .execute()
                                                                                  .body().getChoices().get(0).getMessage();
            messagesHistory.add(BotResponseMessage);
            return true;
        }
        catch (IOException exception)
        {
            Log.println(Log.WARN, "ChatGPT API Connection", "Произошла ошибка при установлении соединения с сервером...");
            return false;
        }
    }


    private List<ChatGPT_Message> messagesHistory;


    private List<ChatGPT_Message> FormMessageHistoryForRequest(ChatGPT_Message userMessage) {
        ArrayList<ChatGPT_Message> messagesHistory = new ArrayList<ChatGPT_Message>();
        messagesHistory.add(new ChatGPT_Message("system", "You are a helpful assistant."));
        messagesHistory.addAll(messagesHistory);
        messagesHistory.add(userMessage);
        return messagesHistory;
    }
}
