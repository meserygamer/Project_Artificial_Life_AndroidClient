package com.example.project_artificial_life_androidclient.APIes.Models;

import com.google.gson.annotations.SerializedName;

public class Choices {

    @SerializedName("index")
    int index;

    @SerializedName("message")
    ChatGPT_Message message;

    @SerializedName("logprobs")
    String logprobs;

    @SerializedName("finish_reason")
    String finishReason;


    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    public void setMessage(ChatGPT_Message message) {
        this.message = message;
    }
    public ChatGPT_Message getMessage() {
        return message;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }
    public String getLogprobs() {
        return logprobs;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
    public String getFinishReason() {
        return finishReason;
    }
}
