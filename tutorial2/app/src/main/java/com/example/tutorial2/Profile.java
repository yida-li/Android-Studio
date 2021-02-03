package com.example.tutorial2;

public class Profile {

private int ID;
private int age;
private String name;

    public int getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile(int ID, int age, String name) {
        this.ID = ID;
        this.age = age;
        this.name = name;
    }
}
