package com.example.project_artificial_life_androidclient.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import java.util.Objects;

import retrofit2.Response;

public class Chat_With_Kandinsky_Model implements Chat_With_Kandinsky_Contract.Model {

    public Chat_With_Kandinsky_Model(){
        chatMessagesList = new ArrayList<>();
    }


    @Override
    public List<Object> GetChatMessagesList() {
        return chatMessagesList;
    }

    @Override
    public void SendUserRequestOnGenerateImageToKandinsky(String userQuarry, Action SuccessfullGeneringCallback, Action FailedGeneringCallback) {

        Kandinsky_SendRequestToGenerate_Params userMessageParams = new Kandinsky_SendRequestToGenerate_Params(1024, 1024, userQuarry);
        chatMessagesList.add(userMessageParams);

        new Thread(new Runnable() {
            @Override
            public void run() {

                int modelID = GetKandinskyModels();
                if(modelID == -1)
                {
                    chatMessagesList.remove(chatMessagesList.size() - 1);
                    FailedGeneringCallback.DoAction();
                    return;
                }
                Kandinsky_SendRequestToGenerate_Data userMessageData = new Kandinsky_SendRequestToGenerate_Data(Integer.toString(modelID));
                String requestNumber = SendRequestToGenerateImage(userMessageParams, userMessageData);
                if(requestNumber == ""){
                    chatMessagesList.remove(chatMessagesList.size() - 1);
                    FailedGeneringCallback.DoAction();
                    return;
                }
                Kandinsky_GeneratedImage generatedImage = GetImageFromServer(requestNumber, 50);
                if (requestNumber == null){
                    chatMessagesList.remove(chatMessagesList.size() - 1);
                    FailedGeneringCallback.DoAction();
                    return;
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Log.println(Log.INFO, "Kandinsky API Connection", gson.toJson(generatedImage));
                chatMessagesList.add(generatedImage);
                SuccessfullGeneringCallback.DoAction();
            }
        }).start();
    }


    private int GetKandinskyModels(){

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

    private String SendRequestToGenerateImage(Kandinsky_SendRequestToGenerate_Params params, @NonNull Kandinsky_SendRequestToGenerate_Data data){

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

    private @Nullable Kandinsky_GeneratedImage GetImageFromServer(String requestNumber, int numItter){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for(int i = 0; i < numItter; i++){
            try
            {
                Response<Kandinsky_GeneratedImage> imageResponse = new KandinskyRetrofitConnection().Get_Kandinsky_API()
                        .CheckRequestToGenerateImage(requestNumber)
                        .execute();
                if (imageResponse.code() != 200){
                    continue;
                }
                if(Objects.equals(imageResponse.body().getStatus(), "FAIL")){
                    Log.println(Log.INFO, "Kandinsky API Connection", "Ошибка при формировании картинки");
                    return null;
                }
                if(Objects.equals(imageResponse.body().getStatus(), "DONE")){
                    Log.println(Log.INFO, "Kandinsky API Connection", "Картинка сформирована!");
                    return imageResponse.body();
                }
                try {
                    Log.println(Log.INFO, "Kandinsky API Connection", gson.toJson(imageResponse.body()));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            catch (IOException ex){
                Log.println(Log.WARN, "Kandinsky API Connection", ex.getMessage());
                return null;
            }
        }
        Log.println(Log.WARN, "Kandinsky API Connection", "Время ожидания получения картинки превышено");
        return null;
    }


    private List<Object> chatMessagesList;
}
