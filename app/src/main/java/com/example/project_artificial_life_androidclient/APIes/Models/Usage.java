package com.example.project_artificial_life_androidclient.APIes.Models;

import com.google.gson.annotations.SerializedName;

public class Usage {

    @SerializedName("prompt_tokens")
    int promptTokens;

    @SerializedName("completion_tokens")
    int completionTokens;

    @SerializedName("total_tokens")
    int totalTokens;


    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }
    public int getPromptTokens() {
        return promptTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }
    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
    public int getTotalTokens() {
        return totalTokens;
    }

}
