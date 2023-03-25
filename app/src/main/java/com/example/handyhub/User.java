package com.example.handyhub;

import java.io.Serializable;

public class User implements Serializable {
    private int image;
    private String Name;
    private String Email;

    public User(int image, String name, String email) {
        this.image = image;
        Name = name;
        Email = email;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}
