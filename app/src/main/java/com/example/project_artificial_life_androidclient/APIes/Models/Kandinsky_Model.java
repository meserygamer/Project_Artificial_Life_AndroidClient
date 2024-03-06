package com.example.project_artificial_life_androidclient.APIes.Models;

public class Kandinsky_Model {

    private int id;
    private String name;
    private float version;
    private String type;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setId( int id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setVersion( float version ) {
        this.version = version;
    }

    public void setType( String type ) {
        this.type = type;
    }

}
