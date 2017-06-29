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

        ViewHolder holder;

        if (listUsersView == null) {
            listUsersView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            holder = new ViewHolder();

            // Find the TextView in the list_item.xml layout with the user's name
            holder.dateTextView = (TextView) listUsersView.findViewById(R.id.list_item_users_name);
            // Find the TextView in the list_item.xml layout with the user's email
            holder.resourceTextView = (TextView) listUsersView.findViewById(R.id.list_item_users_email);
            // Find the ImageView in the list_item.xml layout with the ID list_item_icon
            holder.thumbnailView = (ImageView) listUsersView.findViewById(R.id.list_item_thumbnail);

            listUsersView.setTag(holder);
        }

        holder = (ViewHolder) listUsersView.getTag();

        // Get the {@link UserProfile} object located at this position in the list
        UserProfile currentProfile = getItem(position);

        // Get the user's name from the current UserProfile object and
        // set this text on the name TextView
        holder.dateTextView.setText(String.format("%s %s",currentProfile.getFirstName(),currentProfile.getLastName()));

        // Get the version number from the current UserProfile object and
        // set this text on the number TextView
        holder.resourceTextView.setText(currentProfile.getEmail());

        // Get the image resource ID from the current UserProfile object and
        // set the image

        holder.thumbnailView.setImageResource(R.drawable.avatar);

        return listUsersView;

    }

    private class ViewHolder {

        TextView dateTextView;
        TextView resourceTextView;
        ImageView thumbnailView;

    }

}