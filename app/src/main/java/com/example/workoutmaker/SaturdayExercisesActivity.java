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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashSet;

public class SaturdayExercisesActivity extends AppCompatActivity {
    // initializing variables
    ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();
    ArrayList<String> exerciseNames = new ArrayList<String>();
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
    String StrMaxReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturday_exercises);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // Initializing shared preference object
        loadSpinnerNames(); // Loading spinner names
        // Initializing objects
        Utilities util = new Utilities();
        ExerciseView exerciseView = new ExerciseView();
        Spinner spinner = (Spinner) findViewById(R.id.nameET_saturday);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, exerciseNames);
        spinner.setAdapter(adapter); // Setting spinner adapter

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            // Listens for a selection of a spinner item
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String modifier = "";
                // Checks if the spinner is not empty
                if(!exerciseNames.get(i).equals("Select an exercise")){
                String name = exerciseNames.get(i); // Retrieves spinner value as exercise name
                StrMaxReal = pref.getString(name+"_max_real", "0"); // Retrieves real max from storage
                    // Prompts user to enter real max if its missing
                if(StrMaxReal.equals("0")){
                    Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
                    intent.putExtra("workoutName", name);
                    // Initializing objects
                    SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = pref.getString("listExerciseTypeList", null);
                    Type type = new TypeToken<ArrayList<ExerciseTypes>>() {}.getType();
                    // Creates new array if json file is empty
                    if (json == null){
                        exerciseTypesList = new ArrayList<ExerciseTypes>();
                    }
                    // Converts json file to array list otherwise
                    else{exerciseTypesList = gson.fromJson(json, type);}
                    // Loops through array to
                    for(int j = 0; j < exerciseTypesList.size(); j++){
                        // Checks if array items name equals the spinner name and retrieves corresponding modifier if true
                        if(exerciseTypesList.get(j).getName().equals(name)){
                            modifier = exerciseTypesList.get(j).getModifier();
                        }
                    }
                    // Starts activity for user to enter in their max weight
                    intent.putExtra("workoutModifier", modifier);
                    startActivity(intent);
                }}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();
        // Accessing the data using key and value
        String workoutId = intent.getStringExtra("workoutId");
        load(workoutId); // Loads all of Fridays exercises
        String bodyWeight = pref.getString("bodyWeight", "0");
        Button save = (Button)findViewById(R.id.save_saturday);
        // Activates method on yes button click
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                save(workoutId);
            }
        });

        // Create the adapter to convert the array to views
        ExerciseAdapter saturdayAdapter = new ExerciseAdapter(this, saturdayExercises);
        // Attach the adapter to a ListView
        ListView saturdayLV = (ListView) findViewById(R.id.saturdayLV);
        saturdayLV.setAdapter(saturdayAdapter);
        EditText setsET = (EditText) findViewById(R.id.setsET_saturday);
        EditText repsET = (EditText) findViewById(R.id.repsET_saturday);
        EditText weightET = (EditText) findViewById(R.id.weightET_saturday);
        Button add = (Button)findViewById(R.id.saturday_add);
        // Activates method on yes button click
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strModifier = "";
                String sets = String.valueOf(setsET.getText());
                String reps = String.valueOf(repsET.getText());
                String weight = String.valueOf(weightET.getText());
                String name = spinner.getSelectedItem().toString();
                loadExerciseTypes();
                // Loops through array of exercise types
                for(int j = 0; j < exerciseTypesList.size(); j++){
                    // Compares exercise type name to spinner name to get corresponding modifier
                    if(exerciseTypesList.get(j).getName().equals(name)){
                        strModifier = exerciseTypesList.get(j).getModifier();
                    }
                }
                String displayWeight = "";
                double modifier = util.modifyBodyWeight(strModifier, bodyWeight); // Factors bodyweight into modifier
                double max = Double.valueOf(StrMaxReal);
                double doubleReps = 0;
                // Checks if user filled the weight form
                if (weight.equals("")){
                    displayWeight = util.calculateWeight(reps, StrMaxReal, modifier); // Calculates weight
                    double doubleDisplayWeight = Double.valueOf(displayWeight);
                    // Sets weight to 0 and finds reps instead if weight is less than 0
                    if(doubleDisplayWeight < 0){
                        doubleReps = util.findReps(modifier, max); // Calculates reps
                        doubleReps = Math.round(doubleReps);
                        reps = String.valueOf(doubleReps);
                        displayWeight = "0";
                    }
                }
                // Calculates reps if user enters weight and uses the users input weight for weight
                else{
                     double doubleWeight = Double.valueOf(weight);
                     doubleWeight = doubleWeight + modifier;
                    reps = util.calculateReps(doubleWeight, max); // Calculates users reps
                    displayWeight = weight; 
                     
                }
                // Creates new exercise and adds it to Friday's exercises
                Exercises exercise = new Exercises(name, reps, sets, displayWeight);
                saturdayExercises.add(exercise);
                saturdayAdapter.notifyDataSetChanged();
            }
        });

        // Deletes exercises that user holds down on
        saturdayLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                // To delete the data from the App
                new AlertDialog.Builder(SaturdayExercisesActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            // Removes item from array
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                saturdayExercises.remove(itemToDelete);
                                saturdayAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });
    }
    // Saves Saturday's exercises
    public void save(String id){
        SharedPreferences pref = getSharedPreferences(id+"saturday", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String jsonSun = gson.toJson(saturdayExercises); // Converts array to json file
        editor.putString(id+"listSat", jsonSun); // Saves json file
        editor.apply();

    }

    // Loads Saturday's exercises
    public void load(String id){
        SharedPreferences pref = getSharedPreferences(id+"saturday", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(id+"listSat", null); // Retrieves json file from storage
        Type type = new TypeToken<ArrayList<Exercises>>() {}.getType(); // Creates type token
        // Initializes new array if json file is null
        if (json == null){
            ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();
        }
        else{saturdayExercises = gson.fromJson(json, type);}
    }

    // Loads spinner names
    public void loadSpinnerNames(){
        SharedPreferences pref2 = getSharedPreferences("spinnerNames", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref2.getString("spinnerNamesList", null); // Retrieves json file from storage
        Type type = new TypeToken<ArrayList<String>>() {}.getType(); // Creates type token
        // Initializes new array if json file is null
        if (json == null){
            exerciseNames = new ArrayList<String>();
        }
        else{exerciseNames = gson.fromJson(json, type);}

    }

    // Loads exercise types
    public void loadExerciseTypes(){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("listExerciseTypeList", null); // Retrieves json file from storage
        Type type = new TypeToken<ArrayList<ExerciseTypes>>() {}.getType(); // Creates type token
        // Initializes new array if json file is null
        if (json == null){
            exerciseTypesList = new ArrayList<ExerciseTypes>();
        }
        else{exerciseTypesList = gson.fromJson(json, type);}

    }
}