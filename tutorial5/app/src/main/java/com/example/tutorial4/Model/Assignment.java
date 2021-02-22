package com.example.tutorial4.Model;

import android.text.Editable;

public class Assignment {


String id;
String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Assignment(String id, String grade) {
        this.id = id;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id='" + id + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
