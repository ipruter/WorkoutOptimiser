package com.example.workoutmaker;

public class Routine {
    // Initialize variables
    public String day;
    public int exercises;
    public String list;
    // Constructor
    public Routine(String day, int exercises, String list) {
        this.day = day;
        this.exercises = exercises;
        this.list = list;
    }
    // Sets Routine attributes
    public void setExercises(int exercises){
        this.exercises = exercises;
    }
    // Gets Routine attributes
    public String getDay(){
        return day;
    }
    public int getExercises(){
        return exercises;
    }
    public String getList(){
        return list;
    }
}
