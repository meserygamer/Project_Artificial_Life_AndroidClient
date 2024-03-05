package com.example.project_artificial_life_androidclient.Models;

import android.util.Log;

import com.example.project_artificial_life_androidclient.APIes.Connections.ChatGPTRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_With_ChatGPT_Model implements Chat_With_ChatGPT_Contract.Model {

    public Chat_With_ChatGPT_Model() {
        messagesHistory = new LinkedList<ChatGPT_Message>();
    }


    @Override
    public List<ChatGPT_Message> getMessagesHistory() {
        return messagesHistory;
    }

    @Override
    public void sendMessageToServer(String message, Action successCallBack, Action failCallBack) {

        List<ChatGPT_Message> messagesHistoryForRequest = FormMessageHistoryForRequest(new ChatGPT_Message("user", message)); //Формируем историю сообщений
        ChatGPT_SendMessage_Request request = new ChatGPT_SendMessage_Request(messagesHistoryForRequest);
        (new ChatGPTRetrofitConnection()).Get_ChatGPT_API().SendMessageToChatGPTWithParameters(request).enqueue(new Callback<ChatGPT_SendMessage_Response>() {
            @Override
            public void onResponse(Call<ChatGPT_SendMessage_Response> call, Response<ChatGPT_SendMessage_Response> response) {
                if(response.code() != 200){
                    Log.println(Log.WARN, "ChatGPT API Connection", "При отправке что-то пошло не так\n" + response.message());
                    failCallBack.DoAction();
                    return;
                }
                ChatGPT_Message BotResponseMessage = response.body().getChoices().get(0).getMessage();
                Log.println(Log.INFO, "ChatGPT API Connection", BotResponseMessage.getContent());
                messagesHistory.add(BotResponseMessage);
                successCallBack.DoAction();
                return;
            }

            @Override
            public void onFailure(Call<ChatGPT_SendMessage_Response> call, Throwable t) {
                Log.println(Log.WARN, "ChatGPT API Connection", "Произошла ошибка при установлении соединения с сервером...");
                failCallBack.DoAction();
                return;
            }
        });
    }


    private List<ChatGPT_Message> messagesHistory;


    private List<ChatGPT_Message> FormMessageHistoryForRequest(ChatGPT_Message userMessage) {
        ArrayList<ChatGPT_Message> messagesHistoryForRequest = new ArrayList<ChatGPT_Message>();
        messagesHistoryForRequest.add(new ChatGPT_Message("system", "You are a helpful assistant."));
        messagesHistoryForRequest.addAll(messagesHistory);
        messagesHistoryForRequest.add(userMessage);
        return messagesHistoryForRequest;
    }
}
