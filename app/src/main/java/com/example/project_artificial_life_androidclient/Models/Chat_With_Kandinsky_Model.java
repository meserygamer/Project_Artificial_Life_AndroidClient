package com.example.project_artificial_life_androidclient.Models;

import com.example.project_artificial_life_androidclient.Contracts.Chat_With_Kandinsky_Contract;

import java.util.ArrayList;
import java.util.List;

public class Chat_With_Kandinsky_Model implements Chat_With_Kandinsky_Contract.Model {

    public Chat_With_Kandinsky_Model(){
        ChatMessagesList = new ArrayList<>();
    }


    @Override
    public List<Object> GetChatMessagesList() {
        return null;
    }


    private List<Object> ChatMessagesList;
}
