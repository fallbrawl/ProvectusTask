package com.example.android.provectustask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by nexus on 28.06.2017.
 */
public class ProfileActivity extends AppCompatActivity {
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

        TextView name = (TextView) this.findViewById(R.id.userFullName);
        name.setText(fullName);
    }
}
