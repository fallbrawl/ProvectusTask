package com.example.android.provectustask;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nexus on 28.06.2017.
 */
public class ProfileAdapter extends ArrayAdapter<UserProfile> {
    public ProfileAdapter(Activity context, List<UserProfile> profiles) {
        //// Initializing the ArrayAdapter's internal storage for the context and the list.
        super(context, 0, profiles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listUsersView = convertView;

        if (listUsersView == null) {
            listUsersView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link UserProfile} object located at this position in the list
        UserProfile currentProfile = getItem(position);

        // Find the TextView in the list_item.xml layout with the user's name
        TextView dateTextView = (TextView) listUsersView.findViewById(R.id.list_item_users_name);

        // Get the user's name from the current UserProfile object and
        // set this text on the name TextView
        dateTextView.setText(currentProfile.getFirstName() + " " + currentProfile.getLastName());

        // Find the TextView in the list_item.xml layout with the user's email
        TextView resourceTextView = (TextView) listUsersView.findViewById(R.id.list_item_users_email);

        // Get the version number from the current UserProfile object and
        // set this text on the number TextView
        resourceTextView.setText(currentProfile.getEmail());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView thumbnailView = (ImageView) listUsersView.findViewById(R.id.list_item_thumbnail);
        // Get the image resource ID from the current UserProfile object and
        // set the image

        thumbnailView.setImageResource(R.drawable.avatar);

        return listUsersView;

    }}
