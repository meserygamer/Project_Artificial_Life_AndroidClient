package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    public void RenderingOfAddingNewMessageToList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatListAdapter.notifyDataSetChanged();
                binding.enterMessageToChatField.getText().clear();
                UnlockUserInputSystem();
            }
        });
    }

    @Override
    public void InformUserAboutProblemsWithChatGPTConnection() {
        Toast.makeText(this, "Сообщение не было доставлено!\nПовторите попытку отправки", Toast.LENGTH_SHORT).show();
        UnlockUserInputSystem();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithChatgptBinding.inflate(getLayoutInflater());
        presenter = new Chat_With_ChatGPT_Presenter(this);
        setContentView(binding.getRoot());
        SetAllListeners();
        SetMessageHistoryRecyclerView();
    }


    @Nullable
    private ChatWithChatgptBinding binding = null;

    @Nullable
    private ChatGPTMessageHistoryAdapter chatListAdapter;

    @NonNull
    private Chat_With_ChatGPT_Contract.Presenter presenter;


    private void SetAllListeners() {
        binding.sendMessageToChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMessage = binding.enterMessageToChatField.getText().toString();
                if(userMessage.length() == 0) return;
                LockUserInputSystem();
                presenter.SendUserMessageToChat(userMessage);
            }
        });
    }

    private void SetMessageHistoryRecyclerView() {
        binding.MessageHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chatListAdapter = new ChatGPTMessageHistoryAdapter(presenter.getMessagesHistory());
        binding.MessageHistoryRecyclerView.setAdapter(chatListAdapter);
    }

    private void LockUserInputSystem(){
        binding.enterMessageToChatField.setEnabled(false);
        binding.sendMessageToChatButton.setEnabled(false);
    }

    private void UnlockUserInputSystem(){
        binding.enterMessageToChatField.setEnabled(true);
        binding.sendMessageToChatButton.setEnabled(true);
    }
}