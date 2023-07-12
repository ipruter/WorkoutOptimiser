package com.example.workoutmaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Type;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.time.OffsetDateTime;

public class MainActivity extends AppCompatActivity {
    // Defines lists of daily exercises
    ArrayList<Exercises> sundayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> mondayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> tuesdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> wednesdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> thursdayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> fridayExercises = new ArrayList<Exercises>();
    ArrayList<Exercises> saturdayExercises = new ArrayList<Exercises>();
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>(); // Defines lists of exercise types
    ArrayList<String> exerciseNames = new ArrayList<String>(); // Defines lists of exercise names

    // Defines values to modify percentage of body weight used in a particular type of workout
    String squat = "0.72";
    String hackSquat = "0.36";
    String full = "1";
    String half = "0.5";
    String none = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigns variables for buttons and text views
        Button maxButton = (Button)findViewById(R.id.set_max_weight);
        Button editBodyWeight = (Button)findViewById(R.id.edit_body_weight);
        TextView currentExercise = (TextView)findViewById(R.id.current_exercise);

        loadExerciseTypes(); // Loads an array list of types of exercises

        // Adds all exercises and spinner names for first time user
        if(exerciseTypesList.isEmpty()) {
            exerciseNames.add("Select an exercise");
            exerciseTypesList.add(new ExerciseTypes("Barbell shrugs", none, false));
            exerciseNames.add("Barbell shrugs");
            exerciseTypesList.add(new ExerciseTypes("Bench", none, false));
            exerciseNames.add("Bench");
            exerciseTypesList.add(new ExerciseTypes("Bench close grip", none, false));
            exerciseNames.add("Bench close grip");
            exerciseTypesList.add(new ExerciseTypes("Bench incline", none, false));
            exerciseNames.add("Bench incline");
            exerciseTypesList.add(new ExerciseTypes("Easy bar curls", none, false));
            exerciseNames.add("Easy bar curls");
            exerciseTypesList.add(new ExerciseTypes("Cable bar triceps extensions", none, false));
            exerciseNames.add("Cable bar triceps extensions");
            exerciseTypesList.add(new ExerciseTypes("Cable rope triceps extensions", none, false));
            exerciseNames.add("Cable rope triceps extensions");
            exerciseTypesList.add(new ExerciseTypes("Cable rows", none, false));
            exerciseNames.add("Cable rows");
            exerciseTypesList.add(new ExerciseTypes("Calf raises leg press", none, false));
            exerciseNames.add("Calf raises leg press");
            exerciseTypesList.add(new ExerciseTypes("Chin ups", full, false));
            exerciseNames.add("Chin ups");
            exerciseTypesList.add(new ExerciseTypes("Crunches", half, false));
            exerciseNames.add("Crunches");
            exerciseTypesList.add(new ExerciseTypes("Crunches twisting", half, false));
            exerciseNames.add("Crunches twisting");
            exerciseTypesList.add(new ExerciseTypes("Dead lift", squat, false));
            exerciseNames.add("Dead lift");
            exerciseTypesList.add(new ExerciseTypes("Dips", full, false));
            exerciseNames.add("Dips");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell curls", none, false));
            exerciseNames.add("Dumbbell curls");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell flies", none, false));
            exerciseNames.add("Dumbbell flies");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell presses", none, false));
            exerciseNames.add("Dumbbell presses incline");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell presses incline", none, false));
            exerciseNames.add("Dumbbell presses");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell rows", none, false));
            exerciseNames.add("Dumbbell rows");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell shrugs", none, false));
            exerciseNames.add("Dumbbell shrugs");
            exerciseTypesList.add(new ExerciseTypes("Dumbbell shoulder presses", none, false));
            exerciseNames.add("Dumbbell shoulder presses");
            exerciseTypesList.add(new ExerciseTypes("Face palms", none, false));
            exerciseNames.add("Face palms");
            exerciseTypesList.add(new ExerciseTypes("Front squats", squat, false));
            exerciseNames.add("Front squats");
            exerciseTypesList.add(new ExerciseTypes("Good mornings", squat, false));
            exerciseNames.add("Good mornings");
            exerciseTypesList.add(new ExerciseTypes("Hack squat machine", hackSquat, false));
            exerciseNames.add("Hack squat machine");
            exerciseTypesList.add(new ExerciseTypes("Hammer curls", none, false));
            exerciseNames.add("Hammer curls");
            exerciseTypesList.add(new ExerciseTypes("Lateral raises", none, false));
            exerciseNames.add("Lateral raises");
            exerciseTypesList.add(new ExerciseTypes("Leg abductions", none, false));
            exerciseNames.add("Leg abductions");
            exerciseTypesList.add(new ExerciseTypes("Leg adductions", none, false));
            exerciseNames.add("Leg adductions");
            exerciseTypesList.add(new ExerciseTypes("Leg curls lying", none, false));
            exerciseNames.add("Leg curls lying");
            exerciseTypesList.add(new ExerciseTypes("Leg curls seated", none, false));
            exerciseNames.add("Leg curls seated");
            exerciseTypesList.add(new ExerciseTypes("Leg extensions", none, false));
            exerciseNames.add("Leg extensions");
            exerciseTypesList.add(new ExerciseTypes("Leg press", none, false));
            exerciseNames.add("Leg press");
            exerciseTypesList.add(new ExerciseTypes("Overhead press", none, false));
            exerciseNames.add("Overhead press");
            exerciseTypesList.add(new ExerciseTypes("Pull downs", none, false));
            exerciseNames.add("Pull downs");
            exerciseTypesList.add(new ExerciseTypes("Pull ups", full, false));
            exerciseNames.add("Pull ups");
            exerciseTypesList.add(new ExerciseTypes("Push ups", half, false));
            exerciseNames.add("Push ups");
            exerciseTypesList.add(new ExerciseTypes("Rows", none, false));
            exerciseNames.add("Rows");
            exerciseTypesList.add(new ExerciseTypes("Seated calf raises", none, false));
            exerciseNames.add("Seated calf raises");
            exerciseTypesList.add(new ExerciseTypes("Seated Rows", none, false));
            exerciseNames.add("Seated Rows");
            exerciseTypesList.add(new ExerciseTypes("Skull crushers", none, false));
            exerciseNames.add("Skull crushers");
            exerciseTypesList.add(new ExerciseTypes("Squats", squat, false));
            exerciseNames.add("Squats");
            exerciseTypesList.add(new ExerciseTypes("Standing calf raises", full, false));
            exerciseNames.add("Standing calf raises");
            exerciseTypesList.add(new ExerciseTypes("Stiff legged dead lift", squat, false));
            exerciseNames.add("Stiff legged dead lift");
            exerciseTypesList.add(new ExerciseTypes("T-bar rows", none, false));
            exerciseNames.add("T-bar rows");
            exerciseTypesList.add(new ExerciseTypes("Twisting crunches", half, false));
            exerciseNames.add("Twisting crunches");
            exerciseTypesList.add(new ExerciseTypes("Upright rows", none, false));
            exerciseNames.add("Upright rows");
            exerciseTypesList.add(new ExerciseTypes("Upright rows cable rope", none, false));
            exerciseNames.add("Upright rows cable rope");
            exerciseTypesList.add(new ExerciseTypes("Wrist curls", none, false));
            exerciseNames.add("Wrist curls");

            // Saves array lists of exercise names and typs
            saveExerciseTypes(exerciseTypesList);
            saveSpinnerNames();
        }

