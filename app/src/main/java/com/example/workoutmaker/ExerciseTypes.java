package com.example.workoutmaker;

public class ExerciseTypes {
    // Initialize variables
    public String name;
    public String modifier;
    public Boolean deleteable;
    // Constructor
    public ExerciseTypes(String name, String modifier, Boolean deleteable) {
        this.name = name;
        this.modifier = modifier;
        this.deleteable = deleteable;
    }
    // Sets ExerciseTypes attributes
    public void setName(String id){
        this.name = name;
    }
    public void setModifier(String modifier){
        this.modifier = modifier;
    }
    // Gets ExerciseTypes attributes
    public String getName(){
        return name;
    }
    public String getModifier(){
        return modifier;
    }
    public Boolean getDeleteable(){
        return deleteable;
    }
}
