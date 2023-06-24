package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class ExerciseStandInActivity extends AppCompatActivity {
    // Defines arrays
    ArrayList<Exercises> sundayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> mondayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> tuesdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> wednesdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> thursdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> fridayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_stand_in);
        // Retrieves data from forms
        Button edit = (Button)findViewById(R.id.edit);
        TextView tvExerciseList = (TextView)findViewById(R.id.exercise_list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String exerciseList;
        HashSet<String> set = (HashSet<String>) pref.getStringSet("workouts", null);
        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();
        // Accessing the data using key and value
        String workoutId = intent.getStringExtra("workoutId");
        
        SharedPreferences prefSun = getSharedPreferences(workoutId+"sunday", MODE_PRIVATE);
        SharedPreferences prefMon = getSharedPreferences(workoutId+"monday", MODE_PRIVATE);
        SharedPreferences prefTue = getSharedPreferences(workoutId+"tuesday", MODE_PRIVATE);
        SharedPreferences prefWed = getSharedPreferences(workoutId+"wednesday", MODE_PRIVATE);
        SharedPreferences prefThu = getSharedPreferences(workoutId+"thursday", MODE_PRIVATE);
        SharedPreferences prefFri = getSharedPreferences(workoutId+"friday", MODE_PRIVATE);
        SharedPreferences prefSat = getSharedPreferences(workoutId+"saturday", MODE_PRIVATE);
        Gson gsonSun = new Gson();
        Gson gsonMon = new Gson();
        Gson gsonTue = new Gson();
        Gson gsonWed = new Gson();
        Gson gsonThu = new Gson();
        Gson gsonFri = new Gson();
        Gson gsonSat = new Gson();
        // Retrieve json files from storage
        String jsonSun = prefSun.getString(workoutId+"listSun", null);
        String jsonMon = prefMon.getString(workoutId+"listMon", null);
        String jsonTue = prefTue.getString(workoutId+"listTue", null);
        String jsonWed = prefWed.getString(workoutId+"listWed", null);
        String jsonThu = prefThu.getString(workoutId+"listThu", null);
        String jsonFri = prefFri.getString(workoutId+"listFri", null);
        String jsonSat = prefSat.getString(workoutId+"listSat", null);
        Type type = new TypeToken<ArrayList<Exercises>>() {}.getType(); // Initialize type token
        // Check for null files and creates an array in that instance
        if (jsonSun == null){
            ArrayList<Exercises> sundayExercises = new ArrayList<Exercises>();
        }
        else{sundayExercises = gsonSun.fromJson(jsonSun, type);}
        if (jsonMon == null){
            ArrayList<Exercises> mondayExercises = new ArrayList<Exercises>();
        }
        else{mondayExercises = gsonMon.fromJson(jsonMon, type);}
        if (jsonTue == null){
            ArrayList<Exercises> tuesdayExercises = new ArrayList<Exercises>();
        }
        else{tuesdayExercises = gsonTue.fromJson(jsonTue, type);}
        if (jsonWed == null){
            ArrayList<Exercises> wednesdayExercises = new ArrayList<Exercises>();
        }
        else{wednesdayExercises = gsonWed.fromJson(jsonWed, type);}
        if (jsonThu == null){
            ArrayList<Exercises> thursdayExercises = new ArrayList<Exercises>();
        }
        else{thursdayExercises = gsonThu.fromJson(jsonThu, type);}
        if (jsonFri == null){
            ArrayList<Exercises> fridayExercises = new ArrayList<Exercises>();
        }
        else{fridayExercises = gsonFri.fromJson(jsonFri, type);}
        if (jsonSat == null){
            ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();
        }
        else{saturdayExercises = gsonSat.fromJson(jsonSat, type);}
            
        // Creates a list of call workouts for that week
        exerciseList = "Sunday\n";
        for(int j = 0; j < sundayExercises.size(); j++){
            if (sundayExercises.get(j).getName() != null){
                exerciseList = exerciseList + sundayExercises.get(j).getName() + "     " + sundayExercises.get(j).getWeight() + "     X     " +  sundayExercises.get(j).getReps() + "     X     " + sundayExercises.get(j).getSets() + "\n";
            }
        }
        exerciseList = exerciseList + "\nMonday\n";
        for(int j = 0; j < mondayExercises.size(); j++){
            if (mondayExercises.get(j).getName() != null){
                exerciseList = exerciseList + mondayExercises.get(j).getName() + "     " + mondayExercises.get(j).getWeight() + "     X     " +  mondayExercises.get(j).getReps() + "     X     " + mondayExercises.get(j).getSets() + "\n";
            }
        }
        exerciseList = exerciseList + "\nTuesday\n";
            for(int j = 0; j < tuesdayExercises.size(); j++){
                if (tuesdayExercises.get(j).getName() != null){
                    exerciseList = exerciseList + tuesdayExercises.get(j).getName() + "     " + tuesdayExercises.get(j).getWeight() + "     X     " +  tuesdayExercises.get(j).getReps() + "     X     " + tuesdayExercises.get(j).getSets() + "\n";
                }
            }
        exerciseList = exerciseList + "\nWednesday\n";
            for(int j = 0; j < wednesdayExercises.size(); j++){
                if (wednesdayExercises.get(j).getName() != null){
                    exerciseList = exerciseList + wednesdayExercises.get(j).getName() + "     " + wednesdayExercises.get(j).getWeight() + "     X     " +  wednesdayExercises.get(j).getReps() + "     X     " + wednesdayExercises.get(j).getSets() + "\n";
                }
            }
        exerciseList = exerciseList + "\nThursday\n";
            for(int j = 0; j < thursdayExercises.size(); j++){
                if (thursdayExercises.get(j).getName() != null){
                    exerciseList = exerciseList + thursdayExercises.get(j).getName() + "     " + thursdayExercises.get(j).getWeight() + "     X     " +  thursdayExercises.get(j).getReps() + "     X     " + thursdayExercises.get(j).getSets() + "\n";
                }
            }
        exerciseList = exerciseList + "\nFriday\n";
            for(int j = 0; j < fridayExercises.size(); j++){
                if (fridayExercises.get(j).getName() != null){
                    exerciseList = exerciseList + fridayExercises.get(j).getName() + "     " + fridayExercises.get(j).getWeight() + "     X     " +  fridayExercises.get(j).getReps() + "     X     " + fridayExercises.get(j).getSets() + "\n";
                }
            }
        exerciseList = exerciseList + "\nSaturday\n";
            for(int j = 0; j < saturdayExercises.size(); j++){
                if (saturdayExercises.get(j).getName() != null){
                    exerciseList = exerciseList + saturdayExercises.get(j).getName() + "     " + saturdayExercises.get(j).getWeight() + "     X     " +  saturdayExercises.get(j).getReps() + "     X     " + saturdayExercises.get(j).getSets() + "\n";
                }
            }
        tvExerciseList.setText(exerciseList); // Displays workouts for that week
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Going from MainActivity to NotesEditorActivity
                Intent intent = new Intent(getApplicationContext(), ExerciseView.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);
            }
        });

    }
}