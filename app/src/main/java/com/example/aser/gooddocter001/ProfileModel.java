package com.example.aser.gooddocter001;

public class ProfileModel {
    String name,gender,city,country,age;
    ProfileModel()
    {

    };

    public ProfileModel(String name, String gender, String city, String country, String age) {
        this.name = name;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.age = age;
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
}
