package com.example.project_artificial_life_androidclient.APIes.Interfaces;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Kandinsky_API_Interface {

    @GET("key/api/v1/models")
    public Call<List<Kandinsky_Model>> GetModels();

}
