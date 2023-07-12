package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class QuickWorkoutActivity extends AppCompatActivity {
    // Initialise arrays
    ArrayList<Routine> daysArrayList = new ArrayList<Routine>();
    ArrayList<BooleanDays> booleanDaysArrayList = new ArrayList<BooleanDays>();
    ArrayList<QuickExercises> QuickExerciseList = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> dayOne = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> dayTwo = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> dayThree = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> dayFour = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> dayFive = new ArrayList<QuickExercises>();
    ArrayList<QuickExercises> daySix = new ArrayList<QuickExercises>();
    ArrayList<String> workouts = new ArrayList<>();
    String numberOfexercises[] = {"Max exercise per day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String maxExercises = "";

    String squat = "0.72";
    String hackSquat = "0.36";
    String full = "1";
    String half = "0.5";
    String none = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_workout);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Spinner spinner = (Spinner) findViewById(R.id.number_of_exercises);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, numberOfexercises);
        spinner.setAdapter(adapter); // Setting spinner adapter

        // Initialize buttons and check boxes
        Button save = (Button) findViewById(R.id.save_quick_workout);
        EditText exercise_name = (EditText) findViewById(R.id.quickWorkoutName);
        CheckBox sunday = (CheckBox) findViewById(R.id.sun_check);
        CheckBox monday = (CheckBox) findViewById(R.id.mon_check);
        CheckBox tuesday= (CheckBox) findViewById(R.id.tue_check);
        CheckBox wednesday= (CheckBox) findViewById(R.id.wed_check);
        CheckBox thursday= (CheckBox) findViewById(R.id.thu_check);
        CheckBox friday = (CheckBox) findViewById(R.id.fri_check);
        CheckBox saturday = (CheckBox) findViewById(R.id.sat_check);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            // Listens for a selection of a spinner item
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!numberOfexercises[i].equals("Max exercise per day")){
                    maxExercises = numberOfexercises[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        QuickExerciseList.add(new QuickExercises("Squats", "10", squat, 1, 1, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Leg curls seated", none,"10", 2, 6, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Dips", "10", full,3, 6, "Chest"));
        QuickExerciseList.add(new QuickExercises("Cable rows", "10", none, 4, 3, "Back"));
        QuickExerciseList.add(new QuickExercises("Overhead press", "10", none, 5, 1, "Shoulders"));
        QuickExerciseList.add(new QuickExercises("Leg press", "10",  none, 6, 2, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Stiff legged dead lift", "10", squat, 7, 2, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Bench", "10", none, 8, 1, "Chest"));
        QuickExerciseList.add(new QuickExercises("Chin ups", "10", full,9, 5, "Back"));
        QuickExerciseList.add(new QuickExercises("Upright rows", "10", none, 10, 1, "Upright rows"));
        QuickExerciseList.add(new QuickExercises("Hack squat machine", "10", hackSquat, 11, 4, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Leg curls lying", "10", none, 12, 7, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Dumbbell presses", "10", none, 13, 3, "Chest"));
        QuickExerciseList.add(new QuickExercises("Rows", "10", none,14, 1, "Back"));
        QuickExerciseList.add(new QuickExercises("Upright rows cable rope", "10", none, 15, 2, "Upright rows"));
        QuickExerciseList.add(new QuickExercises("Leg extensions", "10", none, 16, 6, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Good mornings", "10", squat, 17, 3, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Bench incline", "10", none, 18, 2, "Chest"));
        QuickExerciseList.add(new QuickExercises("Dumbbell rows", "10", none, 19, 4, "Back"));
        QuickExerciseList.add(new QuickExercises("Barbell shrugs", "10", none, 20, 1, "Shrugs"));
        QuickExerciseList.add(new QuickExercises("Calf raises leg press", none, "10", 21, 1, "Calves"));
        QuickExerciseList.add(new QuickExercises("Standing calf raises", "10", half, 22, 2, "Calves"));
        QuickExerciseList.add(new QuickExercises("Dumbbell presses incline", "10", none, 23, 4, "Chest"));
        QuickExerciseList.add(new QuickExercises("Pull ups", "10", full, 24, 6, "Back"));
        QuickExerciseList.add(new QuickExercises("Dumbbell shrugs", "10", none, 25, 2, "Shrugs"));
        QuickExerciseList.add(new QuickExercises("Leg extensions", "8", none, 26, 5, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Leg curls seated", "8", none, 27, 4, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Cable bar triceps extensions", "10", none, 28, 2, "Triceps"));
        QuickExerciseList.add(new QuickExercises("Easy bar curls", "10", none, 29, 1, "Biceps"));
        QuickExerciseList.add(new QuickExercises("Lateral raises", "10", none, 30, 1, "Lateral raises"));
        QuickExerciseList.add(new QuickExercises("Seated calf raises", "10", none, 31, 3, "Calves"));
        QuickExerciseList.add(new QuickExercises("Crunches", "10", half,32, 1, "Abs"));
        QuickExerciseList.add(new QuickExercises("Cable rope triceps extensions", "10", none, 33, 3, "Triceps"));
        QuickExerciseList.add(new QuickExercises("Dumbbell curls", "10", none, 34, 2, "Biceps"));
        QuickExerciseList.add(new QuickExercises("Face palms", "10", none, 35, 8, "Back"));
        QuickExerciseList.add(new QuickExercises("Twisting crunches", "10", half, 36, 2, "Abs"));
        QuickExerciseList.add(new QuickExercises("Front squats", "10", squat, 37, 3, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Dead lift", "10", squat, 38, 1, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Dumbbell flies", "10", none, 39, 5, "Chest"));
        QuickExerciseList.add(new QuickExercises("T-bar rows", "10", none, 40, 2, "Back"));
        QuickExerciseList.add(new QuickExercises("Leg extensions", "12", none, 41, 7, "Lower push"));
        QuickExerciseList.add(new QuickExercises("Calf raises leg press", none, "12", 42, 4, "Calves"));
        QuickExerciseList.add(new QuickExercises("Bench close grip", "10", none, 43, 7, "Chest"));
        QuickExerciseList.add(new QuickExercises("Pull downs", "10", none, 44, 7, "Back"));
        QuickExerciseList.add(new QuickExercises("Leg curls lying", "8", none, 45, 5, "Lower pull"));
        QuickExerciseList.add(new QuickExercises("Crunches", "12", half, 46, 3, "Abs"));
        QuickExerciseList.add(new QuickExercises("Skull crushers", "10", none, 47, 1, "Triceps"));
        QuickExerciseList.add(new QuickExercises("Hammer curls", "10", none, 48, 3, "Biceps"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(exercise_name.getText());
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                HashSet<String> set = (HashSet<String>) pref.getStringSet("workouts", null); // Loads hash set
                if(set != null){workouts = new ArrayList(set);}
                
                // Tracks if day was checked or not
                if (sunday.isChecked()) {
                    booleanDaysArrayList.add(new BooleanDays("sunday", true, "listSun"));

                } else {
                    booleanDaysArrayList.add(new BooleanDays("sunday", false, "listSun"));
                }

                if (monday.isChecked()) {
                    booleanDaysArrayList.add(new BooleanDays("monday", true, "listMon"));
                } else {
                    booleanDaysArrayList.add(new BooleanDays("monday", false, "listMon"));
                }

                if(tuesday.isChecked()){
                    booleanDaysArrayList.add(new BooleanDays("tuesday", true, "listTue"));
                }
                else{
                    booleanDaysArrayList.add(new BooleanDays("tuesday", false, "listTue"));
                }

                if(wednesday.isChecked()){
                    booleanDaysArrayList.add(new BooleanDays("wednesday", true, "listWed"));
                }
                else{
                    booleanDaysArrayList.add(new BooleanDays("wednesday", false, "listWed"));
                }

                if(thursday.isChecked()){
                    booleanDaysArrayList.add(new BooleanDays("thursday", true, "listThu"));
                }
                else{
                    booleanDaysArrayList.add(new BooleanDays("thursday", false, "listThu"));
                }

                if(friday.isChecked()){
                    booleanDaysArrayList.add(new BooleanDays("friday", true, "listFri"));
                }
                else{
                    booleanDaysArrayList.add(new BooleanDays("friday", false, "listFri"));
                }

                if(saturday.isChecked()){
                    booleanDaysArrayList.add(new BooleanDays("saturday", true, "listSat"));
                }
                else{
                    booleanDaysArrayList.add(new BooleanDays("saturday", false, "listSat"));
                }


                int count = 0;


                // Adds all days that were checked
                for(int i = 0; i < 7; i++){
                    if(booleanDaysArrayList.get(i).getSelected() == true){
                        daysArrayList.add(new Routine(booleanDaysArrayList.get(i).getDay(), count, booleanDaysArrayList.get(i).getList()));
                        count = count + 1; // Counts days checked
                    }
                }

                int totalExercises = Integer.valueOf(maxExercises) * count;
                if(totalExercises > 48){
                    totalExercises = 48;
                }
                double doubleFiveDivisible = totalExercises - (Double.valueOf(totalExercises) % 5);
                int fiveDivisible = (int)doubleFiveDivisible;
                double doubleModFive = Double.valueOf(totalExercises) % 5;
                int modFive = (int)doubleModFive;

                // Creates exercises based on how many days were checked
                if(count == 1){
                    ArrayList<Exercises> exercises = new ArrayList<Exercises>();
                    if(totalExercises == 1){
                        dayOne.add(QuickExerciseList.get(4));
                        String max = pref.getString(dayOne.get(0).getName()+"_max_real", "0");
                        getWeight(dayOne.get(0).getMod(), max, dayOne.get(0).getReps(),  dayOne.get(0).getName(), "4", exercises);
                    }
                    else if(totalExercises == 2){
                        dayOne.add(QuickExerciseList.get(0));
                        dayOne.add(QuickExerciseList.get(1));
                        for(int i = 0; i < dayOne.size(); i++) {
                            String max = pref.getString(dayOne.get(i).getName() + "_max_real", "0");
                            getWeight(dayOne.get(i).getMod(), max, dayOne.get(i).getReps(), dayOne.get(i).getName(), "4", exercises);
                        }
                    }
                    else if(totalExercises == 3){
                        dayOne.add(QuickExerciseList.get(0));
                        dayOne.add(QuickExerciseList.get(1));
                        dayOne.add(QuickExerciseList.get(4));
                        for(int i = 0; i < dayOne.size(); i++) {
                            String max = pref.getString(dayOne.get(i).getName() + "_max_real", "0");
                            getWeight(dayOne.get(i).getMod(), max, dayOne.get(i).getReps(), dayOne.get(i).getName(), "4", exercises);
                        }
                    }
                    else if(totalExercises == 4){
                        dayOne.add(QuickExerciseList.get(0));
                        dayOne.add(QuickExerciseList.get(1));
                        dayOne.add(QuickExerciseList.get(2));
                        dayOne.add(QuickExerciseList.get(3));
                        for(int i = 0; i < dayOne.size(); i++) {
                            String max = pref.getString(dayOne.get(i).getName() + "_max_real", "0");
                            getWeight(dayOne.get(i).getMod(), max, dayOne.get(i).getReps(), dayOne.get(i).getName(), "4", exercises);
                        }
                    }
                    else {
                        for(int i = 0; i < fiveDivisible; i++) {
                                    dayOne.add(QuickExerciseList.get(i));
                        }
                       if(modFive == 1){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 4));
                        }
                       else if(modFive == 2){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            if(totalExercises == 32){
                                dayTwo.add(QuickExerciseList.get(fiveDivisible + 1));
                            }
                            else {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            }
                        }
                        else if(modFive == 3){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 4));
                        }
                        else if(modFive == 4) {
                           dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 2));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 3));
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();

                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Back")){
                                backArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayOne.get(j));
                            }
                        }

                        bubbleSort(lowerPushArray, lowerPushArray.size());
                        bubbleSort(lowerPullArray, lowerPullArray.size());
                        bubbleSort(chestArray, lowerPullArray.size());
                        bubbleSort(backArray, lowerPullArray.size());


                            for(int j = 0; j < lowerPushArray.size(); j++) {
                                String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", exercises);
                            }

                       for(int j = 0; j < lowerPullArray.size(); j++) {
                            String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                            getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", exercises);
                        }

                         for(int j = 0; j < chestArray.size(); j++) {
                            String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                            getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", exercises);
                        }

                        for(int j = 0; j < backArray.size(); j++) {
                            String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                            getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", exercises);
                        }
                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", exercises);
                        }
                        for(int j = 0; j < uprightRowArray.size(); j++) {
                            String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                            getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", exercises);
                        }
                    }

                    save(name, daysArrayList.get(0).getDay(), exercises, daysArrayList.get(0).getList());
                }
                else if(count == 2){
                    ArrayList<Exercises> dayOneExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayTwoExercises = new ArrayList<Exercises>();

                    if(totalExercises == 2){
                        dayOne.add(QuickExerciseList.get(0));
                        dayTwo.add(QuickExerciseList.get(1));

                        String max = pref.getString(dayOne.get(0).getName() + "_max_real", "0");
                        getWeight(dayOne.get(0).getMod(), max, dayOne.get(0).getReps(), dayOne.get(0).getName(), "4", dayOneExercises);

                        String max2 = pref.getString(dayTwo.get(0).getName() + "_max_real", "0");
                        getWeight(dayTwo.get(0).getMod(), max2, dayTwo.get(0).getReps(), dayTwo.get(0).getName(), "4", dayTwoExercises);
                    }

                    else if(totalExercises == 4){
                        dayOne.add(QuickExerciseList.get(0));
                        dayOne.add(QuickExerciseList.get(1));
                        dayTwo.add(QuickExerciseList.get(2));
                        dayTwo.add(QuickExerciseList.get(3));
                        for(int i = 0; i < dayOne.size(); i++) {
                            String max = pref.getString(dayOne.get(i).getName() + "_max_real", "0");
                            getWeight(dayOne.get(i).getMod(), max, dayOne.get(i).getReps(), dayOne.get(i).getName(), "4", dayOneExercises);
                        }
                        for(int i = 0; i < dayTwo.size(); i++) {
                            String max = pref.getString(dayTwo.get(i).getName() + "_max_real", "0");
                            getWeight(dayTwo.get(i).getMod(), max, dayTwo.get(i).getReps(), dayTwo.get(i).getName(), "4", dayTwoExercises);
                        }
                    }
                    else {
                        for(int i = 0; i < fiveDivisible; i++) {
                            if(QuickExerciseList.get(i).getGroup() == "Lower push" || QuickExerciseList.get(i).getGroup() == "Lower pull"){
                                dayOne.add(QuickExerciseList.get(i));
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Back" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Shrugs"){
                                dayTwo.add(QuickExerciseList.get(i));
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Upright rows"){
                                if(totalExercises == 10 || totalExercises == 14 || totalExercises == 20) {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                else{
                                    dayTwo.add(QuickExerciseList.get(i));
                                }
                            }
                        }

                        if(modFive == 1){
                            if(totalExercises == 6){
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 4));
                            }
                            else{
                                dayTwo.add(QuickExerciseList.get(fiveDivisible + 4));
                            }
                        }
                        else if(modFive == 2){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                        }
                        else if(modFive == 3){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            if(totalExercises == 18){
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 4));
                            }
                            else{
                                dayTwo.add(QuickExerciseList.get(fiveDivisible + 4));
                            }
                        }
                        else if(modFive == 4) {
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            dayTwo.add(QuickExerciseList.get(fiveDivisible + 2));
                            dayTwo.add(QuickExerciseList.get(fiveDivisible + 3));
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shrugArray = new ArrayList<QuickExercises>();

                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayOne.get(j));
                            }
                        }

                        for (int j = 0; j < dayTwo.size(); j++){
                            if(dayTwo.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Back")){
                                backArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayTwo.get(j));
                            }
                        }

                        if (lowerPushArray.size() > 1){
                            bubbleSort(lowerPushArray, lowerPushArray.size());
                            for(int j = 0; j < lowerPushArray.size(); j++) {
                                String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < lowerPushArray.size(); j++) {
                                String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                            }
                        }
                        if (lowerPullArray.size() > 1){
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int j = 0; j < lowerPullArray.size(); j++) {
                                String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < lowerPullArray.size(); j++) {
                                String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                            }
                        }
                        if (chestArray.size() > 1){
                            bubbleSort(chestArray, chestArray.size());
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        if (backArray.size() > 1){
                            bubbleSort(backArray, backArray.size());
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }

                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayTwoExercises);
                        }
                        if (uprightRowArray.size() > 1) {
                            bubbleSort(uprightRowArray, uprightRowArray.size());
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16 || totalExercises == 20) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 6 || totalExercises == 10 || totalExercises == 14) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        for(int j = 0; j < shrugArray.size(); j++) {
                            String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                            if(totalExercises == 18){
                                getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayOneExercises);
                            }
                            else{
                                getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                    }

                    save(name, daysArrayList.get(0).getDay(), dayOneExercises, daysArrayList.get(0).getList());
                    save(name, daysArrayList.get(1).getDay(), dayTwoExercises, daysArrayList.get(1).getList());
                }

               else if(count == 3){
                    ArrayList<Exercises> dayOneExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayTwoExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayThreeExercises = new ArrayList<Exercises>();

                    if(totalExercises == 3){
                        dayOne.add(QuickExerciseList.get(0));
                        dayTwo.add(QuickExerciseList.get(1));
                        dayThree.add(QuickExerciseList.get(4));

                        String max = pref.getString(dayOne.get(0).getName() + "_max_real", "0");
                        getWeight(dayOne.get(0).getMod(), max, dayOne.get(0).getReps(), dayOne.get(0).getName(), "4", dayOneExercises);

                        String max2 = pref.getString(dayTwo.get(0).getName() + "_max_real", "0");
                        getWeight(dayTwo.get(0).getMod(), max2, dayTwo.get(0).getReps(), dayTwo.get(0).getName(), "4", dayTwoExercises);

                        String max3 = pref.getString(dayThree.get(0).getName() + "_max_real", "0");
                        getWeight(dayThree.get(0).getMod(), max3, dayThree.get(0).getReps(), dayThree.get(0).getName(), "4", dayThreeExercises);
                    }
                    else {
                        for(int i = 0; i < fiveDivisible; i++) {
                            if(QuickExerciseList.get(i).getGroup() == "Lower push" || QuickExerciseList.get(i).getGroup() == "Lower pull"){
                                if(QuickExerciseList.get(i).getName() == "Leg curls seated"){
                                    dayThree.add(QuickExerciseList.get(i));
                                }
                                else{
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Chest"|| QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises"){
                                dayTwo.add(QuickExerciseList.get(i));
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Back"){
                                    dayThree.add(QuickExerciseList.get(i));
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Upright rows"){
                                if(totalExercises == 12){
                                    dayTwo.add(QuickExerciseList.get(i));
                                }
                                else{
                                    dayThree.add(QuickExerciseList.get(i));
                                }

                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Shrugs"){
                                if(totalExercises == 18 || totalExercises == 24){
                                    dayThree.add(QuickExerciseList.get(i));
                                }
                                else{
                                    dayTwo.add(QuickExerciseList.get(i));
                                }
                            }

                            else if(QuickExerciseList.get(i).getGroup() == "Calves"){
                                if(totalExercises == 24){
                                    dayTwo.add(QuickExerciseList.get(i));
                                }
                                else{
                                    dayThree.add(QuickExerciseList.get(i));
                                }
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Triceps"){
                                dayTwo.add(QuickExerciseList.get(i));
                            }
                            else if(QuickExerciseList.get(i).getGroup() == "Biceps"){
                                dayThree.add(QuickExerciseList.get(i));
                            }
                        }

                        if(modFive == 1){
                            dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                        }
                        else if(modFive == 2){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                        }
                        else if(modFive == 3){
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                        }
                        else if(modFive == 4) {
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                            dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                            dayTwo.add(QuickExerciseList.get(fiveDivisible + 2));
                            dayThree.add(QuickExerciseList.get(fiveDivisible + 3));
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shrugArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> calfArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> latRaiseArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> tricepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> bicepsArray = new ArrayList<QuickExercises>();


                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayOne.get(j));
                            }
                        }

                        for (int j = 0; j < dayTwo.size(); j++){
                            if(dayTwo.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayTwo.get(j));
                            }
                        }

                        for (int j = 0; j < dayThree.size(); j++){
                            if(dayThree.get(j).getGroup().equals("Back")){
                                backArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayThree.get(j));
                            }
                        }


                        if (chestArray.size() > 1){
                            bubbleSort(chestArray, chestArray.size());
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        if (backArray.size() > 1){
                            bubbleSort(backArray, backArray.size());
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                            }
                        }
                        else {
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                            }
                        }
                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayTwoExercises);
                        }
                        if (uprightRowArray.size() > 1) {
                            bubbleSort(uprightRowArray, uprightRowArray.size());
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        if(totalExercises == 21) {
                            bubbleSort(lowerPushArray, lowerPushArray.size());
                            for(int i = 0; i < 3; i++){
                                String max = pref.getString(lowerPushArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(i).getMod(), max, lowerPushArray.get(i).getReps(), lowerPushArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 3; i < 4; i++){
                                String max2 = pref.getString(lowerPushArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(i).getMod(), max2, lowerPushArray.get(i).getReps(), lowerPushArray.get(i).getName(), "4", dayThreeExercises);
                            }
                        }
                        else {
                            if (lowerPushArray.size() > 1) {
                                bubbleSort(lowerPushArray, lowerPushArray.size());
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            } else {
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                        }
                        if(totalExercises == 9) {
                            String max = pref.getString(lowerPullArray.get(0).getName() + "_max_real", "0");
                            getWeight(lowerPullArray.get(0).getMod(), max, lowerPullArray.get(0).getReps(), lowerPullArray.get(0).getName(), "4", dayOneExercises);

                            String max2 = pref.getString(lowerPullArray.get(1).getName() + "_max_real", "0");
                            getWeight(lowerPullArray.get(1).getMod(), max2, lowerPullArray.get(1).getReps(), lowerPullArray.get(1).getName(), "4", dayThreeExercises);
                        }
                        else if(totalExercises == 12) {
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 1; i < 3; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayThreeExercises);
                            }
                        }
                        else if(totalExercises == 15) {
                            for(int i = 0; i < 2; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 2; i < 3; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayTwoExercises);
                            }
                        }
                        else if(totalExercises == 18) {
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int i = 0; i < 2; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 2; i < 4; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayTwoExercises);
                            }
                        }
                        else{
                            if (lowerPullArray.size() > 1) {
                                bubbleSort(lowerPullArray, lowerPullArray.size());
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                }
                            } else {
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                        }
                        if (calfArray.size() > 1){
                            bubbleSort(calfArray, calfArray.size());
                            if(totalExercises == 24) {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                            else {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < calfArray.size(); j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        if (shrugArray.size() > 1) {
                            bubbleSort(shrugArray, shrugArray.size());
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18 || totalExercises == 24) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }

                        if (latRaiseArray.size() > 1) {
                            bubbleSort(latRaiseArray, latRaiseArray.size());
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if (tricepsArray.size() > 1) {
                            bubbleSort(tricepsArray, tricepsArray.size());
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if (bicepsArray.size() > 1) {
                            bubbleSort(bicepsArray, bicepsArray.size());
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                    }
                    save(name, daysArrayList.get(0).getDay(), dayOneExercises, daysArrayList.get(0).getList());
                    save(name, daysArrayList.get(1).getDay(), dayTwoExercises, daysArrayList.get(1).getList());
                    save(name, daysArrayList.get(2).getDay(), dayThreeExercises, daysArrayList.get(2).getList());

                }
                else if(count == 4){
                    ArrayList<Exercises> dayOneExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayTwoExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayThreeExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayFourExercises = new ArrayList<Exercises>();

                    if(totalExercises == 4){
                        dayOne.add(QuickExerciseList.get(0));
                        dayTwo.add(QuickExerciseList.get(1));
                        dayThree.add(QuickExerciseList.get(2));
                        dayFour.add(QuickExerciseList.get(3));

                        String max = pref.getString(dayOne.get(0).getName() + "_max_real", "0");
                        getWeight(dayOne.get(0).getMod(), max, dayOne.get(0).getReps(), dayOne.get(0).getName(), "4", dayOneExercises);

                        String max2 = pref.getString(dayTwo.get(0).getName() + "_max_real", "0");
                        getWeight(dayTwo.get(0).getMod(), max2, dayTwo.get(0).getReps(), dayTwo.get(0).getName(), "4", dayTwoExercises);

                        String max3 = pref.getString(dayThree.get(0).getName() + "_max_real", "0");
                        getWeight(dayThree.get(0).getMod(), max3, dayThree.get(0).getReps(), dayThree.get(0).getName(), "4", dayThreeExercises);

                        String max4 = pref.getString(dayFour.get(0).getName() + "_max_real", "0");
                        getWeight(dayFour.get(0).getMod(), max4, dayFour.get(0).getReps(), dayFour.get(0).getName(), "4", dayFourExercises);
                    }
                    else {
                        if (totalExercises > 35){
                            for (int i = 0; i < totalExercises; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 12 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 24 || totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                    else if (totalExercises == 36 || totalExercises == 40) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                    else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                    if (totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 36 || totalExercises == 40) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < fiveDivisible; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 12 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 24 || totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                    if (totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 36 || totalExercises == 40) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                }
                            }

                            if (modFive == 1) {
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                            } else if (modFive == 2) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                if (totalExercises == 32) {
                                    dayTwo.add(QuickExerciseList.get(fiveDivisible + 1));
                                } else {
                                    dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                }
                            } else if (modFive == 3) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                            } else if (modFive == 4) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                dayTwo.add(QuickExerciseList.get(fiveDivisible + 2));
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 3));
                            }
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shrugArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> calfArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> latRaiseArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> tricepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> bicepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> absArray = new ArrayList<QuickExercises>();


                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Abs")){
                                absArray.add(dayOne.get(j));
                            }
                        }

                        for (int j = 0; j < dayTwo.size(); j++){
                            if(dayTwo.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Abs")){
                                absArray.add(dayTwo.get(j));
                            }
                        }

                        for (int j = 0; j < dayThree.size(); j++){
                            if(dayThree.get(j).getGroup().equals("Back")){
                                backArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Abs")){
                                absArray.add(dayThree.get(j));
                            }
                        }
                        for (int j = 0; j < dayFour.size(); j++){
                            if(dayFour.get(j).getGroup().equals("Back")){
                                backArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Abs")){
                                absArray.add(dayFour.get(j));
                            }
                        }


                        if (chestArray.size() > 1){
                            bubbleSort(chestArray, chestArray.size());
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16 || totalExercises == 20) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if (backArray.size() > 1){
                            bubbleSort(backArray, backArray.size());
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16 || totalExercises == 20) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            if (totalExercises == 16 || totalExercises == 20) {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayTwoExercises);
                            }
                            else {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayThreeExercises);
                            }
                        }
                        if (uprightRowArray.size() > 1) {
                            bubbleSort(uprightRowArray, uprightRowArray.size());
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                }
                                else if (totalExercises == 24 || totalExercises == 28 || totalExercises == 36 || totalExercises == 40) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        if(totalExercises == 20) {
                            bubbleSort(lowerPushArray, lowerPushArray.size());
                            for(int i = 0; i < 3; i++){
                                String max = pref.getString(lowerPushArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(i).getMod(), max, lowerPushArray.get(i).getReps(), lowerPushArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 3; i < 4; i++){
                                String max2 = pref.getString(lowerPushArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPushArray.get(i).getMod(), max2, lowerPushArray.get(i).getReps(), lowerPushArray.get(i).getName(), "4", dayThreeExercises);
                            }
                        }
                        else {
                            if (lowerPushArray.size() > 1) {
                                bubbleSort(lowerPushArray, lowerPushArray.size());
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            } else {
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                        }


                        if(totalExercises == 16) {
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 1; i < 3; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayFourExercises);
                            }
                        }
                        else if(totalExercises == 20) {
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int i = 0; i < 2; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 2; i < 4; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayFourExercises);
                            }
                        }
                        else{
                            if (lowerPullArray.size() > 1) {
                                bubbleSort(lowerPullArray, lowerPullArray.size());
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 16 || totalExercises == 20) {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                    } else {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                        }
                        if (calfArray.size() > 1){
                            bubbleSort(calfArray, calfArray.size());
                            if(totalExercises == 24 || totalExercises == 28 || totalExercises == 32 || totalExercises == 36 || totalExercises == 40) {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                            else {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < calfArray.size(); j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        if (shrugArray.size() > 1) {
                            bubbleSort(shrugArray, shrugArray.size());
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayOneExercises);
                                }
                                else if (totalExercises == 32 || totalExercises == 36 || totalExercises == 40) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayThreeExercises);
                                }
                                else if (totalExercises == 32) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }

                        if (latRaiseArray.size() > 1) {
                            bubbleSort(latRaiseArray, latRaiseArray.size());
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 28 || totalExercises == 32  || totalExercises == 36  || totalExercises == 40) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if (tricepsArray.size() > 1) {
                            bubbleSort(tricepsArray, tricepsArray.size());
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        if (bicepsArray.size() > 1) {
                            bubbleSort(bicepsArray, bicepsArray.size());
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        if(totalExercises == 36 || totalExercises == 40) {
                            bubbleSort(absArray, absArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 1; i < 2; i++){
                                String max2 = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max2, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayFourExercises);
                            }
                        }
                        else {
                            if (absArray.size() > 1) {
                                bubbleSort(absArray, absArray.size());
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 18) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayOneExercises);
                                    } else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 18) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayOneExercises);
                                    } else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            }
                        }
                    }
                    save(name, daysArrayList.get(0).getDay(), dayOneExercises, daysArrayList.get(0).getList());
                    save(name, daysArrayList.get(1).getDay(), dayTwoExercises, daysArrayList.get(1).getList());
                    save(name, daysArrayList.get(2).getDay(), dayThreeExercises, daysArrayList.get(2).getList());
                    save(name, daysArrayList.get(3).getDay(), dayFourExercises, daysArrayList.get(3).getList());
                }
                else if (count == 5){
                    ArrayList<Exercises> dayOneExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayTwoExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayThreeExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayFourExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayFiveExercises = new ArrayList<Exercises>();

                    if(totalExercises == 5){
                        dayOne.add(QuickExerciseList.get(0));
                        dayTwo.add(QuickExerciseList.get(1));
                        dayThree.add(QuickExerciseList.get(2));
                        dayFour.add(QuickExerciseList.get(3));
                        dayFive.add(QuickExerciseList.get(4));

                        String max = pref.getString(dayOne.get(0).getName() + "_max_real", "0");
                        getWeight(dayOne.get(0).getMod(), max, dayOne.get(0).getReps(), dayOne.get(0).getName(), "4", dayOneExercises);

                        String max2 = pref.getString(dayTwo.get(0).getName() + "_max_real", "0");
                        getWeight(dayTwo.get(0).getMod(), max2, dayTwo.get(0).getReps(), dayTwo.get(0).getName(), "4", dayTwoExercises);

                        String max3 = pref.getString(dayThree.get(0).getName() + "_max_real", "0");
                        getWeight(dayThree.get(0).getMod(), max3, dayThree.get(0).getReps(), dayThree.get(0).getName(), "4", dayThreeExercises);

                        String max4 = pref.getString(dayFour.get(0).getName() + "_max_real", "0");
                        getWeight(dayFour.get(0).getMod(), max4, dayFour.get(0).getReps(), dayFour.get(0).getName(), "4", dayFourExercises);

                        String max5 = pref.getString(dayFour.get(0).getName() + "_max_real", "0");
                        getWeight(dayFive.get(0).getMod(), max5, dayFive.get(0).getReps(), dayFive.get(0).getName(), "4", dayFiveExercises);
                    }
                    else {
                        if (totalExercises > 35){
                            for (int i = 0; i < totalExercises; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 12 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 24 || totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                    else if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                    else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                    if (totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < fiveDivisible; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 12 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 24 || totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                    if (totalExercises == 24 || totalExercises == 28 || totalExercises == 32) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 36) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayOne.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 35) {
                                        dayFive.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }
                                }
                            }

                            if (modFive == 1) {
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                            } else if (modFive == 2) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                if (totalExercises == 32) {
                                    dayTwo.add(QuickExerciseList.get(fiveDivisible + 1));
                                } else {
                                    dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                }
                            } else if (modFive == 3) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 4));
                            } else if (modFive == 4) {
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 0));
                                dayOne.add(QuickExerciseList.get(fiveDivisible + 1));
                                dayTwo.add(QuickExerciseList.get(fiveDivisible + 2));
                                dayThree.add(QuickExerciseList.get(fiveDivisible + 3));
                            }
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shrugArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> calfArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> latRaiseArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> tricepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> bicepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> absArray = new ArrayList<QuickExercises>();


                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Abs")){
                                absArray.add(dayOne.get(j));
                            }
                        }

                        for (int j = 0; j < dayTwo.size(); j++){
                            if(dayTwo.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Abs")){
                                absArray.add(dayTwo.get(j));
                            }
                        }

                        for (int j = 0; j < dayThree.size(); j++){
                            if(dayThree.get(j).getGroup().equals("Back")){
                                backArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Abs")){
                                absArray.add(dayThree.get(j));
                            }
                        }
                        for (int j = 0; j < dayFour.size(); j++){
                            if(dayFour.get(j).getGroup().equals("Back")){
                                backArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Abs")){
                                absArray.add(dayFour.get(j));
                            }
                        }
                        for (int j = 0; j < dayFive.size(); j++){
                            if(dayFive.get(j).getGroup().equals("Back")){
                                backArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Abs")){
                                absArray.add(dayFive.get(j));
                            }
                        }


                        if (chestArray.size() > 1){
                            bubbleSort(chestArray, chestArray.size());
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        if (backArray.size() > 1){
                            bubbleSort(backArray, backArray.size());
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            if (totalExercises == 16) {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayTwoExercises);
                            }
                            else {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayFiveExercises);
                            }
                        }
                        if (uprightRowArray.size() > 1) {
                            bubbleSort(uprightRowArray, uprightRowArray.size());
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                }
                                else if (totalExercises == 24 || totalExercises == 28 || totalExercises == 36) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }


                            if (lowerPushArray.size() > 1) {
                                bubbleSort(lowerPushArray, lowerPushArray.size());
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            } else {
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }



                        if(totalExercises == 16) {
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayOneExercises);
                            }
                            for(int i = 1; i < 3; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayFourExercises);
                            }
                        }
                        else{
                            if (lowerPullArray.size() > 1) {
                                bubbleSort(lowerPullArray, lowerPullArray.size());
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 16) {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                    } else {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if(totalExercises == 25 || totalExercises == 30){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 1; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 1; j < 2; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else if(totalExercises == 35){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 2; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 2; j < 3; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else if(totalExercises == 45 || totalExercises == 48){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 2; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 2; j < 4; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else{
                            if (calfArray.size() > 1) {
                                bubbleSort(calfArray, calfArray.size());
                                if (totalExercises == 24 || totalExercises == 28 || totalExercises == 32 || totalExercises == 36) {
                                    for (int j = 0; j < calfArray.size(); j++) {
                                        String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                        getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                                    }
                                } else {
                                    for (int j = 0; j < calfArray.size(); j++) {
                                        String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                        getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }
                        }
                        if (shrugArray.size() > 1) {
                            bubbleSort(shrugArray, shrugArray.size());
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayOneExercises);
                                }
                                else if (totalExercises == 32 || totalExercises == 36) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayThreeExercises);
                                }
                                else if (totalExercises == 32) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }

                        if (latRaiseArray.size() > 1) {
                            bubbleSort(latRaiseArray, latRaiseArray.size());
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 28 || totalExercises == 32  || totalExercises == 36) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        if (tricepsArray.size() > 1) {
                            bubbleSort(tricepsArray, tricepsArray.size());
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        if (bicepsArray.size() > 1) {
                            bubbleSort(bicepsArray, bicepsArray.size());
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        if(totalExercises == 40) {
                            bubbleSort(absArray, absArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayTwoExercises);
                            }
                            for(int i = 1; i < 2; i++){
                                String max2 = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max2, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayFiveExercises);
                            }
                        }
                        else {
                            if (absArray.size() > 1) {
                                bubbleSort(absArray, absArray.size());
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 45 || totalExercises == 48) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayFiveExercises);
                                    }
                                    else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 35) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayFiveExercises);
                                    } else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            }
                            if(totalExercises == 35) {
                                String max2 = pref.getString(QuickExerciseList.get(35).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(35).getMod(), max2, QuickExerciseList.get(35).getReps(), QuickExerciseList.get(35).getName(), "4", dayTwoExercises);
                            }
                        }
                    }
                    save(name, daysArrayList.get(0).getDay(), dayOneExercises, daysArrayList.get(0).getList());
                    save(name, daysArrayList.get(1).getDay(), dayTwoExercises, daysArrayList.get(1).getList());
                    save(name, daysArrayList.get(2).getDay(), dayThreeExercises, daysArrayList.get(2).getList());
                    save(name, daysArrayList.get(3).getDay(), dayFourExercises, daysArrayList.get(3).getList());
                    save(name, daysArrayList.get(4).getDay(), dayFiveExercises, daysArrayList.get(4).getList());
                }

                else {
                    ArrayList<Exercises> dayOneExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayTwoExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayThreeExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayFourExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> dayFiveExercises = new ArrayList<Exercises>();
                    ArrayList<Exercises> daySixExercises = new ArrayList<Exercises>();


                        if (totalExercises > 35){
                            for (int i = 0; i < totalExercises; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                    else if (totalExercises == 35) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                    else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                    if (totalExercises == 28 || totalExercises == 32) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 35) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 35) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < fiveDivisible; i++) {
                                if (QuickExerciseList.get(i).getGroup() == "Lower push") {
                                    dayOne.add(QuickExerciseList.get(i));
                                }
                                if (QuickExerciseList.get(i).getGroup() == "Lower pull") {
                                    if (totalExercises == 16) {
                                        dayOne.add(QuickExerciseList.get(i));
                                    } else {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Chest" || QuickExerciseList.get(i).getGroup() == "Shoulders" || QuickExerciseList.get(i).getGroup() == "Lateral raises") {
                                    if (totalExercises == 16) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayThree.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Back") {
                                    if (totalExercises == 16) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFour.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Upright rows") {
                                    if (totalExercises == 8 || totalExercises == 32) {
                                        dayFour.add(QuickExerciseList.get(i));
                                    } else if (totalExercises == 28) {
                                        dayTwo.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }

                                } else if (QuickExerciseList.get(i).getGroup() == "Shrugs") {
                                    if (totalExercises == 16 || totalExercises == 28 || totalExercises == 32) {
                                        dayThree.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }
                                } else if (QuickExerciseList.get(i).getGroup() == "Calves") {
                                        dayOne.add(QuickExerciseList.get(i));
                                }
                                else if (QuickExerciseList.get(i).getGroup() == "Triceps") {
                                    dayThree.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Biceps") {
                                    dayFour.add(QuickExerciseList.get(i));
                                } else if (QuickExerciseList.get(i).getGroup() == "Abs") {
                                    if (totalExercises == 35) {
                                        dayFive.add(QuickExerciseList.get(i));
                                    } else {
                                        dayFive.add(QuickExerciseList.get(i));
                                    }
                                }
                            }

                            if (modFive == 1) {
                               // daySix.add(QuickExerciseList.get(fiveDivisible + 4));
                                String max = pref.getString(QuickExerciseList.get(fiveDivisible + 4).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 4).getMod(), max, QuickExerciseList.get(fiveDivisible + 4).getReps(), QuickExerciseList.get(fiveDivisible + 4).getName(), "4", daySixExercises);
                            } else if (modFive == 2) {
                               // daySix.add(QuickExerciseList.get(fiveDivisible + 0));
                                // daySix.add(QuickExerciseList.get(fiveDivisible + 1));
                                if(totalExercises == 12){
                                    String max = pref.getString(QuickExerciseList.get(fiveDivisible + 0).getName() + "_max_real", "0");
                                    getWeight(QuickExerciseList.get(fiveDivisible + 0).getMod(), max, QuickExerciseList.get(fiveDivisible + 0).getReps(), QuickExerciseList.get(fiveDivisible + 0).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    String max = pref.getString(QuickExerciseList.get(fiveDivisible + 0).getName() + "_max_real", "0");
                                    getWeight(QuickExerciseList.get(fiveDivisible + 0).getMod(), max, QuickExerciseList.get(fiveDivisible + 0).getReps(), QuickExerciseList.get(fiveDivisible + 0).getName(), "4", dayTwoExercises);
                                }

                                String max2 = pref.getString(QuickExerciseList.get(fiveDivisible + 1).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 1).getMod(), max2, QuickExerciseList.get(fiveDivisible + 1).getReps(), QuickExerciseList.get(fiveDivisible + 1).getName(), "4", daySixExercises);

                            } else if (modFive == 3) {
                              /*  daySix.add(QuickExerciseList.get(fiveDivisible + 0));
                                daySix.add(QuickExerciseList.get(fiveDivisible + 1));
                                daySix.add(QuickExerciseList.get(fiveDivisible + 4)); */
                                String max = pref.getString(QuickExerciseList.get(fiveDivisible + 0).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 0).getMod(), max, QuickExerciseList.get(fiveDivisible + 0).getReps(), QuickExerciseList.get(fiveDivisible + 0).getName(), "4", dayTwoExercises);
                                String max2 = pref.getString(QuickExerciseList.get(fiveDivisible + 1).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 1).getMod(), max2, QuickExerciseList.get(fiveDivisible + 1).getReps(), QuickExerciseList.get(fiveDivisible + 1).getName(), "4", dayTwoExercises);
                                String max3 = pref.getString(QuickExerciseList.get(fiveDivisible + 4).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 4).getMod(), max3, QuickExerciseList.get(fiveDivisible + 4).getReps(), QuickExerciseList.get(fiveDivisible + 4).getName(), "4", daySixExercises);
                            } else if (modFive == 4) {
                              /*  daySix.add(QuickExerciseList.get(fiveDivisible + 0));
                                daySix.add(QuickExerciseList.get(fiveDivisible + 1));
                                daySix.add(QuickExerciseList.get(fiveDivisible + 2));
                                daySix.add(QuickExerciseList.get(fiveDivisible + 3)); */
                                String max = pref.getString(QuickExerciseList.get(fiveDivisible + 0).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 0).getMod(), max, QuickExerciseList.get(fiveDivisible + 0).getReps(), QuickExerciseList.get(fiveDivisible + 0).getName(), "4", daySixExercises);

                                String max2 = pref.getString(QuickExerciseList.get(fiveDivisible + 1).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 1).getMod(), max2, QuickExerciseList.get(fiveDivisible + 1).getReps(), QuickExerciseList.get(fiveDivisible + 1).getName(), "4", daySixExercises);

                                String max3 = pref.getString(QuickExerciseList.get(fiveDivisible + 2).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 2).getMod(), max3, QuickExerciseList.get(fiveDivisible + 2).getReps(), QuickExerciseList.get(fiveDivisible + 2).getName(), "4", dayFiveExercises);

                                String max4 = pref.getString(QuickExerciseList.get(fiveDivisible + 3).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(fiveDivisible + 3).getMod(), max4, QuickExerciseList.get(fiveDivisible + 3).getReps(), QuickExerciseList.get(fiveDivisible + 3).getName(), "4", daySixExercises);
                            }
                        }

                        ArrayList<QuickExercises> lowerPushArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> lowerPullArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> chestArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> backArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shoulderArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> uprightRowArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> shrugArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> calfArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> latRaiseArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> tricepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> bicepsArray = new ArrayList<QuickExercises>();
                        ArrayList<QuickExercises> absArray = new ArrayList<QuickExercises>();


                        for (int j = 0; j < dayOne.size(); j++){
                            if(dayOne.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayOne.get(j));
                            }
                            else if(dayOne.get(j).getGroup().equals("Abs")){
                                absArray.add(dayOne.get(j));
                            }
                        }

                        for (int j = 0; j < dayTwo.size(); j++){
                            if(dayTwo.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower push")){
                                lowerPushArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayTwo.get(j));
                            }
                            else if(dayTwo.get(j).getGroup().equals("Abs")){
                                absArray.add(dayTwo.get(j));
                            }
                        }

                        for (int j = 0; j < dayThree.size(); j++){
                            if(dayThree.get(j).getGroup().equals("Back")){
                                backArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Chest")){
                                chestArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shoulders")){
                                shoulderArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lateral raises")){
                                latRaiseArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Triceps")){
                                tricepsArray.add(dayThree.get(j));
                            }
                            else if(dayThree.get(j).getGroup().equals("Abs")){
                                absArray.add(dayThree.get(j));
                            }
                        }
                        for (int j = 0; j < dayFour.size(); j++){
                            if(dayFour.get(j).getGroup().equals("Back")){
                                backArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayFour.get(j));
                            }
                            else if(dayFour.get(j).getGroup().equals("Abs")){
                                absArray.add(dayFour.get(j));
                            }
                        }
                        for (int j = 0; j < dayFive.size(); j++){
                            if(dayFive.get(j).getGroup().equals("Back")){
                                backArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Upright rows")){
                                uprightRowArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Shrugs")){
                                shrugArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Lower pull")){
                                lowerPullArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Calves")){
                                calfArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Biceps")){
                                bicepsArray.add(dayFive.get(j));
                            }
                            else if(dayFive.get(j).getGroup().equals("Abs")){
                                absArray.add(dayFive.get(j));
                            }
                        }

                    for (int j = 0; j < daySix.size(); j++){
                        if(daySix.get(j).getGroup().equals("Back")){
                            backArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Upright rows")){
                            uprightRowArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Shrugs")){
                            shrugArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Lower pull")){
                            lowerPullArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Calves")){
                            calfArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Biceps")){
                            bicepsArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Abs")){
                            absArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Lower push")){
                            lowerPushArray.add(daySix.get(j));
                        }
                        else if(daySix.get(j).getGroup().equals("Lower pull")){
                            lowerPullArray.add(daySix.get(j));
                        }
                    }


                        if (chestArray.size() > 1){
                            bubbleSort(chestArray, chestArray.size());
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayTwoExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < chestArray.size(); j++) {
                                String max = pref.getString(chestArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(chestArray.get(j).getMod(), max, chestArray.get(j).getReps(), chestArray.get(j).getName(), "4", dayThreeExercises);
                                }
                            }
                        }
                        if (backArray.size() > 1){
                            bubbleSort(backArray, backArray.size());
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayThreeExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        else {
                            for(int j = 0; j < backArray.size(); j++) {
                                String max = pref.getString(backArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8 || totalExercises == 12) {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(backArray.get(j).getMod(), max, backArray.get(j).getReps(), backArray.get(j).getName(), "4", dayFourExercises);
                                }
                            }
                        }
                        for(int j = 0; j < shoulderArray.size(); j++) {
                            String max = pref.getString(shoulderArray.get(j).getName() + "_max_real", "0");
                            if (totalExercises == 16) {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayTwoExercises);
                            }
                            else {
                                getWeight(shoulderArray.get(j).getMod(), max, shoulderArray.get(j).getReps(), shoulderArray.get(j).getName(), "4", dayFiveExercises);
                            }
                        }
                        if (uprightRowArray.size() > 1) {
                            bubbleSort(uprightRowArray, uprightRowArray.size());
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 25) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", daySixExercises);
                                }
                                else if (totalExercises == 28) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < uprightRowArray.size(); j++) {
                                String max = pref.getString(uprightRowArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 8) {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFourExercises);
                                } else {
                                    getWeight(uprightRowArray.get(j).getMod(), max, uprightRowArray.get(j).getReps(), uprightRowArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }


                            if (lowerPushArray.size() > 1) {
                                bubbleSort(lowerPushArray, lowerPushArray.size());
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            } else {
                                for (int j = 0; j < lowerPushArray.size(); j++) {
                                    String max = pref.getString(lowerPushArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPushArray.get(j).getMod(), max, lowerPushArray.get(j).getReps(), lowerPushArray.get(j).getName(), "4", dayOneExercises);
                                }
                            }



                        if(totalExercises == 12) {
                            bubbleSort(lowerPullArray, lowerPullArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayTwoExercises);
                            }
                            for(int i = 1; i < 2; i++){
                                String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                                getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", daySixExercises);
                            }
                        }
                    else if(totalExercises == 18) {
                        bubbleSort(lowerPullArray, lowerPullArray.size());
                        for(int i = 0; i < 1; i++){
                            String max = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                            getWeight(lowerPullArray.get(i).getMod(), max, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", dayTwoExercises);
                        }
                        for(int i = 1; i < 3; i++){
                            String max2 = pref.getString(lowerPullArray.get(i).getName() + "_max_real", "0");
                            getWeight(lowerPullArray.get(i).getMod(), max2, lowerPullArray.get(i).getReps(), lowerPullArray.get(i).getName(), "4", daySixExercises);
                        }
                    }
                        else{
                            if (lowerPullArray.size() > 1) {
                                bubbleSort(lowerPullArray, lowerPullArray.size());
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 16) {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayOneExercises);
                                    } else {
                                        getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayTwoExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < lowerPullArray.size(); j++) {
                                    String max = pref.getString(lowerPullArray.get(j).getName() + "_max_real", "0");
                                    getWeight(lowerPullArray.get(j).getMod(), max, lowerPullArray.get(j).getReps(), lowerPullArray.get(j).getName(), "4", dayTwoExercises);
                                }
                            }
                        }
                        if(totalExercises == 36){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 1; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 1; j < 3; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else if(totalExercises > 36){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 2; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 2; j < 4; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else if(totalExercises == 45 || totalExercises == 48){
                            bubbleSort(calfArray, calfArray.size());
                            for (int j = 0; j < 2; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                            }
                            for (int j = 2; j < 4; j++) {
                                String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayTwoExercises);
                            }
                        }
                        else{
                            if (calfArray.size() > 1) {
                                bubbleSort(calfArray, calfArray.size());
                                if (totalExercises == 28 || totalExercises == 32) {
                                    for (int j = 0; j < calfArray.size(); j++) {
                                        String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                        getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", dayOneExercises);
                                    }
                                } else {
                                    for (int j = 0; j < calfArray.size(); j++) {
                                        String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                        getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", daySixExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < calfArray.size(); j++) {
                                    String max = pref.getString(calfArray.get(j).getName() + "_max_real", "0");
                                    getWeight(calfArray.get(j).getMod(), max, calfArray.get(j).getReps(), calfArray.get(j).getName(), "4", daySixExercises);
                                }
                            }
                        }
                        if (shrugArray.size() > 1) {
                            bubbleSort(shrugArray, shrugArray.size());
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 16) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayOneExercises);
                                }
                                else if (totalExercises == 32) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < shrugArray.size(); j++) {
                                String max = pref.getString(shrugArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 24) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", daySixExercises);
                                }
                                else if (totalExercises == 32) {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayTwoExercises);
                                }
                                else {
                                    getWeight(shrugArray.get(j).getMod(), max, shrugArray.get(j).getReps(), shrugArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }

                        if (latRaiseArray.size() > 1) {
                            bubbleSort(latRaiseArray, latRaiseArray.size());
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 30) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", daySixExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < latRaiseArray.size(); j++) {
                                String max = pref.getString(latRaiseArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 30) {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", daySixExercises);
                                } else {
                                    getWeight(latRaiseArray.get(j).getMod(), max, latRaiseArray.get(j).getReps(), latRaiseArray.get(j).getName(), "4", dayFiveExercises);
                                }
                            }
                        }
                        if (tricepsArray.size() > 1) {
                            bubbleSort(tricepsArray, tricepsArray.size());
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", daySixExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < tricepsArray.size(); j++) {
                                String max = pref.getString(tricepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(tricepsArray.get(j).getMod(), max, tricepsArray.get(j).getReps(), tricepsArray.get(j).getName(), "4", daySixExercises);
                                }
                            }
                        }
                        if (bicepsArray.size() > 1) {
                            bubbleSort(bicepsArray, bicepsArray.size());
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", daySixExercises);
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < bicepsArray.size(); j++) {
                                String max = pref.getString(bicepsArray.get(j).getName() + "_max_real", "0");
                                if (totalExercises == 18) {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", dayOneExercises);
                                } else {
                                    getWeight(bicepsArray.get(j).getMod(), max, bicepsArray.get(j).getReps(), bicepsArray.get(j).getName(), "4", daySixExercises);
                                }
                            }
                        }
                        if(totalExercises == 40) {
                            bubbleSort(absArray, absArray.size());
                            for(int i = 0; i < 1; i++){
                                String max = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayTwoExercises);
                            }
                            for(int i = 1; i < 2; i++){
                                String max2 = pref.getString(absArray.get(i).getName() + "_max_real", "0");
                                getWeight(absArray.get(i).getMod(), max2, absArray.get(i).getReps(), absArray.get(i).getName(), "4", dayFiveExercises);
                            }
                        }
                        else {
                            if (absArray.size() > 1) {
                                bubbleSort(absArray, absArray.size());
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 45) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayFiveExercises);
                                    }
                                    else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", daySixExercises);
                                    }
                                }
                            } else {
                                for (int j = 0; j < absArray.size(); j++) {
                                    String max = pref.getString(absArray.get(j).getName() + "_max_real", "0");
                                    if (totalExercises == 35) {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", dayFiveExercises);
                                    } else {
                                        getWeight(absArray.get(j).getMod(), max, absArray.get(j).getReps(), absArray.get(j).getName(), "4", daySixExercises);
                                    }
                                }
                            }
                            if(totalExercises == 35) {
                                String max2 = pref.getString(QuickExerciseList.get(35).getName() + "_max_real", "0");
                                getWeight(QuickExerciseList.get(35).getMod(), max2, QuickExerciseList.get(35).getReps(), QuickExerciseList.get(35).getName(), "4", dayTwoExercises);
                            }

                    }
                    save(name, daysArrayList.get(0).getDay(), dayOneExercises, daysArrayList.get(0).getList());
                    save(name, daysArrayList.get(1).getDay(), dayTwoExercises, daysArrayList.get(1).getList());
                    save(name, daysArrayList.get(2).getDay(), dayThreeExercises, daysArrayList.get(2).getList());
                    save(name, daysArrayList.get(3).getDay(), dayFourExercises, daysArrayList.get(3).getList());
                    save(name, daysArrayList.get(4).getDay(), dayFiveExercises, daysArrayList.get(4).getList());
                    save(name, daysArrayList.get(5).getDay(), daySixExercises, daysArrayList.get(5).getList());

                }
                count = 0; // Resets count
                booleanDaysArrayList.clear(); // Resets Boolean array
                workouts.add(name); // Adds workout
                set = new HashSet(workouts); // Creates new set
                pref.edit().putStringSet("workouts", set).apply();
                // Goes back to workout page
                Intent intent = new Intent(QuickWorkoutActivity.this, WorkoutView.class);
                startActivity(intent);
            }
        });
    }
    
    // Saves data
    public void save(String name, String day, ArrayList<Exercises> exercises, String list) {
        SharedPreferences pref = getSharedPreferences(name + day, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercises);
        editor.putString(name + list, json);
        editor.apply();
    }

    // Adds exercises
    public void getWeight(String strModifier, String StrMaxReal, String reps, String name, String sets, ArrayList<Exercises> exercises){
        // Initialize objects
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Utilities util = new Utilities();
        String bodyWeight = pref.getString("bodyWeight", "0"); // Retrieve body weight
        String displayWeight = "";
        double modifier = util.modifyBodyWeight(strModifier, bodyWeight); // Finds bodyweight modifier
        double max = Double.valueOf(StrMaxReal);
        double dReps = 0;
        displayWeight = util.calculateWeight(reps, StrMaxReal, modifier); // Calculates weight
        double dDisplay_Weight = Double.valueOf(displayWeight);
        // Finds reps if body weight prevents user from workout out at a desired intensity
        if(dDisplay_Weight < 0){
                dReps = util.findReps(modifier, max); // Calculates reps
                dReps = Math.round(dReps);
                reps = String.valueOf(dReps);
                displayWeight = "0";
        }
        exercises.add(new Exercises(name, reps, sets, displayWeight)); // Adds exercise
    }
    static void bubbleSort(ArrayList<QuickExercises> arr, int n)
    {
        QuickExercises temp;
        int i, j;
        boolean swapped;
        for (i = 0; i < n; i++) {
            swapped = false;
            for (j = 0; j < n -1; j++) {
                if (arr.get(j).getCompoundRank() > arr.get(j + 1).getCompoundRank()) {

                    // Swap arr[j] and arr[j+1]
                    temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }
}