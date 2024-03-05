package com.example.project_artificial_life_androidclient.Presenters;

import androidx.annotation.NonNull;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;
import com.example.project_artificial_life_androidclient.Models.Chat_With_ChatGPT_Model;

import java.util.List;

public class Chat_With_ChatGPT_Presenter implements Chat_With_ChatGPT_Contract.Presenter{

    public Chat_With_ChatGPT_Presenter(Chat_With_ChatGPT_Contract.View view){
        this.view = view;
        model = new Chat_With_ChatGPT_Model();
    }

    @Override
    public List<ChatGPT_Message> getMessagesHistory() {
        return model.getMessagesHistory();
    }

    @Override
    public void SendUserMessageToChat(String userMessage) {
        model.sendMessageToServer(userMessage
                , new Action() {
                    @Override
                    public void DoAction() {
                        view.RenderOfAddingNewMessageToList();
                    }
                }
                , new Action() {
                    @Override
                    public void DoAction() {
                        view.InformUserAboutProblemsWithChatGPTConnection();
                    }
                });

    }


    @NonNull
    private Chat_With_ChatGPT_Contract.View view;

    @NonNull
    private Chat_With_ChatGPT_Contract.Model model;

}
