package com.example.project_artificial_life_androidclient.APIes.Interfaces;

import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatGPT_API_Interface {

    @POST("chatGPT/sendMessageToChatBot")
    public ChatGPT_SendMessage_Response SendMessageToChatGPTWithParameters(@Body ChatGPT_SendMessage_Request messages);
}
