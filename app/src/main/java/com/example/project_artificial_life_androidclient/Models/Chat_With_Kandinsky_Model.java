package com.example.project_artificial_life_androidclient.Models;

import android.util.Log;

import com.example.project_artificial_life_androidclient.APIes.Connections.ChatGPTRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Connections.KandinskyRetrofitConnection;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_Model;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Data;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_MyServer;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;
import com.example.project_artificial_life_androidclient.Contracts.Chat_With_Kandinsky_Contract;
import com.example.project_artificial_life_androidclient.JavaFunctionalityExtensions.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_With_Kandinsky_Model implements Chat_With_Kandinsky_Contract.Model {

    public Chat_With_Kandinsky_Model(){
        ChatMessagesList = new ArrayList<>();
    }


    @Override
    public List<Object> GetChatMessagesList() {
        return ChatMessagesList;
    }

    @Override
    public void SendUserRequestOnGenerateImageToKandinsky(String userQuarry, Action SuccessfullGeneringCallback, Action FailedGeneringCallback) {

        Kandinsky_SendRequestToGenerate_Params userMessageParams = new Kandinsky_SendRequestToGenerate_Params(1024, 1024, userQuarry);

        new Thread(new Runnable() {
            @Override
            public void run() {

                int modelID = GetKandinskyModels();
                Kandinsky_SendRequestToGenerate_Data userMessageData = new Kandinsky_SendRequestToGenerate_Data(Integer.toString(modelID));
                SendRequestToGenerateImage(userMessageParams, userMessageData);
            }
        }).start();
    }


    public int GetKandinskyModels(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try
        {
            Response<List<Kandinsky_Model>> response = (new KandinskyRetrofitConnection()).Get_Kandinsky_API().GetModels().execute();
            Log.println(Log.DEBUG, "Kandinsky API Connection", gson.toJson(response.body()));
            if(response.code() != 200){
                return -1;
            }
            return response.body().get(0).getId();
        }
        catch (IOException ex){
            Log.println(Log.WARN, "Kandinsky API Connection", ex.getMessage());
            return -1;
        }
    }

    public String SendRequestToGenerateImage(Kandinsky_SendRequestToGenerate_Params params, Kandinsky_SendRequestToGenerate_Data data){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try
        {
            Response<Kandinsky_GeneratedImage> imageResponse = new ChatGPTRetrofitConnection().Get_Kandinsky_MyProxy_API()
                    .SendRequestToGenerateImage(new Kandinsky_SendRequestToGenerate_MyServer(params, Integer.valueOf(data.getModel_id())))
                    .execute();
            if (imageResponse.body() == null || imageResponse.code() != 200){
                return "";
            }
            Log.println(Log.INFO, "Kandinsky API Connection", imageResponse.body().getUuid());
            return imageResponse.body().getUuid();
        }
        catch (IOException ex){
            Log.println(Log.WARN, "Kandinsky API Connection", ex.getMessage());
            return "";
        }
    }


    private List<Object> ChatMessagesList;
}
