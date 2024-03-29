package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.project_artificial_life_androidclient.APIes.Connections.ChatGPTRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Presenters.Chat_With_ChatGPT_Presenter;
import com.example.project_artificial_life_androidclient.RecyclerViewAdapters.ChatGPTMessageHistoryAdapter;
import com.example.project_artificial_life_androidclient.Services.ClassifiedSwipe;
import com.example.project_artificial_life_androidclient.databinding.ChatWithChatgptBinding;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_With_ChatGPT_View extends AppCompatActivity implements Chat_With_ChatGPT_Contract.View{

    //Public Methods
    public ChatWithChatgptBinding getBinding()
    {
        return binding;
    }

    @Override
    public void RenderOfAddingNewMessageToList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RenderNewMessageInChat();
                binding.enterMessageToChatField.getText().clear();
                UnlockUserInputSystem();
            }
        });
    }

    @Override
    public void InformUserAboutProblemsWithChatGPTConnection() {
        chatListAdapter.notifyItemRemoved(chatListAdapter.getItemCount());
        Toast.makeText(this, "Сообщение не было доставлено!\nПовторите попытку отправки", Toast.LENGTH_SHORT).show();
        UnlockUserInputSystem();
    }

    @Override //Рендер нового сообщения в чате
    public void RenderNewMessageInChat() {
        chatListAdapter.notifyItemInserted(chatListAdapter.getItemCount() - 1);
    }


    //Protected Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithChatgptBinding.inflate(getLayoutInflater());
        presenter = new Chat_With_ChatGPT_Presenter(this);
        setContentView(binding.getRoot());
        SetAllListeners();
        SetMessageHistoryRecyclerView();
    }


    //Private Fields
    @Nullable
    private ChatWithChatgptBinding binding = null;

    @Nullable
    private ChatGPTMessageHistoryAdapter chatListAdapter;

    @NonNull
    private Chat_With_ChatGPT_Contract.Presenter presenter;


    //Private Methods
    private void SetAllListeners() {

        Activity activity = this;
        binding.sendMessageToChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMessage = binding.enterMessageToChatField.getText().toString();
                if(userMessage.length() == 0) return;
                LockUserInputSystem();
                presenter.SendUserMessageToChat(userMessage);
            }
        });
        binding.GoToNextPageChatGPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, Chat_With_Kandinsky_View.class);
                startActivity(intent);
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