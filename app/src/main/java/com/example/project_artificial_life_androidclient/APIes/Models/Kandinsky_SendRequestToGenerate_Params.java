package com.example.project_artificial_life_androidclient.APIes.Models;

public class Kandinsky_SendRequestToGenerate_Params {

    public Kandinsky_SendRequestToGenerate_Params(){

    }

    public Kandinsky_SendRequestToGenerate_Params(int width,
                                                  int height,
                                                  String query){

        this.height = height;
        this.width = width;
        this.generateParams = new Kandinsky_SendRequestToGenerate_GenerateParams(query);
    }


    private String type = "GENERATE";
    private int numImages = 1;
    private int width;
    private int height;
    Kandinsky_SendRequestToGenerate_GenerateParams generateParams;


    // Getter Methods

    public String getType() {
        return type;
    }

    public int getNumImages() {
        return numImages;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Kandinsky_SendRequestToGenerate_GenerateParams getGenerateParams() {
        return generateParams;
    }

    // Setter Methods

    public void setType( String type ) {
        this.type = type;
    }

    public void setNumImages( int numImages ) {
        this.numImages = numImages;
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public void setGenerateParams(Kandinsky_SendRequestToGenerate_GenerateParams generateParams) {
        this.generateParams = generateParams;
    }
}
