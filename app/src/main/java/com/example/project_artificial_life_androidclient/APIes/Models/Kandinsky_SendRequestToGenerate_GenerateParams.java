package com.example.project_artificial_life_androidclient.APIes.Models;

public class Kandinsky_SendRequestToGenerate_GenerateParams {

    public Kandinsky_SendRequestToGenerate_GenerateParams(){}

    public Kandinsky_SendRequestToGenerate_GenerateParams(String query){
        this.query = query;
    }


    private String query;


    // Getter Methods

    public String getQuery() {
        return query;
    }

    // Setter Methods

    public void setQuery( String query ) {
        this.query = query;
    }

}
