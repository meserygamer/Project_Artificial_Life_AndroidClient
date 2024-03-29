package com.example.project_artificial_life_androidclient.APIes.Connections;

import com.example.project_artificial_life_androidclient.APIes.Interfaces.ChatGPT_API_Interface;
import com.example.project_artificial_life_androidclient.APIes.Interfaces.Kandinsky_API_Interface_My;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatGPTRetrofitConnection {

    public static final String URL = "http://185.36.143.136/";


    public ChatGPT_API_Interface Get_ChatGPT_API()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).setLenient().create()))
                    .build();
        }
        return retrofit.create(ChatGPT_API_Interface.class);
    }

    public Kandinsky_API_Interface_My Get_Kandinsky_MyProxy_API()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).setLenient().create()))
                    .build();
        }
        return retrofit.create(Kandinsky_API_Interface_My.class);
    }


    private Retrofit retrofit = null;

}
