package com.example.project_artificial_life_androidclient.Contracts;

import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;

import java.util.List;

public interface Chat_With_Kandinsky_Contract {

    public interface Model{
        public List<Object> GetChatMessagesList();

        public void SendUserRequestOnGenerateImageToKandinsky(String userQuarry,
                                                              Action SuccessfullGeneringCallback,
                                                              Action FailedGeneringCallback);
    }

    public interface View{

    }

    public interface Presenter{

        public List<Object> GetChatMessagesList();

        public void UserSendMessage(String quarry);
    }

}
