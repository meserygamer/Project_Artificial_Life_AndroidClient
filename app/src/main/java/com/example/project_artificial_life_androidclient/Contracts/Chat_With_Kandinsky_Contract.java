package com.example.project_artificial_life_androidclient.Contracts;

import java.util.List;

public interface Chat_With_Kandinsky_Contract {

    public interface Model{
        public List<Object> GetChatMessagesList();
    }

    public interface View{

    }

    public interface Presenter{

        public List<Object> GetChatMessagesList();
    }

}
