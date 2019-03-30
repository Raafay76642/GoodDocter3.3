package com.example.aser.gooddocter001;

public class ProfileModel {
    String name,gender,city,country,age,id;
    ProfileModel()
    {

    };

    public ProfileModel(String name, String gender, String city, String country, String age,String id) {
        this.name = name;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getAge() {
        return age;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public void setId(String id) {
        this.id = id;
    }
}
