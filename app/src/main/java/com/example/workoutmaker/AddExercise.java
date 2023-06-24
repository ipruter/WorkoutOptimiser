package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddExercise extends AppCompatActivity {
    // Initialize array
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
    ArrayList<String> exerciseNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        MaxWeightActivity maxWeightActivity = new MaxWeightActivity();
        // Loads array data
        load();
        loadSpinnerNames();
        EditText nameET = (EditText) findViewById(R.id.spinner_name);
        EditText modifierET = (EditText) findViewById(R.id.modifier);
        Button addExercisesType = (Button) findViewById(R.id.add_exercise_type);
        // Activates method on yes button click
        addExercisesType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Retrieves user input
                EditText nameET = (EditText) findViewById(R.id.spinner_name);
                EditText modifierET = (EditText) findViewById(R.id.modifier);
                String name = String.valueOf(nameET.getText());
                String modifier = String.valueOf(modifierET.getText());
                exerciseTypesList.add(new ExerciseTypes(name, modifier, true)); // Creates new exercise type object
                save(exerciseTypesList); // Saves object
                exerciseNames.add(name); // Adds new spinner name
                saveSpinnerNames(); // Saves spinner name
                // Goes back to max weight activity
                Intent intent = new Intent(AddExercise.this, MaxWeightActivity.class);
                startActivity(intent);
                }
        });
    }
    
    // Loads exercise types from storage
    public void load(){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("listExerciseTypeList", null); // Loads json file
        Type type = new TypeToken<ArrayList<ExerciseTypes>>() {}.getType(); // Creates type token
        // Initializes new array if json file is null
        if (json == null){
            ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
        }
        else{exerciseTypesList = gson.fromJson(json, type);} // Converts json file to arraylist if not null
    }
    
    // Saves exercise types to storage
    public void save(ArrayList<ExerciseTypes> exerciseTypesList){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseTypesList); // Converts array to json file
        editor.putString("listExerciseTypeList", json); // Saves json file
        editor.apply();

    }
    
    // Save spinner names
    public void saveSpinnerNames(){
        SharedPreferences pref = getSharedPreferences("spinnerNames", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseNames);
        editor.putString("spinnerNamesList", json);
        editor.apply();

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