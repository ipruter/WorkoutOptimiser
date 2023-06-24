package com.example.workoutmaker;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Type;
import java.util.concurrent.ThreadLocalRandom;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class ExerciseView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();
        // Accessing the data using key and value
        String workoutId = intent.getStringExtra("workoutId");

        Button edit_sunday = (Button)findViewById(R.id.edit_sunday);
        Button edit_monday = (Button)findViewById(R.id.edit_monday);
        Button edit_tuesday = (Button)findViewById(R.id.edit_tuesday);
        Button edit_wednesday = (Button)findViewById(R.id.edit_wednesday);
        Button edit_thursday = (Button)findViewById(R.id.edit_thursday);
        Button edit_friday = (Button)findViewById(R.id.edit_friday);
        Button edit_saturday = (Button)findViewById(R.id.edit_saturday);

        // Activates method on yes button click
        edit_sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, SundayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, MondayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, TuesdayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, WednesdayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, ThursdayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, FridayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });

        edit_saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExerciseView.this, SaturdayExercisesActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);

            }
        });
    }

}