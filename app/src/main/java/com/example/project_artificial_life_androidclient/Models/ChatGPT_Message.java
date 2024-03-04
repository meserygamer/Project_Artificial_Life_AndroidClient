package com.example.project_artificial_life_androidclient.Models;

public class ChatGPT_Message {

    public String getRole()
    {
        return role;
    }
    public String getContent()
    {
        return content;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
    public void setContent(String content)
    {
        this.content = content;
    }


    private String role;
    private String content;

}
