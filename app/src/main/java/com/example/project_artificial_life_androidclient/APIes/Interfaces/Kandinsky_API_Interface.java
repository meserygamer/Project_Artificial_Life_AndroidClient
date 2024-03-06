package com.example.project_artificial_life_androidclient.APIes.Interfaces;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_Model;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Data;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Kandinsky_API_Interface {

    @Headers({
            "X-Key : Key DF00F62BD21C4894FD5B2A66EED314A5",
            "X-Secret : Secret A2F7FBA41BC4405670A925CC84D54373"
    })
    @GET("key/api/v1/models")
    public Call<List<Kandinsky_Model>> GetModels();

    @Multipart
    @Headers({
            "X-Key : Key DF00F62BD21C4894FD5B2A66EED314A5",
            "X-Secret : Secret A2F7FBA41BC4405670A925CC84D54373"
    })
    @POST("key/api/v1/text2image/run")
    public Call<Kandinsky_GeneratedImage> SendRequestToGenerateImage(@Part("params")Kandinsky_SendRequestToGenerate_Params params,
                                                                     @Part("data") Kandinsky_SendRequestToGenerate_Data data);

    @Headers({
            "X-Key : Key DF00F62BD21C4894FD5B2A66EED314A5",
            "X-Secret : Secret A2F7FBA41BC4405670A925CC84D54373"
    })
    @GET("key/api/v1/text2image/status/{image_id}")
    public Call<Kandinsky_GeneratedImage> CheckRequestToGenerateImage(@Path("image_id") String uuid);
}
