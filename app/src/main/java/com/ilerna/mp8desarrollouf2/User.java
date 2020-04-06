package com.ilerna.mp8desarrollouf2;


import java.io.Serializable;

public class User implements Serializable {
    private int Id;
    private String Name;
    private String LastName;
    private String Email;
    private String UserName;
    private String Password;

    public User(int id, String name, String lastName, String email, String userName, String password) {
        Id = id;
        Name = name;
        LastName = lastName;
        Email = email;
        UserName = userName;
        Password = password;
    }

    public User(String name, String lastName, String email, String userName, String password) {
        Name = name;
        LastName = lastName;
        Email = email;
        UserName = userName;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
