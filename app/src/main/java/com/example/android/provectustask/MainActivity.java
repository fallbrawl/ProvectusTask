package com.example.android.provectustask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.provectustask.Utils.NetworkUtils;
import com.example.android.provectustask.Utils.UtilsJson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //Request API URL
    final static String url = "https://randomuser.me/api/?results=10&noinfo";
    private ArrayList<UserProfile> userProfiles = new ArrayList<>();
    ProgressBar spinner;

    //Task for retrieving array of {@link Profile}s
    private class ParseJsonTask extends AsyncTask<String, Void, ArrayList<UserProfile>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner =  (ProgressBar) findViewById(R.id.progressBar1);
            spinner.setIndeterminate(true);
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<UserProfile> doInBackground(String... params) {
            ArrayList <UserProfile> wow = new ArrayList<>();

            try {
                wow =  UtilsJson.fetchUserProfileData(params[0]);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            return wow;
        }

        @Override
        protected void onPostExecute(ArrayList<UserProfile> contains) {
            spinner.setVisibility(View.GONE);
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (NetworkUtils.isNetworkAvailable(this)) {
            //Fetchin data

            try {
                userProfiles = new ParseJsonTask().execute(url).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // Create an {@link ProfileAdapter}, whose data source is a list of
            // {@link Profile}s. The adapter knows how to create list item views for each item
            // in the list.
            ProfileAdapter statusesAdapter = new ProfileAdapter(this, userProfiles);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) this.findViewById(R.id.list_for_profiles);
            listView.setAdapter(statusesAdapter);



            // Set a click listener to get to the correct profile info from the list
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    // Get the {@link UserProfile} object at the given position the user clicked on
                    UserProfile profile = userProfiles.get(position);
                    Intent profileCardIntent = new Intent(MainActivity.this, ProfileActivity.class);

                    //Passing serialized object to the new activity
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("profile", profile);
                    profileCardIntent.putExtras(bundle);
                    startActivity(profileCardIntent);
                }
            });
        }
    }

}
