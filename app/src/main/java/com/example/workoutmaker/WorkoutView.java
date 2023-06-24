package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;

public class WorkoutView extends AppCompatActivity {
     static ArrayList<String> workouts = new ArrayList<>(); // Initialize array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);
        ListView workoutsLV = findViewById(R.id.workoutsLV);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // Initialize SharedPreferences object
        HashSet<String> set = (HashSet<String>) pref.getStringSet("workouts", null); // Initialize hash set
        if(set != null){workouts = new ArrayList(set);} // Check if hashset is null
        // Using custom listView Provided by Android Studio
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(this, workouts); // Initialize workout adapter
        workoutsLV.setAdapter(workoutAdapter); // Set adapter to listview

        workoutsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // Listens for clicks on list elements
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("currentWorkout", workouts.get(i));
                editor.commit();
                // Going from MainActivity to NotesEditorActivity
                Intent intent = new Intent(getApplicationContext(), ExerciseStandInActivity.class);
                intent.putExtra("workoutId", workouts.get(i));
                startActivity(intent);
            }
        });

       workoutsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            // Listens for long clicks on each list element
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                // To delete the data from the App
                new AlertDialog.Builder(WorkoutView.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                workouts.remove(itemToDelete); // Deletes element
                                workoutAdapter.notifyDataSetChanged();
                                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                HashSet<String> set = new HashSet(WorkoutView.workouts); // Updates hashset
                                pref.edit().putStringSet("workouts", set).apply();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });

        Button add = (Button) findViewById(R.id.addWorkout);
        // Activates method on yes button click
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText workout_name = (EditText) findViewById(R.id.workout_name);
                String workout = String.valueOf(workout_name.getText());
                workouts.add(workout); // Adds workout
                workoutAdapter.notifyDataSetChanged();
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                HashSet<String> set = new HashSet(WorkoutView.workouts); // Updates hashset
                pref.edit().putStringSet("workouts", set).apply();
            }
        });

        Button quickWorkout = (Button) findViewById(R.id.quickWorkout);
        // Activates method on yes button click
        quickWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(WorkoutView.this, QuickWorkoutActivity.class);
                startActivity(intent);
            }
        });
    }
}