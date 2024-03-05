package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.project_artificial_life_androidclient.APIes.Connections.ChatGPTRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Presenters.Chat_With_ChatGPT_Presenter;
import com.example.project_artificial_life_androidclient.RecyclerViewAdapters.ChatGPTMessageHistoryAdapter;
import com.example.project_artificial_life_androidclient.databinding.ChatWithChatgptBinding;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_With_ChatGPT_View extends AppCompatActivity implements Chat_With_ChatGPT_Contract.View {

    public ChatWithChatgptBinding getBinding()
    {
        return binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithChatgptBinding.inflate(getLayoutInflater());
        presenter = new Chat_With_ChatGPT_Presenter(this);
        setContentView(binding.getRoot());
        SetAllListeners();
        SetMessageHistoryRecyclerView();
        ChatGPT_SendMessage_Request request = new ChatGPT_SendMessage_Request();
        List<ChatGPT_Message> messagesHistory = new LinkedList<ChatGPT_Message>();
        ChatGPT_Message message = new ChatGPT_Message("user", "hello! What do you do?");
        messagesHistory.add(message);
        request.setMessages(messagesHistory);
        (new ChatGPTRetrofitConnection()).Get_ChatGPT_API().SendMessageToChatGPTWithParameters(request).enqueue(new Callback<ChatGPT_SendMessage_Response>() {
            @Override
            public void onResponse(Call<ChatGPT_SendMessage_Response> call, Response<ChatGPT_SendMessage_Response> response) {
                Log.println(Log.DEBUG, "ChatGPTAPI", "Я получил ответ: " + response.body().getChoices().get(0).getMessage().getContent());
            }

            @Override
            public void onFailure(Call<ChatGPT_SendMessage_Response> call, Throwable t) {
                Log.println(Log.DEBUG, "ChatGPTAPI", "Я провалился босс: " + t.getMessage());
            }
        });
    }


    private @Nullable ChatWithChatgptBinding binding = null;

    private Chat_With_ChatGPT_Contract.Presenter presenter;


    private void SetAllListeners() {
        binding.sendMessageToChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

    private void SetMessageHistoryRecyclerView() {
        binding.MessageHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.MessageHistoryRecyclerView.setAdapter(new ChatGPTMessageHistoryAdapter(presenter.getMessagesHistory()));
    }


}