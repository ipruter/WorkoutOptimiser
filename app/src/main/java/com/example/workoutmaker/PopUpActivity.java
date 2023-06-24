package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class PopUpActivity extends AppCompatActivity {
    // Initialize arrays
    ArrayList<String> exerciseNames = new ArrayList<String>();
    ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        // Initialize buttons
        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Goes back to previous activity
                Intent intent = new Intent(PopUpActivity.this, MaxWeightActivity.class);
                startActivity(intent);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Fetch data that is passed from MainActivity
                Intent intent = getIntent();
                // Accessing the data using key and value
                String name = intent.getStringExtra("name");
                    SharedPreferences pref = getSharedPreferences("exerciseTypeList", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = pref.getString("listExerciseTypeList", null); // Loads json file from storage
                    Type type = new TypeToken<ArrayList<ExerciseTypes>>() {
                    }.getType();
                    // Creates array if json is null
                    if (json == null) {
                        ArrayList<ExerciseTypes> exerciseTypesList = new ArrayList<ExerciseTypes>();
                    } else {
                        exerciseTypesList = gson.fromJson(json, type);
                    }

                    SharedPreferences pref2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    HashSet<String> setName = (HashSet<String>) pref2.getStringSet("exerciseNames", null); // Initializes hash set of exercise names

                    // Checks if hash set is null
                    if (setName != null) {
                        exerciseNames = new ArrayList(setName);
                    }

                    // Deletes exercise type
                    for (int i = 0; i < exerciseTypesList.size(); i++) {
                        if (exerciseTypesList.get(i).getName().equals(name)) {
                            exerciseTypesList.remove(i);
                        }
                    }
                    //Deletes associated spinner name
                    for (int i = 0; i < exerciseNames.size(); i++) {
                        if (exerciseNames.get(i).equals(name)) {
                            exerciseNames.remove(i);
                        }
                    }

                    // Updates changes
                    setName = new HashSet(exerciseNames);
                    pref2.edit().putStringSet("exerciseNames", setName).apply();
                    SharedPreferences.Editor editor = pref.edit();
                    json = gson.toJson(exerciseTypesList);
                    editor.putString("listExerciseTypeList", json);
                    editor.apply();
                    // Goes back to previous activity
                intent = new Intent(PopUpActivity.this, MaxWeightActivity.class);
                startActivity(intent);
            }

        });
    }
}