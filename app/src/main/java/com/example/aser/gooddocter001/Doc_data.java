package com.example.aser.gooddocter001;

//Base class to hold information about our property
public class Doc_data {

    //property basics

    private String DocterName;
    private String Discrpition;
    private String image;
    private Double Fee;
    private Boolean Status;

    //constructor
    public Doc_data(
            String doctorName, String description, Double fee, String image, Boolean status){


        this.DocterName = doctorName;
        this.Status = status;
        this.Discrpition = description;
        this.Fee = fee;
        this.image = image;

    }

    //getters
    public String getDocterName() {return DocterName; }
    public String getDescription() {return Discrpition; }
    public Double getFee() {return Fee; }
    public String getImage() { return image; }
    public Boolean getStatus(){return Status; }
}
