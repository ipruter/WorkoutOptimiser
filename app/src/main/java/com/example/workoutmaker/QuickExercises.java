package com.example.workoutmaker;

public class QuickExercises {
    public String name;
    public String reps;
    public String mod;
    public int valueRank;
    public int compoundRank;
    public String group;
    // Initialize constructor
    public QuickExercises(String name, String reps, String mod, int valueRank, int compoundRank, String group) {
        this.name = name;
        this.reps = reps;
        this.mod = mod;
        this.valueRank = valueRank;
        this.compoundRank = compoundRank;
        this.group = group;
    }
    // Sets Exercises attributes
    public void setName(String name){
        this.name = name;
    }
    public void setReps(String reps){
        this.reps = reps;
    }
    public void setMod(String mod) {
        this.mod = mod;
    }
    public void setValueRank(int valueRank){
        this.valueRank = valueRank;
    }
    public void setCompoundRank(int compoundRank){
        this.compoundRank = compoundRank;
    }
    public void setGroup(String group){
        this.group = group;
    }
    // Gets Exercises attributes
    public String getName(){
        return name;
    }
    public String getReps(){
        return reps;
    }
    public String getMod(){
        return mod;
    }
    public int getValueRank(){
        return valueRank;
    }
    public int getCompoundRank(){
        return compoundRank;
    }
    public String getGroup(){
        return group;
    }
}
