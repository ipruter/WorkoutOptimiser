package com.example.workoutmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditBodyWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_body_weight);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // Initialize variables
        TextView tvDisplayBodyWeight = (TextView)findViewById(R.id.display_body_weight);
        EditText inputBodyWeight = (EditText)findViewById(R.id.body_weight_form);
        String bodyWeight;
        bodyWeight = pref.getString("bodyWeight", "0"); // Retrieves body weight from storage
        tvDisplayBodyWeight.setText(bodyWeight); // Displays body weight in textview

        // Runs method when save button is clicked
        inputBodyWeight.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String body_weight = "0";
                body_weight = String.valueOf(inputBodyWeight.getText()); // Retrieves user input from edit text box
                //Creates objects for saving data
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("bodyWeight", body_weight);
                editor.commit(); // Saves body weight
                body_weight = pref.getString("bodyWeight", "0"); // Gets body weight
                tvDisplayBodyWeight.setText(body_weight); // Displays body weight
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Button home = (Button) findViewById(R.id.body_weight_home);
        // Activates method on yes button click
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Goes back to the main activity
                Intent intent = getIntent();
                intent = new Intent(EditBodyWeightActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}