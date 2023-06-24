package com.example.workoutmaker;

public class BooleanDays {
    
    // Initialize variables
    public String day;
    public Boolean selected;
    public String list;

    // Initialize constructor
    public BooleanDays(String day, Boolean selected, String list) {
        this.day = day;
        this.selected = selected;
        this.list= list;
    }

    public String getDay(){
        return day;
    } // Gets day attribute

    public Boolean getSelected(){
        return selected;
    } // Gets selected attribute

    public void setList(String list){
        this.list = list;
    } // Sets list attribute

    public String getList(){
        return list;
    } // Gets list attribute
}
