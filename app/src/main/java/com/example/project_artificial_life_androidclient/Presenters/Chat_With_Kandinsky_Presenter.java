package com.example.project_artificial_life_androidclient.Presenters;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_ChatGPT_Contract;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_Kandinsky_Contract;
import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;
import com.example.project_artificial_life_androidclient.Models.Chat_With_Kandinsky_Model;
import com.example.project_artificial_life_androidclient.Services.ImageSaver;

import java.util.List;

public class Chat_With_Kandinsky_Presenter implements Chat_With_Kandinsky_Contract.Presenter{

    public Chat_With_Kandinsky_Presenter(Chat_With_Kandinsky_Contract.View view, ImageSaver imageSaver){
        this.view = view;
        model = new Chat_With_Kandinsky_Model(imageSaver);
    }


    @Override
    public List<Object> GetChatMessagesList() {
        return model.GetChatMessagesList();
    }

    @Override
    public void UserSendMessage(String quarry) {
        model.SendUserRequestOnGenerateImageToKandinsky(quarry,
                new Action() {
                    @Override
                    public void DoAction() {
                        view.RenderOfAddingNewMessageToList();
                    }
                },
                new Action() {
                    @Override
                    public void DoAction() {
                        view.InformUserAboutProblemsWithKandinskyConnection();
                    }
                });
        view.RenderNewMessageInChat();
    }


    private Chat_With_Kandinsky_Contract.View view;

    private Chat_With_Kandinsky_Contract.Model model;
}
