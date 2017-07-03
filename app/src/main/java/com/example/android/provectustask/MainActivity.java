package com.example.android.provectustask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.provectustask.Utils.NetworkUtils;
import com.example.android.provectustask.Utils.UtilsJson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //Request API URL
    final static String url = "https://randomuser.me/api/?results=10&noinfo";
    Context context = getBaseContext();
    private ArrayList<UserProfile> userProfiles = new ArrayList<>();


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

        }
    }

    //Task for retrieving array of {@link Profile}s
    private class ParseJsonTask extends AsyncTask<String, Void, ArrayList<UserProfile>> {

        @Override
        protected ArrayList<UserProfile> doInBackground(String... params) {
            ArrayList <UserProfile> wow = new ArrayList<>();
            UtilsJson utilsJson = new UtilsJson();
            try {
                wow =  utilsJson.fetchUserProfileData(params[0]);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.e("staty wow", String.valueOf(wow.size()));

            return wow;
        }

        @Override
        protected void onPostExecute(ArrayList<UserProfile> contains) {

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

            Log.e("staty asyn", String.valueOf(userProfiles.size()));
            // Create an {@link ProfileAdapter}, whose data source is a list of
            // {@link Profile}s. The adapter knows how to create list item views for each item
            // in the list.
            ProfileAdapter statusesAdapter = new ProfileAdapter(this, userProfiles);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) this.findViewById(R.id.list_for_statuses);
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
