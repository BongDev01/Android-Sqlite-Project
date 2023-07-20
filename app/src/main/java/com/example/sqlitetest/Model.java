package com.example.sqlitetest;

public class Model {

    public String Name;
    public String Age;
    public String Address;

    public Model(String name, String age, String address) {
        Name = name;
        Age = age;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
