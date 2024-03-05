package com.example.project_artificial_life_androidclient.Contracts;

import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_Message;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Request;
import com.example.project_artificial_life_androidclient.APIes.Models.ChatGPT_SendMessage_Response;
import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;

import java.util.List;

public interface Chat_With_ChatGPT_Contract {

    public interface Model{
        public List<ChatGPT_Message> getMessagesHistory();

        public void sendMessageToServer(String message, Action successCallBack, Action failCallBack);
    }

    public interface View{

        public void RenderingOfAddingNewMessageToList();

        public void InformUserAboutProblemsWithChatGPTConnection();

    }

    public interface Presenter{

        public List<ChatGPT_Message> getMessagesHistory();

        public void SendUserMessageToChat(String userMessage);

    }

}
