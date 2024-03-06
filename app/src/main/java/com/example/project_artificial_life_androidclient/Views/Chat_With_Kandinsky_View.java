package com.example.project_artificial_life_androidclient.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_Kandinsky_Contract;
import com.example.project_artificial_life_androidclient.Presenters.Chat_With_Kandinsky_Presenter;
import com.example.project_artificial_life_androidclient.R;
import com.example.project_artificial_life_androidclient.RecyclerViewAdapters.KandinskyMessageHistoryAdapter;
import com.example.project_artificial_life_androidclient.databinding.ChatWithKandinskyBinding;

import java.util.List;

public class Chat_With_Kandinsky_View extends AppCompatActivity implements Chat_With_Kandinsky_Contract.View {

    public ChatWithKandinskyBinding getBinding() {
        return binding;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChatWithKandinskyBinding.inflate(getLayoutInflater());
        presenter = new Chat_With_Kandinsky_Presenter(this);
        setContentView(binding.getRoot());
        SetAllListeners();
        SetMessageHistoryRecyclerView();
    }

    //Private Fields
    @Nullable
    private ChatWithKandinskyBinding binding = null;

    @Nullable
    private KandinskyMessageHistoryAdapter adapter;

    @NonNull
    private Chat_With_Kandinsky_Contract.Presenter presenter;

    //Private Methods
    private void SetAllListeners(){

        binding.kandinskySendMessageToChatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LockUserInputSystem();
                presenter.UserSendMessage(binding.kandinskyEnterMessageToChatField
                                                 .getText()
                                                 .toString());
            }
        });
    }

    private void SetMessageHistoryRecyclerView(){
        binding.KandinskyMessageHistoryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        );
        List<Object> messagesList = presenter.GetChatMessagesList();
        adapter = new KandinskyMessageHistoryAdapter(messagesList);
        binding.KandinskyMessageHistoryRecyclerView.setAdapter(adapter);
    }

    private void LockUserInputSystem(){
        binding.kandinskyEnterMessageToChatField.setEnabled(false);
        binding.kandinskySendMessageToChatButton.setEnabled(false);
    }

    private void UnlockUserInputSystem(){
        binding.kandinskyEnterMessageToChatField.setEnabled(true);
        binding.kandinskySendMessageToChatButton.setEnabled(true);
    }
}