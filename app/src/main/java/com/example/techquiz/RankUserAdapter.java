package com.example.techquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RankUserAdapter extends ArrayAdapter<User> {

    public RankUserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_rank_view, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.userName);
        TextView tvScore = (TextView) convertView.findViewById(R.id.userScore);
        // Populate the data into the template view using the data object
        tvName.setText(user.getUsername());
        tvScore.setText(Integer.toString(user.getScore()));
        // Return the completed view to render on screen
        return convertView;
    }
}
