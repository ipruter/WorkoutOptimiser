package com.example.workoutmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WorkoutAdapter extends ArrayAdapter<String> {
    private ArrayList<String> workouts;
    public WorkoutAdapter(Context context, ArrayList<String> workouts) {
        super(context, 0, workouts);
        this.workouts = workouts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String workouts = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item2, parent, false);
        }
        TextView workout_item = (TextView) convertView.findViewById(R.id.workout_item);
        workout_item.setText(workouts);

        return convertView;
    }
}
