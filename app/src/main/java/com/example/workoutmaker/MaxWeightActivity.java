package com.example.workoutmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MaxWeightActivity extends AppCompatActivity {
    public MaxWeightActivity(){}
    // Initialize variables
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
    ArrayList<String> exerciseNames = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_weight);
        
        // Loads data
        load();
        loadSpinnerNames();

        // Create the adapter to convert the array to views
        ExerciseTypeAdapter exercise_type_adapter = new ExerciseTypeAdapter(this, exerciseTypesList);
        // Attach the adapter to a ListView
        ListView exerciseTypeLV = (ListView) findViewById(R.id.exercise_type_LV);
        exerciseTypeLV.setAdapter(exercise_type_adapter);
        Button addExercisesType = (Button) findViewById(R.id.add_exercises); // Initialize button
        // Activates method on yes button click
        addExercisesType.setOnClickListener(new View.OnClickListener(){
            // Goes to AddExercise
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MaxWeightActivity.this, AddExercise.class);
                startActivity(intent);
            }
        });
        
        exerciseTypeLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                // Pop up window
                new AlertDialog.Builder(MaxWeightActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            // Deletes the data from the App
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name = exerciseTypesList.get(itemToDelete).getName(); // Retrieves the name of the exercise type
                                exerciseTypesList.remove(itemToDelete); // Deletes item
                                exercise_type_adapter.notifyDataSetChanged();
                                // Deletes the associated spinner name with the exercise type
                                for (i = 0; i < exerciseNames.size(); i++) {
                                    if (exerciseNames.get(i).equals(name)) {
                                        exerciseNames.remove(i);
                                    }
                                }
                                saveSpinnerNames();
                                exercise_type_adapter.notifyDataSetChanged();
                                save(exerciseTypesList);
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });}
        
        // Save exercise types
    public void save(ArrayList<ExerciseTypes> exerciseTypesList){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseTypesList);
        editor.putString("listExerciseTypeList", json);
        editor.commit();

    }

    // Load exercise types
    public void load(){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("listExerciseTypeList", null);
        Type type = new TypeToken<ArrayList<ExerciseTypes>>() {}.getType();
        if (json == null){
            exerciseTypesList = new ArrayList<ExerciseTypes>();
        }
        else{exerciseTypesList = gson.fromJson(json, type);}
    }

    // Save spinner names
    public void saveSpinnerNames(){
        SharedPreferences pref = getSharedPreferences("spinnerNames", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseNames);
        editor.putString("spinnerNamesList", json);
        editor.commit();

    }

    // Load spinner names
    public void loadSpinnerNames(){
        SharedPreferences pref = getSharedPreferences("spinnerNames", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("spinnerNamesList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        if (json == null){
            exerciseNames = new ArrayList<String>();
        }
        else{exerciseNames = gson.fromJson(json, type);}

    }
}