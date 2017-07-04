package com.example.android.provectustask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;


/**
 * Created by nexus on 28.06.2017.
 */
public class ProfileActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent userInfo = getIntent();

        // Recieving a serialized info bundle to fill in profile page
        Bundle bundle = userInfo.getExtras();
        UserProfile profile = (UserProfile) bundle.getSerializable("profile");

        //Fullname
        String fullName = String.format("%s. %s %s", profile != null ? profile.getTitle() : " ", profile.getFirstName(), profile.getLastName() );

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

        //Avatar

        ImageView avatar = (ImageView) this.findViewById(R.id.avatar);

        //TODO: extract image downloading into separate class to avoid code duplication

        try {
            if (avatar.getDrawable() == null){
                Bitmap avatarPic = new DownloadImageTask().execute(profile.getPictureUrlMedium()).get();
                avatar.setImageBitmap(avatarPic);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
