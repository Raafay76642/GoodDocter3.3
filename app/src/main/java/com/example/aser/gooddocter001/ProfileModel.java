package com.example.aser.gooddocter001;

public class ProfileModel {
    String name;
    String gender;
    String country;
    String age;
    String id;
    String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public ProfileModel(String name, String gender, String country, String age) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;

    }



    public ProfileModel(String name, String gender, String country, String age, String id,String email) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.id = id;
        this.email=email;

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
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
