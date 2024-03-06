package com.example.project_artificial_life_androidclient.APIes.Models;

import java.util.List;

public class Kandinsky_GeneratedImage {

    private String uuid;
    private String status;
    private List<String> images = null;
    private String errorDescription = null;
    private boolean censored = false;


    // Getter Methods

    public String getUuid() {
        return uuid;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public boolean getCensored() {
        return censored;
    }

    public List<String> getImages(){
        return images;
    }

    // Setter Methods

    public void setUuid( String uuid ) {
        this.uuid = uuid;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setErrorDescription( String errorDescription ) {
        this.errorDescription = errorDescription;
    }

    public void setCensored( boolean censored ) {
        this.censored = censored;
    }

    public void setImages( List<String> images ){
        this.images = images;
    }

}
