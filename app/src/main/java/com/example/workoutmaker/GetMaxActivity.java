package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class GetMaxActivity extends AppCompatActivity {
    String strWeight;
    String strReps;
    String strMax;
    double max;
    double weight;
    double reps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_max);
        Utilities util = new Utilities();
        // Define views
        EditText inputWeight = (EditText)findViewById(R.id.weight);
        EditText inputReps = (EditText)findViewById(R.id.reps);
        TextView tvMax = (TextView)findViewById(R.id.display_max);
        TextView exerciseName = (TextView)findViewById(R.id.exercise_name);
        // Creates objects for saving data
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        String bodyWeight = pref.getString("bodyWeight", "0"); // Retrieve body weight from storage
        Intent intent = getIntent();
        String modifier = intent.getStringExtra("workoutModifier"); // Retrieve modifier from previous activity
        String workoutName = intent.getStringExtra("workoutName");  // Retrieve workout name from previous activity
        exerciseName.setText(workoutName); // Displays exercise name to user
        double modBodyWeight = util.modifyBodyWeight(modifier, bodyWeight); // Calculates modified body weight
        String strModBodyWeight = String.valueOf(modBodyWeight);
        
        // Listens for changes in edit text texts
        inputWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText input_reps = (EditText)findViewById(R.id.reps);
                // Listens for all forms to be filled before calculating
                if(!String.valueOf(input_reps.getText()).equals("") && !String.valueOf(inputWeight.getText()).equals("")) {
                    // Gets input data from edit text forms
                    strWeight = String.valueOf(inputWeight.getText());
                    strReps = String.valueOf(input_reps.getText());
                    weight = Double.parseDouble(strWeight);
                    reps = Double.parseDouble(strReps);
                    max = util.getMax(weight, reps, strModBodyWeight); // Calculates the users 1RM based on weight and reps
                    tvMax.setText(String.valueOf((max)));
                    strMax = String.valueOf(Math.round(max));
                    tvMax.setText(String.valueOf(max)); // Displays 1RM
                    double doubleBodyWeight = Double.valueOf(strModBodyWeight);
                    max = max + doubleBodyWeight;
                    String strMaxReal = String.valueOf(max); 
                    setMax(strMax);
                    updateLists(workoutName, modifier, strMaxReal); // Updates new values for exercises
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // add your code here
            }
        });

        inputReps.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // add your code here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText input_weight = (EditText)findViewById(R.id.weight);
                // Listens for all forms to be filled before calculating
                if(!String.valueOf(input_weight.getText()).equals("") && !String.valueOf(inputReps.getText()).equals("")) {
                    String body_weight = "0";
                    strWeight = String.valueOf(input_weight.getText());
                    strReps = String.valueOf(inputReps.getText());
                    weight = Double.parseDouble(strWeight);
                    reps = Double.parseDouble(strReps);
                    max = util.getMax(weight, reps, strModBodyWeight); // Calculates the users 1RM based on weight and reps
                    tvMax.setText(String.valueOf(Math.round(max)));
                    strMax = String.valueOf(Math.round(max));
                    double dBody_weight = Double.valueOf(strModBodyWeight);
                    max = max + dBody_weight;
                    String strMaxReal = String.valueOf(max);
                    if(strMax != null) {
                        setMax(strMax);
                    }
                    updateLists(workoutName, modifier, strMaxReal); // Updates new values for exercises
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        
        Button home = (Button) findViewById(R.id.home);
        // Activates method on yes button click
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = getIntent();
                intent = new Intent(GetMaxActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button quickWorkout = (Button) findViewById(R.id.back_quick_workout);
        // Activates method on yes button click
        quickWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = getIntent();
                intent = new Intent(GetMaxActivity.this, QuickWorkoutActivity.class);
                startActivity(intent);
            }
        });

        Button exercises = (Button) findViewById(R.id.exercises);
        // Activates method on yes button click
        exercises.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = getIntent();
                intent = new Intent(GetMaxActivity.this, MaxWeightActivity.class);
                startActivity(intent);
            }
        });
    }
    
    // Calculates and saves the max weight to storage
    public void setMax(String maxWeight){
        Intent intent = getIntent();
        Utilities util = new Utilities();
        // Accessing the data using key and value
        String workoutName = intent.getStringExtra("workoutName");
        String modifier = intent.getStringExtra("workoutModifier");
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = pref.edit();
        String bodyWeight = pref.getString("body_weight", "0");
        double modBodyWeight = util.modifyBodyWeight(modifier, bodyWeight); // Modify body weight
        editor.putString(workoutName + "_max", maxWeight);
        editor.commit(); // Save data
        double doubleMaxWeight = Double.valueOf(maxWeight);
        double realMaxWeight = modBodyWeight + doubleMaxWeight;
        String strRealMax = String.valueOf(realMaxWeight);
        editor.putString(workoutName + "_max_real", strRealMax); // Save real max
        editor.commit(); // Save data
        
    }

    // Updates all displayed weight after max is changed
    public void updateLists(String value, String modifier, String max) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String front_squat_real = max;
        // Initialize arrays
        ArrayList<String> workouts = new ArrayList<>();
        ArrayList<Exercises> sundayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> mondayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> tuesdayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> wednesdayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> thursdayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> fridayExercises = new ArrayList<Exercises>();
        ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();
        Utilities util = new Utilities();
        String body_weight = pref.getString("body_weight", "0"); // Retrieve body weight from storage
        HashSet<String> set = (HashSet<String>) pref.getStringSet("workouts", null); // Retrieve hash set from storage
        
        // Creates new array if hash set is empty
        if (set != null) {
            workouts = new ArrayList(set);
        }
        
        for (int i = 0; i < workouts.size(); i++) {
            String id = workouts.get(i);
            // Initialize shared preferences from each day of the week
            SharedPreferences prefSun = getSharedPreferences(id + "sunday", MODE_PRIVATE);
            SharedPreferences prefMon = getSharedPreferences(id + "monday", MODE_PRIVATE);
            SharedPreferences prefTue = getSharedPreferences(id + "tuesday", MODE_PRIVATE);
            SharedPreferences prefWed = getSharedPreferences(id + "wednesday", MODE_PRIVATE);
            SharedPreferences prefThu = getSharedPreferences(id + "thursday", MODE_PRIVATE);
            SharedPreferences prefFri = getSharedPreferences(id + "friday", MODE_PRIVATE);
            SharedPreferences prefSat = getSharedPreferences(id + "saturday", MODE_PRIVATE);
            Gson gsonSun = new Gson();
            Gson gsonMon = new Gson();
            Gson gsonTue = new Gson();
            Gson gsonWed = new Gson();
            Gson gsonThu = new Gson();
            Gson gsonFri = new Gson();
            Gson gsonSat = new Gson();
            // Retrieves json files from storage
            String jsonSun = prefSun.getString(id + "listSun", null);
            String jsonMon = prefMon.getString(id + "listMon", null);
            String jsonTue = prefTue.getString(id + "listTue", null);
            String jsonWed = prefWed.getString(id + "listWed", null);
            String jsonThu = prefThu.getString(id + "listThu", null);
            String jsonFri = prefFri.getString(id + "listFri", null);
            String jsonSat = prefSat.getString(id + "listSat", null);
            Type type = new TypeToken<ArrayList<Exercises>>() { // Initializes type token
            }.getType();
            // Creates new arrays if json files are null
            if (jsonSun == null) {
                sundayExercises = new ArrayList<Exercises>();
            } else {
                sundayExercises = gsonSun.fromJson(jsonSun, type);
            }
            if (jsonMon == null) {
                mondayExercises = new ArrayList<Exercises>();
            } else {
                mondayExercises = gsonMon.fromJson(jsonMon, type);
            }
            if (jsonTue == null) {
                tuesdayExercises = new ArrayList<Exercises>();
            } else {
                tuesdayExercises = gsonTue.fromJson(jsonTue, type);
            }
            if (jsonWed == null) {
                wednesdayExercises = new ArrayList<Exercises>();
            } else {
                wednesdayExercises = gsonWed.fromJson(jsonWed, type);
            }
            if (jsonThu == null) {
                thursdayExercises = new ArrayList<Exercises>();
            } else {
                thursdayExercises = gsonThu.fromJson(jsonThu, type);
            }
            if (jsonFri == null) {
                fridayExercises = new ArrayList<Exercises>();
            } else {
                fridayExercises = gsonFri.fromJson(jsonFri, type);
            }
            if (jsonSat == null) {
                saturdayExercises = new ArrayList<Exercises>();
            } else {
                saturdayExercises = gsonSat.fromJson(jsonSat, type);
            }
            
            for (int j = 0; j < sundayExercises.size(); j++) {
                if (sundayExercises.get(j).getName() != null) {
                    // Finds daily exercise by comparing name to exercise type name and retrieving reps value
                    if (sundayExercises.get(j).getName().equals(value)) {
                        String reps = sundayExercises.get(j).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight); // Get modifier
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier); // Calculate weight
                        sundayExercises.get(j).setWeight(weight); // Set weight
                    }
                }
            }
            
            for (int a = 0; a < mondayExercises.size(); a++) {
                if (mondayExercises.get(a).getName() != null) {
                    if (mondayExercises.get(a).getName().equals(value)) {

                        String reps = mondayExercises.get(a).getReps();

                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        mondayExercises.get(a).setWeight(weight);
                    }

                }

            }

            for (int b = 0; b < tuesdayExercises.size(); b++) {
                if (tuesdayExercises.get(b).getName() != null) {
                    if (tuesdayExercises.get(b).getName().equals(value)) {
                        String reps = tuesdayExercises.get(b).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        tuesdayExercises.get(b).setWeight(weight);
                    }
                }
            }

            for (int j = 0; j < wednesdayExercises.size(); j++) {
                if (wednesdayExercises.get(j).getName() != null) {
                    if (wednesdayExercises.get(j).getName().equals(value)) {
                        String reps = wednesdayExercises.get(j).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        wednesdayExercises.get(j).setWeight(weight);
                    }
                }
            }

            for (int j = 0; j < thursdayExercises.size(); j++) {
                if (thursdayExercises.get(j).getName() != null) {
                    if (thursdayExercises.get(j).getName().equals(value)) {
                        String reps = thursdayExercises.get(j).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        thursdayExercises.get(j).setWeight(weight);
                    }
                }
            }

            for (int j = 0; j < fridayExercises.size(); j++) {
                if (fridayExercises.get(j).getName() != null) {
                    if (fridayExercises.get(j).getName().equals(value)) {
                        String reps = fridayExercises.get(j).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        fridayExercises.get(j).setWeight(weight);
                    }
                }
            }

            for (int j = 0; j < saturdayExercises.size(); j++) {
                if (saturdayExercises.get(j).getName() != null) {
                    if (saturdayExercises.get(j).getName().equals(value)) {
                        String reps = saturdayExercises.get(j).getReps();
                        double dModifier = util.modifyBodyWeight(modifier, body_weight);
                        String weight = util.calculateWeight(reps, front_squat_real, dModifier);
                        saturdayExercises.get(j).setWeight(weight);
                    }
                }
            }

            SharedPreferences.Editor editorSun = prefSun.edit();
            SharedPreferences.Editor editorTue = prefTue.edit();
            SharedPreferences.Editor editorWed = prefWed.edit();
            SharedPreferences.Editor editorThu = prefThu.edit();
            SharedPreferences.Editor editorFri = prefFri.edit();
            SharedPreferences.Editor editorMon = prefMon.edit();
            SharedPreferences.Editor editorSat = prefSat.edit();
            // Converts arrays to json files
            jsonSun = gsonSun.toJson(sundayExercises);
            jsonMon = gsonMon.toJson(mondayExercises);
            jsonTue = gsonTue.toJson(tuesdayExercises);
            jsonWed = gsonWed.toJson(wednesdayExercises);
            jsonThu = gsonThu.toJson(thursdayExercises);
            jsonFri = gsonFri.toJson(fridayExercises);
            jsonSat = gsonSat.toJson(saturdayExercises);
            // Saves json files to storage
            editorSun.putString(id + "listSun", jsonSun);
            editorMon.putString(id + "listMon", jsonMon);
            editorTue.putString(id + "listTue", jsonTue);
            editorWed.putString(id + "listWed", jsonWed);
            editorThu.putString(id + "listThu", jsonThu);
            editorFri.putString(id + "listFri", jsonFri);
            editorSat.putString(id + "listSat", jsonSat);
            editorSun.apply();
            editorMon.apply();
            editorTue.apply();
            editorWed.apply();
            editorThu.apply();
            editorFri.apply();
            editorSat.apply();
        }

    }
      
}