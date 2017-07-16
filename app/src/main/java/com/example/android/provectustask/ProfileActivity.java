package com.example.android.provectustask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;


/**
 * Created by nexus on 28.06.2017.
 */
public class ProfileActivity extends AppCompatActivity {

    private class DownloadAvatarImageTask extends AsyncTask<String, Void, Bitmap> {

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
            ImageView avatar = (ImageView) findViewById(R.id.avatar);
            avatar.setImageBitmap(result);
        }
    }

    private class DownloadBackgroundImageTask extends AsyncTask<String, Void, Bitmap> {

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
            ImageView randomBackground = (ImageView) findViewById(R.id.randomBackgroundUserPic);
            randomBackground.setImageBitmap(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ProgressBar spinner = (ProgressBar) this.findViewById(R.id.profileProgressBar);
        Bitmap backgroundPic = null;
        Bitmap avatarPic = null;

        spinner.setVisibility(View.VISIBLE);

        Intent userInfo = getIntent();

        // Recieving a serialized info bundle to fill in profile page
        Bundle bundle = userInfo.getExtras();
        UserProfile profile = (UserProfile) bundle.getSerializable("profile");

        //Fullname
        String fullName = String.format("%s. %s %s", profile != null ? profile.getTitle() : " ", profile.getFirstName(), profile.getLastName());

        TextView nameView = (TextView) this.findViewById(R.id.userFullName);
        nameView.setText(fullName);

        //DoB
        String dob = profile.getDateOfBirth();
        TextView dateOfBirthView = (TextView) this.findViewById(R.id.userBirthday);
        dateOfBirthView.setText(dob);

        //Email
        String email = profile.getEmail();
        TextView emailView = (TextView) this.findViewById(R.id.userEmail);
        emailView.setText(email);

        //Phones
        String phone = profile.getPhone();
        String cellphone = profile.getCellPhone();

        TextView phoneView = (TextView) this.findViewById(R.id.userPhone);
        TextView cellphoneView = (TextView) this.findViewById(R.id.userCellPhone);

        phoneView.setText(phone);
        cellphoneView.setText(cellphone);

        //Full address
        String street = profile.getStreet();
        TextView streetView = (TextView) this.findViewById(R.id.userStreet);
        TextView miscAddressView = (TextView) this.findViewById(R.id.userMisc);

        String city = profile.getCity();
        String postalCode = profile.getPostalCode();
        String state = profile.getState();
        String misc = String.format("%s, %s, %s", city, postalCode, state);

        miscAddressView.setText(misc);
        streetView.setText(street);

        //TODO: extract image downloading into separate class to avoid code duplication
        //Gettin random pic for background and avatar

        new DownloadAvatarImageTask().execute(profile.getPictureUrlMedium());
        new DownloadBackgroundImageTask().execute("https://unsplash.it/450/450/?random");

        spinner.setVisibility(View.GONE);
        LinearLayout profileLayout = (LinearLayout) this.findViewById(R.id.profileLayout);
        profileLayout.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
