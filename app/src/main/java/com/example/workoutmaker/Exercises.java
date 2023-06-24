package com.example.workoutmaker;

public class Exercises {
    // Initialize variables
    public String name;
    public String reps;
    public String sets;
    public String weight;
    // Initialize constructor
    public Exercises(String exorcise, String reps, String sets, String weight) {
        this.name = exorcise;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }
    // Sets Exercises attributes
    public void setReps(String reps){
        this.reps = reps;
    }
    public void setSets(String sets){
        this.sets = sets;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    // Gets Exercises attributes
    public String getName(){
        return name;
    }
    public String getReps(){
        return reps;
    }
    public String getSets(){
        return sets;
    }
    public String getWeight(){
        return weight;
    }
}


