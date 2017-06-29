package com.example.android.provectustask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.provectustask.Utils.NetworkUtils;
import com.example.android.provectustask.Utils.UtilsJson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Request API URL
    final static String url = "https://randomuser.me/api/?results=5&noinfo";

    private class ParseJsonTask extends AsyncTask<String, Void, ArrayList<UserProfile>> {

        @Override
        protected ArrayList<UserProfile> doInBackground(String... params) {


            return UtilsJson.fetchUserProfileData(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<UserProfile> contains) {


        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetworkUtils.isNetworkAvailable(this)) {

            final List<UserProfile> profiles = new ArrayList<>();

            profiles.add(new UserProfile("mr", "smbody1", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody2", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody3", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody4", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody5", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            profiles.add(new UserProfile("mr", "smbody", "noone", "f", "loool", "odessa", "alabama",
                    345345, new Date(234234), "sdfsdf", "sdfsdf","herdemisemyrise.bt@gmail.com", "sdfsdf" ));
            // Create an {@link StatusAdapter}, whose data source is a list of
            // {@link Status}s. The adapter knows how to create list item views for each item
            // in the list.

            ProfileAdapter statusesAdapter = new ProfileAdapter(this, profiles);

            // Get a reference to the ListView, and attach the adapter to the listView.

            ListView listView = (ListView) this.findViewById(R.id.list_for_statuses);
            listView.setAdapter(statusesAdapter);


            // Set a click listener to play the audio when the list item is clicked on
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                    // Get the {@link Word} object at the given position the user clicked on
                    UserProfile profile = profiles.get(position);
                    Intent profileCardIntent = new Intent(MainActivity.this, ProfileActivity.class);

                    profileCardIntent.putExtra("name", profile.getFirstName());
                    profileCardIntent.putExtra("email", profile.getEmail());

                    new ParseJsonTask().execute(url);

                    startActivity(profileCardIntent);




                }
            });
        }
    }

}
