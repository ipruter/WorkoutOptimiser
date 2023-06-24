package com.example.workoutmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercises> {
    private ArrayList<Exercises> exorcises;
    public ExerciseAdapter(Context context, ArrayList<Exercises> exorcises) {
        super(context, 0, exorcises);
        this.exorcises = exorcises;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Exercises exorcise = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvReps = (TextView) convertView.findViewById(R.id.tvReps);
        TextView tvSets = (TextView) convertView.findViewById(R.id.tvSets);
        TextView tvWeight = (TextView) convertView.findViewById(R.id.tvWeight);
        // Populate the data into the template view using the data object
        tvName.setText(exorcise.name);
        tvReps.setText("x    " + exorcise.reps);
        tvSets.setText("x    " + exorcise.sets);
        tvWeight.setText(exorcise.weight);

        return convertView;
    }
}
