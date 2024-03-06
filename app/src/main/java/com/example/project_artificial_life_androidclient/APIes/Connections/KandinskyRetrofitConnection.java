package com.example.project_artificial_life_androidclient.APIes.Connections;

import com.example.project_artificial_life_androidclient.APIes.Interfaces.ChatGPT_API_Interface;
import com.example.project_artificial_life_androidclient.APIes.Interfaces.Kandinsky_API_Interface;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KandinskyRetrofitConnection {

    public static final String URL = "https://api-key.fusionbrain.ai/";


    public Kandinsky_API_Interface Get_Kandinsky_API()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create((new GsonBuilder()).setLenient().create()))
                    .build();
        }
        return retrofit.create(Kandinsky_API_Interface.class);
    }


    private Retrofit retrofit = null;

}
