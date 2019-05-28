package com.example.aser.gooddocter001;

public class ProfileModel {
    String name;
    String gender;
    String country;
    String age;
    String id;
    String email;
    String profilePic;

    public ProfileModel() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    String role;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }


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
    public ProfileModel(String name, String gender, String country, String age, String profilePic,String email,String id) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.profilePic = profilePic;
        this.email=email;
        this.id=id;

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
