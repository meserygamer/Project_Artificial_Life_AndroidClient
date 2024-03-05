package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Presenters.Chat_With_ChatGPT_Presenter;
import com.example.project_artificial_life_androidclient.RecyclerViewAdapters.ChatGPTMessageHistoryAdapter;
import com.example.project_artificial_life_androidclient.databinding.ChatWithChatgptBinding;

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
        SetAllListners();
        SetMessageHistoryRecyclerView();
    }


    private @Nullable ChatWithChatgptBinding binding = null;

    private Chat_With_ChatGPT_Contract.Presenter presenter;


    private void SetAllListners() {

    }

    private void SetMessageHistoryRecyclerView() {
        binding.MessageHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.MessageHistoryRecyclerView.setAdapter(new ChatGPTMessageHistoryAdapter(presenter.getMessagesHistory()));
    }


}