package com.example.workoutmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class ExerciseTypeAdapter extends ArrayAdapter<ExerciseTypes> {
    // Initialize arrays
    private ArrayList<ExerciseTypes> exerciseTypes;
    ArrayList<String> exerciseNames = new ArrayList<String>();
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();

    // Initialize constructor
    public ExerciseTypeAdapter(Context context, ArrayList<ExerciseTypes> exerciseTypes) {
        super(context, 0, exerciseTypes);
        this.exerciseTypes = exerciseTypes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ExerciseTypes exerciseTypes = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_type_list_items, parent, false);
        }
        String value = exerciseTypes.getName(); // Retrieves name attribute from object
        String modifier = exerciseTypes.getModifier(); // Retrieves modifier attribute from object
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext()); // Initializes SharedPreferences object
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvTypeName);
        TextView tvMax = (TextView)convertView.findViewById(R.id.tvMax);
        TextView tvRealMax = (TextView)convertView.findViewById(R.id.tvMaxReal);
        String max = pref.getString(value +"_max", "0"); // Retrieves max from storage
        String maxReal = pref.getString(value +"_max_real", "0"); // Retrieves real max from storage
        double doubleMax = Double.valueOf(max);
        double doubleMaxReal = Double.valueOf(maxReal);
        doubleMax = Math.round(doubleMax);
        doubleMaxReal = Math.round(doubleMaxReal);
        int intMax = (int)doubleMax;
        int intMaxReal = (int)doubleMaxReal;
        // Populate the data into the template view using the data object
        tvName.setText(exerciseTypes.name);
        tvMax.setText("max\n"+Integer.toString(intMax));
        tvRealMax.setText("real max\n"+Integer.toString(intMaxReal));
        Button home = (Button) convertView.findViewById(R.id.getMaxFS);
        Button overloadButton = (Button) convertView.findViewById(R.id.overLoad);
        Button delete = (Button) convertView.findViewById(R.id.delete);

        // Activates method on yes button click
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(), GetMaxActivity.class);
                // Passes values to the GetMaxActivity
                intent.putExtra("workoutName", value);
                intent.putExtra("workoutModifier", modifier);
                getContext().startActivity(intent); // Starts GetMaxActivity
            }
        });

        if (exerciseTypes.getDeleteable() == true) {
            // Activates method on yes button click
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = exerciseTypes.getName(); // Retrieves exercise type name for delete activity
                    Intent intent = new Intent(getContext(), PopUpActivity.class);
                    intent.putExtra("name", name); // Passes name value to activity
                    getContext().startActivity(intent); // Starts pop up activity to delete exercise type
                }
            });
        }
        
        // Activates method on yes button click
        overloadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String value = exerciseTypes.getName(); // Retrieves exercise type name
                String exerciseRealMax = pref.getString(value+ "_max_real", "0"); // Retrieves real 1RM of a specific exercise type
                String exerciseMax = pref.getString(value+ "_max", "0"); // Retrieves 1RM of a specific exercise type
                // Initialize arrays
                ArrayList<String> workouts = new ArrayList<>();
                ArrayList<Exercises> sundayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> mondayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> tuesdayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> wednesdayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> thursdayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> fridayExorcises = new ArrayList<Exercises>();
                ArrayList<Exercises> saturdayExorcises = new ArrayList<Exercises>();
                Utilities util = new Utilities();
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = pref.edit();
                String bodyWeight = pref.getString("bodyWeight", "0"); // Retrieves body weight
                HashSet<String> set = (HashSet<String>) pref.getStringSet("workouts", null);
                double modifiedBodyWeight = util.modifyBodyWeight(modifier, bodyWeight); // Modifies body weight
                double doubleExerciseRealMax = Double.valueOf(exerciseRealMax);
                double doubleExerciseMax = Double.valueOf(exerciseMax);
                doubleExerciseMax = doubleExerciseRealMax * 0.05 + doubleExerciseRealMax - modifiedBodyWeight; // Overloads max by 5% and subtracts modified body weight
                double roundedExerciseMax = Math.round(doubleExerciseMax);
                exerciseMax = String.valueOf(doubleExerciseMax);
                String stringExerciseMax = String.valueOf(roundedExerciseMax);
                editor.putString(value+ "_max", exerciseMax); // Put max into storage
                editor.commit();
                doubleExerciseRealMax = doubleExerciseRealMax * 0.05 + doubleExerciseRealMax; // Overload real max by 5%
                double dRealFS_round = Math.round(doubleExerciseRealMax);
                tvMax.setText(stringExerciseMax); // Displays data in text view
                exerciseRealMax = String.valueOf(doubleExerciseRealMax);
                String front_squat_real_round = String.valueOf(dRealFS_round);
                editor.putString(value+ "_max_real", exerciseRealMax); // Put real max into storage
                editor.commit();
                tvRealMax.setText(front_squat_real_round);

                if(set != null){workouts = new ArrayList(set);} // Creates a new arraylist if hashset is empty
                for(int i = 0; i < workouts.size(); i++){
                    String id = workouts.get(i);
                    // Initialize preferences for each day of the week
                    SharedPreferences prefSun = getContext().getSharedPreferences(id+"sunday", getContext().MODE_PRIVATE);
                    SharedPreferences prefMon = getContext().getSharedPreferences(id+"monday", getContext().MODE_PRIVATE);
                    SharedPreferences prefTue = getContext().getSharedPreferences(id+"tuesday", getContext().MODE_PRIVATE);
                    SharedPreferences prefWed = getContext().getSharedPreferences(id+"wednesday", getContext().MODE_PRIVATE);
                    SharedPreferences prefThu = getContext().getSharedPreferences(id+"thursday", getContext().MODE_PRIVATE);
                    SharedPreferences prefFri = getContext().getSharedPreferences(id+"friday", getContext().MODE_PRIVATE);
                    SharedPreferences prefSat = getContext().getSharedPreferences(id+"saturday", getContext().MODE_PRIVATE);
                    Gson gsonSun = new Gson();
                    Gson gsonMon = new Gson();
                    Gson gsonTue = new Gson();
                    Gson gsonWed = new Gson();
                    Gson gsonThu = new Gson();
                    Gson gsonFri = new Gson();
                    Gson gsonSat = new Gson();
                    // Retrieves exercises of each day of the week from storage
                    String jsonSun = prefSun.getString(id+"listSun", null);
                    String jsonMon = prefMon.getString(id+"listMon", null);
                    String jsonTue = prefTue.getString(id+"listTue", null);
                    String jsonWed = prefWed.getString(id+"listWed", null);
                    String jsonThu = prefThu.getString(id+"listThu", null);
                    String jsonFri = prefFri.getString(id+"listFri", null);
                    String jsonSat = prefSat.getString(id+"listSat", null);
                    Type type = new TypeToken<ArrayList<Exercises>>() {}.getType(); // Initializes type token
                    // Creates new array lists if json files are empty
                    if (jsonSun == null){
                        sundayExorcises = new ArrayList<Exercises>();
                    }
                    else{sundayExorcises = gsonSun.fromJson(jsonSun, type);}
                    if (jsonMon == null){
                        mondayExorcises = new ArrayList<Exercises>();
                    }
                    else{mondayExorcises = gsonMon.fromJson(jsonMon, type);}
                    if (jsonTue == null){
                        tuesdayExorcises = new ArrayList<Exercises>();
                    }
                    else{tuesdayExorcises = gsonTue.fromJson(jsonTue, type);}
                    if (jsonWed == null){
                        wednesdayExorcises = new ArrayList<Exercises>();
                    }
                    else{wednesdayExorcises = gsonWed.fromJson(jsonWed, type);}
                    if (jsonThu == null){
                        thursdayExorcises = new ArrayList<Exercises>();
                    }
                    else{thursdayExorcises = gsonThu.fromJson(jsonThu, type);}
                    if (jsonFri == null){
                        fridayExorcises = new ArrayList<Exercises>();
                    }
                    else{fridayExorcises = gsonFri.fromJson(jsonFri, type);}
                    if (jsonSat == null){
                        saturdayExorcises = new ArrayList<Exercises>();
                    }
                    else{saturdayExorcises = gsonSat.fromJson(jsonSat, type);}

                    // Iterates through array list
                    for(int j = 0; j < sundayExorcises.size(); j++){
                        if (sundayExorcises.get(j).getName() != null){
                            // Compares daily exercise name attribute to exercise type name attribute
                            if(sundayExorcises.get(j).getName().equals(value)){
                                String reps = sundayExorcises.get(j).getReps(); // Retrieves reps value from daily exercises
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight); // Modifies body weight
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier); // Calculates weight
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier; // Calculates bar weight
                                // Adjusts reps if weight is less than 0 meaning body weight is too much resistance
                                if(dWeight < 0) {
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax); // Calculates reps
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    sundayExorcises.get(j).setReps(reps); // Sets new reps
                                }
                                sundayExorcises.get(j).setWeight(weight); // Sets new weight
                            }

                        }

                    }
                    for(int a = 0; a < mondayExorcises.size(); a++){
                        if (mondayExorcises.get(a).getName() != null){
                            if(mondayExorcises.get(a).getName().equals(value)){

                                String reps = mondayExorcises.get(a).getReps();

                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    mondayExorcises.get(a).setReps(reps);
                                }
                                mondayExorcises.get(a).setWeight(weight);
                            }

                        }

                    }

                    for(int b = 0; b < tuesdayExorcises.size(); b++){
                        if (tuesdayExorcises.get(b).getName() != null){
                            if(tuesdayExorcises.get(b).getName().equals(value)){
                                String reps = tuesdayExorcises.get(b).getReps();
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    tuesdayExorcises.get(b).setReps(reps);
                                }
                                tuesdayExorcises.get(b).setWeight(weight);
                            }
                        }
                    }

                    for(int j = 0; j < wednesdayExorcises.size(); j++){
                        if (wednesdayExorcises.get(j).getName() != null){
                            if(wednesdayExorcises.get(j).getName().equals(value)){
                                String reps = wednesdayExorcises.get(j).getReps();
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    wednesdayExorcises.get(j).setReps(reps);
                                }
                                wednesdayExorcises.get(j).setWeight(weight);
                            }
                        }
                    }

                    for(int j = 0; j < thursdayExorcises.size(); j++){
                        if (thursdayExorcises.get(j).getName() != null){
                            if(thursdayExorcises.get(j).getName().equals(value)){
                                String reps = thursdayExorcises.get(j).getReps();
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    thursdayExorcises.get(j).setReps(reps);
                                }
                                thursdayExorcises.get(j).setWeight(weight);
                            }
                        }
                    }

                    for(int j = 0; j < fridayExorcises.size(); j++){
                        if (fridayExorcises.get(j).getName() != null){
                            if(fridayExorcises.get(j).getName().equals(value)){
                                String reps = fridayExorcises.get(j).getReps();
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    fridayExorcises.get(j).setReps(reps);
                                }
                                fridayExorcises.get(j).setWeight(weight);
                            }
                        }
                    }

                    for(int j = 0; j < saturdayExorcises.size(); j++){
                        if (saturdayExorcises.get(j).getName() != null){
                            if(saturdayExorcises.get(j).getName().equals(value)){
                                String reps = saturdayExorcises.get(j).getReps();
                                double dModifier = util.modifyBodyWeight(modifier, bodyWeight);
                                String weight = util.calculateWeight(reps, exerciseRealMax, dModifier);
                                double dWeight = Double.valueOf(weight);
                                dWeight = dWeight - dModifier;
                                if(dWeight < 0) {
                                    //double intensity = modifier / max;
                                    double dReps = util.findReps(dModifier, doubleExerciseRealMax);
                                    dReps = Math.round(dReps);
                                    reps = String.valueOf(dReps);
                                    weight = "0";
                                    saturdayExorcises.get(j).setReps(reps);
                                }
                                saturdayExorcises.get(j).setWeight(weight);
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
                    // Converts arraylists to json files
                    jsonSun = gsonSun.toJson(sundayExorcises);
                    jsonMon = gsonMon.toJson(mondayExorcises);
                    jsonTue = gsonTue.toJson(tuesdayExorcises);
                    jsonWed = gsonWed.toJson(wednesdayExorcises);
                    jsonThu = gsonThu.toJson(thursdayExorcises);
                    jsonFri = gsonFri.toJson(fridayExorcises);
                    jsonSat = gsonSat.toJson(saturdayExorcises);
                    // Puts json files into storage
                    editorSun.putString(id+"listSun", jsonSun);
                    editorMon.putString(id+"listMon", jsonMon);
                    editorTue.putString(id+"listTue", jsonTue);
                    editorWed.putString(id+"listWed", jsonWed);
                    editorThu.putString(id+"listThu", jsonThu);
                    editorFri.putString(id+"listFri", jsonFri);
                    editorSat.putString(id+"listSat", jsonSat);
                    editorSun.apply();
                    editorMon.apply();
                    editorTue.apply();
                    editorWed.apply();
                    editorThu.apply();
                    editorFri.apply();
                    editorSat.apply();
                }

            }
        });

        return convertView;
    }
}
