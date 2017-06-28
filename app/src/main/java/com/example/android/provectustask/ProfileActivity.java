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
        Intent userInfo = getIntent();


        // Recieving an info to fill in profile page from intent

        String fullName = userInfo.getStringExtra("name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView name = (TextView) this.findViewById(R.id.userFullName);
        name.setText(fullName);
    }
}
