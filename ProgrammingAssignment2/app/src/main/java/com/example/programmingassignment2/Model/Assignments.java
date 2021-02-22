package com.example.programmingassignment2.Model;

public class Assignments {

    private long key;
    private long id;
    private long gradePoints;

    public Assignments(long key, long id, long gradePoints) {
        this.key = key;
        this.id = id;
        this.gradePoints = gradePoints;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "key=" + key +
                ", id=" + id +
                ", gradePoints=" + gradePoints +
                '}';
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGradePoints() {
        return gradePoints;
    }

    public void setGradePoints(long gradePoints) {
        this.gradePoints = gradePoints;
    }
}