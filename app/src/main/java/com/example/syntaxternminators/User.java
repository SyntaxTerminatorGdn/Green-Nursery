package com.example.syntaxternminators;

public class User {
    public String userId;
    public String fullName;
    public String address;
    public String age;
    public String gender;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userId, String fullName, String address, String age, String gender) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }
}
