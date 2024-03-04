package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.R;
import com.example.project_artificial_life_androidclient.databinding.ChatWithChatgptBinding;

public class Chat_With_ChatGPT extends AppCompatActivity {

    public ChatWithChatgptBinding getBinding()
    {
        return binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithChatgptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    private @Nullable ChatWithChatgptBinding binding = null;

    private Chat_With_ChatGPT_Contract.Presenter presenter;

}