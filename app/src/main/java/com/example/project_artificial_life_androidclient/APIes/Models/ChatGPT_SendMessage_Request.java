package com.example.project_artificial_life_androidclient.APIes.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatGPT_SendMessage_Request {
    @SerializedName("model")
    String model;

    @SerializedName("messages")
    List<ChatGPT_Message> messages;

    @SerializedName("temperature")
    double temperature;


    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setMessages(List<ChatGPT_Message> messages) {
        this.messages = messages;
    }
    public List<ChatGPT_Message> getMessages() {
        return messages;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public double getTemperature() {
        return temperature;
    }
}