        // Creates objects for saving data
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String bodyWeight = pref.getString("bodyWeight", "0");
        String currentWorkout = pref.getString("currentWorkout", null);

        // Prompts user to enter body weight when the program starts
        if(bodyWeight.equals("0")){
            // Opens activity for user to enter their body weight
            Intent intent = new Intent(MainActivity.this, EditBodyWeightActivity.class);
            startActivity(intent);
        }

        // Finds the day of the week
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String exerciseList = ""; // Defines a list of exercises used to display the entire week

        Type type = new TypeToken<ArrayList<Exercises>>() {}.getType(); // Creates TypeToken object to establish variable type
        // Gets current date
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            OffsetDateTime offset = OffsetDateTime.now();

            // Displays a list of workouts to do based on the current day
        if(currentWorkout != null) {
            if (offset.getDayOfWeek().name().equals("SUNDAY")) {
                SharedPreferences prefSun = getSharedPreferences(currentWorkout+"sunday", MODE_PRIVATE);
                Gson gsonSun = new Gson();
                String jsonSun = prefSun.getString(currentWorkout+"listSun", null);
                if (jsonSun == null){
                    ArrayList<Exercises> sundayExercises = new ArrayList<Exercises>();
                }
                else{sundayExercises = gsonSun.fromJson(jsonSun, type);}
                for(int j = 0; j < sundayExercises.size(); j++){
                    if (sundayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + sundayExercises.get(j).getName() + "     " + sundayExercises.get(j).getWeight() + "     X     " +  sundayExercises.get(j).getReps() + "     X     " + sundayExercises.get(j).getSets() + "\n";
                    }
                }

            } else if (offset.getDayOfWeek().name().equals("MONDAY")) {

                SharedPreferences prefMon = getSharedPreferences(currentWorkout+"monday", MODE_PRIVATE);
                Gson gsonMon = new Gson();
                String jsonMon = prefMon.getString(currentWorkout+"listMon", null);
                if (jsonMon == null){
                    ArrayList<Exercises> monday_exorcises = new ArrayList<Exercises>();
                }
                else{mondayExercises = gsonMon.fromJson(jsonMon, type);}

                for(int j = 0; j < mondayExercises.size(); j++){
                    if (mondayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + mondayExercises.get(j).getName() + "     " + mondayExercises.get(j).getWeight() + "     X     " +  mondayExercises.get(j).getReps() + "     X     " + mondayExercises.get(j).getSets() + "\n";

                    }

                }

            } else if (offset.getDayOfWeek().name().equals("TUESDAY")) {
                SharedPreferences prefTue = getSharedPreferences(currentWorkout+"tuesday", MODE_PRIVATE);
                Gson gsonTue = new Gson();
                String jsonTue = prefTue.getString(currentWorkout+"listTue", null);
                if (jsonTue == null){
                    ArrayList<Exercises> tuesday_exorcises = new ArrayList<Exercises>();
                }
                else{tuesdayExercises = gsonTue.fromJson(jsonTue, type);}
                for(int j = 0; j < tuesdayExercises.size(); j++){
                    if (tuesdayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + tuesdayExercises.get(j).getName() + "     " + tuesdayExercises.get(j).getWeight() + "     X     " +  tuesdayExercises.get(j).getReps() + "     X     " + tuesdayExercises.get(j).getSets() + "\n";
                    }
                }

            } else if (offset.getDayOfWeek().name().equals("WEDNESDAY")) {
                SharedPreferences prefWed = getSharedPreferences(currentWorkout+"wednesday", MODE_PRIVATE);
                Gson gsonWed = new Gson();
                String jsonWed = prefWed.getString(currentWorkout+"listWed", null);
                if (jsonWed == null){
                    ArrayList<Exercises> wednesdayExercises = new ArrayList<Exercises>();
                }
                else{wednesdayExercises = gsonWed.fromJson(jsonWed, type);}
                for(int j = 0; j < wednesdayExercises.size(); j++){
                    if (wednesdayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + wednesdayExercises.get(j).getName() + "     " + wednesdayExercises.get(j).getWeight() + "     X     " +  wednesdayExercises.get(j).getReps() + "     X     " + wednesdayExercises.get(j).getSets() + "\n";
                    }
                }
            } else if (offset.getDayOfWeek().name().equals("THURSDAY")) {
                SharedPreferences prefThu = getSharedPreferences(currentWorkout+"thursday", MODE_PRIVATE);
                Gson gsonThu = new Gson();
                String jsonThu = prefThu.getString(currentWorkout+"listThu", null);
                if (jsonThu == null){
                    ArrayList<Exercises> thursday_exorcises = new ArrayList<Exercises>();
                }
                else{thursdayExercises = gsonThu.fromJson(jsonThu, type);}

                for(int j = 0; j < thursdayExercises.size(); j++){
                    if (thursdayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + thursdayExercises.get(j).getName() + "     " + thursdayExercises.get(j).getWeight() + "     X     " +  thursdayExercises.get(j).getReps() + "     X     " + thursdayExercises.get(j).getSets() + "\n";
                    }
                }

            } else if (offset.getDayOfWeek().name().equals("FRIDAY")) {
                SharedPreferences prefFri = getSharedPreferences(currentWorkout+"friday", MODE_PRIVATE);
                Gson gsonFri = new Gson();
                String jsonFri = prefFri.getString(currentWorkout+"listFri", null);
                if (jsonFri == null){
                    ArrayList<Exercises> friday_exorcises = new ArrayList<Exercises>();
                }
                else{fridayExercises = gsonFri.fromJson(jsonFri, type);}

                for(int j = 0; j < fridayExercises.size(); j++){
                    if (fridayExercises.get(j).getName() != null){
                        exerciseList = exerciseList + fridayExercises.get(j).getName() + "     " + fridayExercises.get(j).getWeight() + "     X     " +  fridayExercises.get(j).getReps() + "     X     " + fridayExercises.get(j).getSets() + "\n";
                    }
                }

            } else if (offset.getDayOfWeek().name().equals("SATURDAY")) {
                SharedPreferences prefSat = getSharedPreferences(currentWorkout + "saturday", MODE_PRIVATE);
                Gson gsonSat = new Gson();
                String jsonSat = prefSat.getString(currentWorkout + "listSat", null);
                if (jsonSat == null) {
                    ArrayList<Exercises> saturday_exorcises = new ArrayList<Exercises>();
                } else {
                    saturdayExercises = gsonSat.fromJson(jsonSat, type);
                }
                for (int j = 0; j < saturdayExercises.size(); j++) {
                    if (saturdayExercises.get(j).getName() != null) {
                        exerciseList = exerciseList + saturdayExercises.get(j).getName() + "     " + saturdayExercises.get(j).getWeight() + "     X     " + saturdayExercises.get(j).getReps() + "     X     " + saturdayExercises.get(j).getSets() + "\n";
                    }
                }
            }

            }

        }
        else{exerciseList = "There are no current workouts yet";}
        currentExercise.setText(exerciseList);

        // Proceeds to MaxWeightActivity if maxButton is pressed
        maxButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, MaxWeightActivity.class);
                startActivity(intent);
            }
        });

        // Runs method when save button is clicked
        editBodyWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, EditBodyWeightActivity.class);
                startActivity(intent);
            }
        });

        Button workouts = (Button) findViewById(R.id.workouts);
        // Activates method on yes button click
        workouts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, WorkoutView.class);
                startActivity(intent);
            }
        });
    }
    // Loads exercise types
    public void loadExerciseTypes(){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("listExerciseTypeList", null);
        Type type = new TypeToken<ArrayList<ExerciseTypes>>() {}.getType();
        if (json == null){
            exerciseTypesList = new ArrayList<ExerciseTypes>();
        }
        else{exerciseTypesList = gson.fromJson(json, type);}
    }

    // Saves exercise types
    public void saveExerciseTypes(ArrayList<ExerciseTypes> exerciseTypesList){
        SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseTypesList);
        editor.putString("listExerciseTypeList", json);
        editor.apply();

    }

    // Saves spinner names
    public void saveSpinnerNames(){
        SharedPreferences pref = getSharedPreferences("spinnerNames", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseNames);
        editor.putString("spinnerNamesList", json);
        editor.apply();

    }

}