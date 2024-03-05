package com.example.project_artificial_life_androidclient.Contracts;

import com.example.project_artificial_life_androidclient.Models.ChatGPT_Message;

import java.util.List;

public interface Chat_With_ChatGPT_Contract {

    public interface Model{
        public List<ChatGPT_Message> getMessagesHistory();
    }

    public interface View{

    }

    public interface Presenter{

        public List<ChatGPT_Message> getMessagesHistory();

    }

}
