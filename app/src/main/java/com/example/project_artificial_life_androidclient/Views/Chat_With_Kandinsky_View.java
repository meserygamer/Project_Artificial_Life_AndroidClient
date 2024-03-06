package com.example.project_artificial_life_androidclient.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_Kandinsky_Contract;
import com.example.project_artificial_life_androidclient.R;
import com.example.project_artificial_life_androidclient.databinding.ChatWithKandinskyBinding;

public class Chat_With_Kandinsky_View extends AppCompatActivity implements Chat_With_Kandinsky_Contract.View {

    public ChatWithKandinskyBinding getBinding() {
        return binding;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithKandinskyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    private ChatWithKandinskyBinding binding;
}