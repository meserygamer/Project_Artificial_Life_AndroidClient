package com.example.project_artificial_life_androidclient.APIes.Models;

public class Kandinsky_SendRequestToGenerate_MyServer {

    public Kandinsky_SendRequestToGenerate_MyServer(){}

    public Kandinsky_SendRequestToGenerate_MyServer(Kandinsky_SendRequestToGenerate_Params messageParam, int modelID){

        this.messageParam = messageParam;
        this.modelID = modelID;
    }

    private Kandinsky_SendRequestToGenerate_Params messageParam;

    private int modelID;


    // Getter Methods

    public Kandinsky_SendRequestToGenerate_Params getMessageParam(){ return messageParam; }

    public int getModelID() {
        return modelID;
    }

    // Setter Methods

    public void setMessageParam( Kandinsky_SendRequestToGenerate_Params params) { this.messageParam = messageParam; }

    public void setModelID( int modelID ) {
        this.modelID = modelID;
    }

}
