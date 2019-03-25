package com.example.codelabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView username;
    private TextView organisation;
    private TextView profileLink;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        username = findViewById(R.id.activity_detail_username);
        organisation = findViewById(R.id.activity_detail_organisation);
        profileLink = findViewById(R.id.activity_detail_profile_link);
        profileImage = findViewById(R.id.activity_detail_profile_image);

        username.setText(getIntent().getExtras().getString("Username"));
        profileImage.setImageResource(getIntent().getExtras().getInt("ProfileImage"));
        organisation.setText("Andela");
        profileLink.setText("http://github.com/profile");

    }
}
