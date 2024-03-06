package com.example.project_artificial_life_androidclient.APIes.Interfaces;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_MyServer;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Kandinsky_API_Interface_My {

    @POST("Kandinsky/SendRequestToServer")
    public Call<Kandinsky_GeneratedImage> SendRequestToGenerateImage(@Body Kandinsky_SendRequestToGenerate_MyServer params);
}
