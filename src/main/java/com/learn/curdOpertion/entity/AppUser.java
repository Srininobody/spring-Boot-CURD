package com.learn.curdOpertion.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(name = "user_name",length = 50,nullable = false)
    private String name;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "Date_of_birth",nullable = false)
    private Date dob;

    @Column(name = "gender",length = 30,nullable = false)
    private String gender;

    @Column(name="country",length = 20)
    private String country;

    @Column(name = "profile_photo",length = 100)
    private String profilePic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
