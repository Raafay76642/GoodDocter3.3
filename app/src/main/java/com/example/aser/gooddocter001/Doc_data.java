package com.example.aser.gooddocter001;

//Base class to hold information about our property
public class Doc_data {

    //property basics

    private String name;
    private String descrpition;
    private String image;
    private Double fee;
    private Boolean status;

    //constructor
    public Doc_data(
            String doctorName, String description, Double fee, String image, Boolean status){


        this.name = doctorName;
        this.status = status;
        this.descrpition = description;
        this.fee = fee;
        this.image = image;

    }

    //getters
    public String getName() {return name; }
    public String getDescription() {return descrpition; }
    public Double getFee() {return fee; }
    public String getImage() { return image; }
    public Boolean getStatus(){return status; }
}
