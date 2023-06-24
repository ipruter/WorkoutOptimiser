package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashSet;

public class QuickWorkoutActivity extends AppCompatActivity {
    // Initialise arrays
    ArrayList<Routine> daysArrayList = new ArrayList<Routine>();
    ArrayList<BooleanDays> booleanDaysArrayList = new ArrayList<BooleanDays>();
    ArrayList<String> workouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_workout);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

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
        
        // Prompts user for 1RM if value is 0
        String squats = pref.getString("Squats"+"_max_real", "0");
        if(squats.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Squats");
            intent.putExtra("workoutModifier", "0.72");
            startActivity(intent);
        }
        String LegCurlsSeated = pref.getString("Leg curls seated"+"_max_real", "0");
        if(LegCurlsSeated.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Leg curls seated");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String bench = pref.getString("Bench"+"_max_real", "0");
        if(bench.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Bench");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String chinUps = pref.getString("Chin ups"+"_max_real", "0");
        if(chinUps.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Chin ups");
            intent.putExtra("workoutModifier", "1");
            startActivity(intent);
        }

        String pullUps = pref.getString("Pull ups"+"_max_real", "0");
        if(pullUps.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Pull ups");
            intent.putExtra("workoutModifier", "1");
            startActivity(intent);
        }

        String uprightRows = pref.getString("Upright rows"+"_max_real", "0");
        if(uprightRows.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Upright rows");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String calfRaises = pref.getString("Calf raises"+"_max_real", "0");
        if(calfRaises.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Calf raises");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String goodMornings = pref.getString("Good mornings"+"_max_real", "0");
        if(goodMornings.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Good mornings");
            intent.putExtra("workoutModifier", "0.72");
            startActivity(intent);
        }
        String frontSquats = pref.getString("Front squats"+"_max_real", "0");
        if(frontSquats.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Front squats");
            intent.putExtra("workoutModifier", "0.72");
            startActivity(intent);
        }
        String overheadPress = pref.getString("Overhead press"+"_max_real", "0");
        if(overheadPress.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Overhead press");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String dumbbellFlies = pref.getString("Dumbbell flies"+"_max_real", "0");
        if(dumbbellFlies.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Dumbbell flies");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String legCurlsLying = pref.getString("Leg curls lying"+"_max_real", "0");
        if(legCurlsLying.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Leg curls lying");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String legPress = pref.getString("Leg press"+"_max_real", "0");
        if(legPress.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Leg press");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String legExtensions = pref.getString("Leg extensions"+"_max_real", "0");
        if(legExtensions.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Leg extensions");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String dumbbellPress = pref.getString("Dumbbell press"+"_max_real", "0");
        if(dumbbellPress.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Dumbbell press");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String benchCloseGrip = pref.getString("Bench close grip"+"_max_real", "0");
        if(benchCloseGrip.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Bench close grip");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String skullCrushers = pref.getString("Skull crushers"+"_max_real", "0");
        if(skullCrushers.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Skull crushers");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String stiffLeggedDeadLift = pref.getString("Stiff legged dead lift"+"_max_real", "0");
        if(stiffLeggedDeadLift.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Stiff legged dead lift");
            intent.putExtra("workoutModifier", "0.72");
            startActivity(intent);
        }
        String shrugs = pref.getString("Shrugs"+"_max_real", "0");
        if(shrugs.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Shrugs");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }

        String lateralRaises = pref.getString("Lateral raises"+"_max_real", "0");
        if(lateralRaises.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Lateral raises");
            intent.putExtra("workoutModifier", "0");
            startActivity(intent);
        }
        String twistingCrunches = pref.getString("Twisting crunches"+"_max_real", "0");
        if(twistingCrunches.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Twisting crunches");
            intent.putExtra("workoutModifier", "0.5");
            startActivity(intent);
        }

        String Crunches = pref.getString("Crunches"+"_max_real", "0");
        if(Crunches.equals("0")) {
            Intent intent = new Intent(getApplicationContext(), GetMaxActivity.class);
            intent.putExtra("workoutName", "Crunches");
            intent.putExtra("workoutModifier", "0.5");
            startActivity(intent);
        }

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

                // Creates exercises based on how many days were checked
                if(count == 1){
                    ArrayList<Exercises> exercises = new ArrayList<Exercises>();
                    String SquatsMaxReal = pref.getString("Squats"+"_max_real", "0");
                    getWeight("0.72", SquatsMaxReal, "10", "Squats", "4", exercises);
                    String lcsMaxReal = pref.getString("Leg curls seated"+"_max_real", "0");
                    getWeight("0", lcsMaxReal, "10", "Leg curls seated", "4", exercises);
                    String benchMaxReal = pref.getString("Bench"+"_max_real", "0");
                    getWeight("0", benchMaxReal, "10", "Bench", "4", exercises);
                    String cuMaxReal = pref.getString("Chin ups"+"_max_real", "0");
                    getWeight("1", cuMaxReal, "10", "Chin ups", "4", exercises);
                    String urrMaxReal = pref.getString("Upright rows"+"_max_real", "0");
                    getWeight("0", urrMaxReal, "10", "Upright rows", "4", exercises);

                    save(name, daysArrayList.get(0).getDay(), exercises, daysArrayList.get(0).getList());
                }
                else if(count == 2){
                    for(int i = 0; i < count; i++){
                        if(daysArrayList.get(i).getExercises() == 0){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String SquatsMaxReal = pref.getString("Squats"+"_max_real", "0");
                            getWeight("0.72", SquatsMaxReal, "10", "Squats", "4", exercises);

                            String gmMaxReal = pref.getString("Good mornings"+"_max_real", "0");
                            getWeight("0.72", gmMaxReal, "10", "Good mornings", "4", exercises);

                            String fsMaxReal = pref.getString("Front squats"+"_max_real", "0");
                            getWeight("0.72", fsMaxReal, "10", "Front squats", "4", exercises);

                            String lcsMaxReal = pref.getString("Leg curls seated"+"_max_real", "0");
                            getWeight("0", lcsMaxReal, "10", "Leg curls seated", "4", exercises);

                            String crMaxReal = pref.getString("Calf raises"+"_max_real", "0");
                            getWeight("0", crMaxReal, "10", "Calf raises", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else{
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String benchMaxReal = pref.getString("Bench"+"_max_real", "0");
                            getWeight("0", benchMaxReal, "10", "Bench", "4", exercises);

                            String cuMaxReal = pref.getString("Chin ups"+"_max_real", "0");
                            getWeight("1", cuMaxReal, "10", "Chin ups", "4", exercises);

                            String urrMaxReal = pref.getString("Upright rows"+"_max_real", "0");
                            getWeight("0", urrMaxReal, "10", "Upright rows", "4", exercises);

                            String opMaxReal = pref.getString("Overhead press"+"_max_real", "0");
                            getWeight("0", opMaxReal, "10", "Overhead press", "4", exercises);

                            String dfMaxReal = pref.getString("Dumbbell flies"+"_max_real", "0");
                            getWeight("0", dfMaxReal, "10", "Dumbbell flies", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                    }
                    

                }
               else if(count == 3){
                    for(int i = 0; i < count; i++){
                        if(daysArrayList.get(i).getExercises() == 0){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String SquatsMaxReal = pref.getString("Squats"+"_max_real", "0");
                            getWeight("0.72", SquatsMaxReal, "10", "Squats", "4", exercises);

                            String gmMaxReal = pref.getString("Good mornings"+"_max_real", "0");
                            getWeight("0.72", gmMaxReal, "10", "Good mornings", "4", exercises);

                            String fsMaxReal = pref.getString("Front squats"+"_max_real", "0");
                            getWeight("0.72", fsMaxReal, "10", "Front squats", "4", exercises);

                            String lcsMaxReal = pref.getString("Leg curls seated"+"_max_real", "0");
                            getWeight("0", lcsMaxReal, "10", "Leg curls seated", "4", exercises);

                            String crMaxReal = pref.getString("Calf raises"+"_max_real", "0");
                            getWeight("0", crMaxReal, "10", "Calf raises", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 1){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String benchMaxReal = pref.getString("Bench"+"_max_real", "0");
                            getWeight("0", benchMaxReal, "10", "Bench", "4", exercises);

                            String dpMaxReal = pref.getString("Dumbbell presses"+"_max_real", "0");
                            getWeight("0", dpMaxReal, "10", "Dumbbell presses", "4", exercises);

                            String bcgMaxReal = pref.getString("Bench close grip"+"_max_real", "0");
                            getWeight("0", bcgMaxReal, "10", "Bench close grip", "4", exercises);

                            String opMaxReal = pref.getString("Overhead press"+"_max_real", "0");
                            getWeight("0", opMaxReal, "10", "Overhead press", "4", exercises);

                            String dfMaxReal = pref.getString("Dumbbell flies"+"_max_real", "0");
                            getWeight("0", dfMaxReal, "10", "Dumbbell flies", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else{
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String cuMaxReal = pref.getString("Chin ups"+"_max_real", "0");
                            getWeight("1", cuMaxReal, "10", "Chin ups", "4", exercises);

                            String rowsMaxReal = pref.getString("Rows"+"_max_real", "0");
                            getWeight("0", rowsMaxReal, "10", "Rows", "4", exercises);

                            String puMaxReal = pref.getString("Pull ups"+"_max_real", "0");
                            getWeight("1", puMaxReal, "10", "Pull ups", "4", exercises);

                            String urrMaxReal = pref.getString("Upright rows"+"_max_real", "0");
                            getWeight("0", urrMaxReal, "10", "Upright rows", "4", exercises);

                            String crunchesMaxReal = pref.getString("Crunches"+"_max_real", "0");
                            getWeight("0.5", crunchesMaxReal, "10", "Crunches", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                    }

                }
                else if(count == 4){
                    for(int i = 0; i < count; i++){
                        if(daysArrayList.get(i).getExercises() == 0){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String SquatsMaxReal = pref.getString("Squats"+"_max_real", "0");
                            getWeight("0.72", SquatsMaxReal, "10", "Squats", "4", exercises);

                            String fsMaxReal = pref.getString("Front squats"+"_max_real", "0");
                            getWeight("0.72", fsMaxReal, "10", "Front squats", "4", exercises);

                            String lpMaxReal = pref.getString("Leg press"+"_max_real", "0");
                            getWeight("0", lpMaxReal, "10", "Leg press", "4", exercises);

                            String leMaxReal = pref.getString("Leg extensions"+"_max_real", "0");
                            getWeight("0", leMaxReal, "10", "Leg extensions", "4", exercises);

                            String crunchesMaxReal = pref.getString("Crunches"+"_max_real", "0");
                            getWeight("0.5", crunchesMaxReal, "10", "Crunches", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 1){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String benchMaxReal = pref.getString("Bench"+"_max_real", "0");
                            getWeight("0", benchMaxReal, "10", "Bench", "4", exercises);

                            String dpMaxReal = pref.getString("Dumbbell presses"+"_max_real", "0");
                            getWeight("0", dpMaxReal, "10", "Dumbbell presses", "4", exercises);

                            String bcgMaxReal = pref.getString("Bench close grip"+"_max_real", "0");
                            getWeight("0", bcgMaxReal, "10", "Bench close grip", "4", exercises);

                            String opMaxReal = pref.getString("Overhead press"+"_max_real", "0");
                            getWeight("0", opMaxReal, "10", "Overhead press", "4", exercises);

                            String dfMaxReal = pref.getString("Dumbbell flies"+"_max_real", "0");
                            getWeight("0", dfMaxReal, "10", "Dumbbell flies", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 2){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String sldlMaxReal = pref.getString("Stiff legged dead lift"+"_max_real", "0");
                            getWeight("0.72", sldlMaxReal, "10", "Stiff legged dead lift", "4", exercises);

                            String gmMaxReal = pref.getString("Good mornings"+"_max_real", "0");
                            getWeight("0.72", gmMaxReal, "10", "Good mornings", "4", exercises);

                            String lcsMaxReal = pref.getString("Leg curls seated"+"_max_real", "0");
                            getWeight("0", lcsMaxReal, "10", "Leg curls seated", "4", exercises);

                            String lclMaxReal = pref.getString("Leg curls lying"+"_max_real", "0");
                            getWeight("0", lclMaxReal, "10", "Leg curls lying", "4", exercises);

                            String crMaxReal = pref.getString("Calf raises"+"_max_real", "0");
                            getWeight("0", crMaxReal, "10", "Calf raises", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else{
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String cuMaxReal = pref.getString("Chin ups"+"_max_real", "0");
                            getWeight("1", cuMaxReal, "10", "Chin ups", "4", exercises);

                            String rowsMaxReal = pref.getString("Rows"+"_max_real", "0");
                            getWeight("0", rowsMaxReal, "10", "Rows", "4", exercises);

                            String puMaxReal = pref.getString("Pull ups"+"_max_real", "0");
                            getWeight("1", puMaxReal, "10", "Pull ups", "4", exercises);

                            String urrMaxReal = pref.getString("Upright rows"+"_max_real", "0");
                            getWeight("0", urrMaxReal, "10", "Upright rows", "4", exercises);

                            String crunchesMaxReal = pref.getString("Crunches"+"_max_real", "0");
                            getWeight("0.5", crunchesMaxReal, "10", "Crunches", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());

                        }
                    }
                }

                 else {
                    for(int i = 0; i < count; i++){
                        if(daysArrayList.get(i).getExercises() == 0){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String SquatsMaxReal = pref.getString("Squats"+"_max_real", "0");
                            getWeight("0.72", SquatsMaxReal, "10", "Squats", "4", exercises);

                            String fsMaxReal = pref.getString("Front squats"+"_max_real", "0");
                            getWeight("0.72", fsMaxReal, "10", "Front squats", "4", exercises);

                            String lpMaxReal = pref.getString("Leg press"+"_max_real", "0");
                            getWeight("0", lpMaxReal, "10", "Leg press", "4", exercises);

                            String leMaxReal = pref.getString("Leg extensions"+"_max_real", "0");
                            getWeight("0", leMaxReal, "10", "Leg extensions", "4", exercises);

                            String crMaxReal = pref.getString("Calf raises"+"_max_real", "0");
                            getWeight("0", crMaxReal, "10", "Calf raises", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 1){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String benchMaxReal = pref.getString("Bench"+"_max_real", "0");
                            getWeight("0", benchMaxReal, "10", "Bench", "4", exercises);

                            String dpMaxReal = pref.getString("Dumbbell presses"+"_max_real", "0");
                            getWeight("0", dpMaxReal, "10", "Dumbbell presses", "4", exercises);

                            String bcgMaxReal = pref.getString("Bench close grip"+"_max_real", "0");
                            getWeight("0", bcgMaxReal, "10", "Bench close grip", "4", exercises);

                            String dfMaxReal = pref.getString("Dumbbell flies"+"_max_real", "0");
                            getWeight("0", dfMaxReal, "10", "Dumbbell flies", "4", exercises);

                            String scMaxReal = pref.getString("Skull crushers"+"_max_real", "0");
                            getWeight("0", scMaxReal, "10", "Skull crushers", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 2){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String sldlMaxReal = pref.getString("Stiff legged dead lift"+"_max_real", "0");
                            getWeight("0.72", sldlMaxReal, "10", "Stiff legged dead lift", "4", exercises);

                            String gmMaxReal = pref.getString("Good mornings"+"_max_real", "0");
                            getWeight("0.72", gmMaxReal, "10", "Good mornings", "4", exercises);

                            String lcsMaxReal = pref.getString("Leg curls seated"+"_max_real", "0");
                            getWeight("0", lcsMaxReal, "10", "Leg curls seated", "4", exercises);

                            String lclMaxReal = pref.getString("Leg curls lying"+"_max_real", "0");
                            getWeight("0", lclMaxReal, "10", "Leg curls lying", "4", exercises);

                            String crMaxReal = pref.getString("Calf raises"+"_max_real", "0");
                            getWeight("0", crMaxReal, "10", "Calf raises", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else if(daysArrayList.get(i).getExercises() == 3){
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String cuMaxReal = pref.getString("Chin ups"+"_max_real", "0");
                            getWeight("1", cuMaxReal, "10", "Chin ups", "4", exercises);

                            String rowsMaxReal = pref.getString("Rows"+"_max_real", "0");
                            getWeight("0", rowsMaxReal, "10", "Rows", "4", exercises);

                            String puMaxReal = pref.getString("Pull ups"+"_max_real", "0");
                            getWeight("1", puMaxReal, "10", "Pull ups", "4", exercises);

                            String bcMaxReal = pref.getString("Bicep curls"+"_max_real", "0");
                            getWeight("0", bcMaxReal, "10", "Bicep curls", "4", exercises);

                            String urrMaxReal = pref.getString("Upright rows"+"_max_real", "0");
                            getWeight("0", urrMaxReal, "10", "Upright rows", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                        else{
                            ArrayList<Exercises> exercises = new ArrayList<Exercises>();

                            String opMaxReal = pref.getString("Overhead press"+"_max_real", "0");
                            getWeight("0", opMaxReal, "10", "Overhead press", "4", exercises);

                            String lrMaxReal = pref.getString("Lateral raises"+"_max_real", "0");
                            getWeight("0", lrMaxReal, "10", "Lateral raises", "4", exercises);

                            String shrugsMaxReal = pref.getString("Shrugs"+"_max_real", "0");
                            getWeight("0", shrugsMaxReal, "10", "Shrugs", "4", exercises);

                            String crunchesMaxReal = pref.getString("Crunches"+"_max_real", "0");
                            getWeight("0.5", crunchesMaxReal, "10", "Crunches", "4", exercises);

                            String tcMaxReal = pref.getString("Twisting crunches"+"_max_real", "0");
                            getWeight("0.5", tcMaxReal, "10", "Twisting crunches", "4", exercises);

                            save(name, daysArrayList.get(i).getDay(), exercises, daysArrayList.get(i).getList());
                        }
                    }

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
}