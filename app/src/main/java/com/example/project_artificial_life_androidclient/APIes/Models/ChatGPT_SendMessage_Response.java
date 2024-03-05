package com.example.project_artificial_life_androidclient.APIes.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatGPT_SendMessage_Response {
    @SerializedName("id")
    String id;

    @SerializedName("object")
    String object;

    @SerializedName("created")
    int created;

    @SerializedName("model")
    String model;

    @SerializedName("system_fingerprint")
    String systemFingerprint;

    @SerializedName("choices")
    List<Choices> choices;

    @SerializedName("usage")
    Usage usage;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setObject(String object) {
        this.object = object;
    }
    public String getObject() {
        return object;
    }

    public void setCreated(int created) {
        this.created = created;
    }
    public int getCreated() {
        return created;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setSystemFingerprint(String systemFingerprint) {
        this.systemFingerprint = systemFingerprint;
    }
    public String getSystemFingerprint() {
        return systemFingerprint;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
    public List<Choices> getChoices() {
        return choices;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public Usage getUsage() {
        return usage;
    }
}
